package ge.nemsi.MessageGateway.gateway;

import ge.nemsi.MessageGateway.entity.Client;
import ge.nemsi.MessageGateway.entity.Message;
import ge.nemsi.MessageGateway.exception.ExternalGatewayException;
import ge.nemsi.MessageGateway.exception.MessageGatewayException;
import ge.nemsi.MessageGateway.repository.ClientRepository;
import ge.nemsi.MessageGateway.wrapper.SmsRequest;
import ge.nemsi.MessageGateway.wrapper.SmsResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Luka Nemsitsveridze
 * @role Component class responsible for sending SMS requests to different gateways based on client request.
 * <p>This class interacts with {@link ClientRepository} to retrieve client information.
 * It sends SMS requests using various implementations of {@link GatewayI} such as {@link GoogleGateway}, {@link NetflixGateway}, or {@link DefaultGateway}.
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class GatewaySender {
    private final ClientRepository clientRepository;

    /**
     * @param smsRequest The SMS request details encapsulated in {@link SmsRequest}.
     * @param messageObj The message object containing message details.
     * @return A ResponseEntity containing the response of sending the SMS, encapsulated in {@link SmsResponse}.
     * @throws MessageGatewayException  If there's an internal server error (HTTP status code 4xx).
     * @throws ExternalGatewayException If there's an issue with the external gateway (HTTP status code 5xx).
     * @role Sends an SMS request to the appropriate gateway based on client information.
     */
    @SneakyThrows
    public ResponseEntity<SmsResponse> send(SmsRequest smsRequest, Message messageObj) {
        SmsRequest requestClone = smsRequest.clone();
        String message = getFinalMessage(messageObj.getMessage(), smsRequest.isDecode());
        GatewayI gatewayI = switch (requestClone.getRequesterName()) {
            case "Google" -> new GoogleGateway();
            case "Netflix" -> new NetflixGateway();
            default -> new DefaultGateway();
        };
        log.info("send request to gateway:{}", requestClone.getRequesterName());
        Client client = clientRepository.findById(smsRequest.getRequesterName()).orElseThrow();
        int statusCode = gatewayI.send(client.getUrl(), requestClone, message);
        log.info("get response from gateway:{}, statusCode:{}", requestClone.getRequesterName(), statusCode);

        SmsResponse smsResponse = new SmsResponse();
        smsResponse.setLocalDateTime(LocalDateTime.now());

        if (statusCode >= 200 && statusCode < 300) {
            smsResponse.setStatusCode(200);
            smsResponse.setText("Message Sent Successfully");
        } else if (statusCode >= 400 && statusCode < 500) {
            throw new MessageGatewayException("Internal Server Error");
        } else if (statusCode > 500) {
            throw new ExternalGatewayException("Gateway had some problem");
        }
        return new ResponseEntity<>(smsResponse, HttpStatusCode.valueOf(smsResponse.getStatusCode()));
    }

    /**
     * @param message  message which needs modification
     * @param isDecode boolean weather or not to decode message in base64
     * @return returns final 'ready to send' sms
     * @role adds 4 digits code for message and decodes if necessary
     */
    private String getFinalMessage(String message, boolean isDecode) {
        message = message.replace("XXXX", String.valueOf(ThreadLocalRandom.current().nextInt(1000, 10000)));
        if (isDecode)
            message = new String(Base64.getDecoder().decode(message));
        return message;
    }


}
