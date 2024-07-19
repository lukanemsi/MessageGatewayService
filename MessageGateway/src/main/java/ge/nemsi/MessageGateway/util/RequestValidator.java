package ge.nemsi.MessageGateway.util;

import ge.nemsi.MessageGateway.entity.ClientIP;
import ge.nemsi.MessageGateway.exception.MessageGatewayException;
import ge.nemsi.MessageGateway.repository.ClientIPRepository;
import ge.nemsi.MessageGateway.repository.ClientRepository;
import ge.nemsi.MessageGateway.wrapper.ClientProperties;
import ge.nemsi.MessageGateway.wrapper.SmsRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * util class for validating
 *
 * @author Luka Nemsitsveridze
 * @hidden
 * @role offers methods for validating incoming requests
 */
@Log4j2
@Component
@RequiredArgsConstructor
public class RequestValidator {

    private final ClientRepository clientRepository;
    private final ClientIPRepository clientIPRepository;
    private final ClientProperties clientProperties;
    private final Pattern pattern = Pattern.compile("(\\+\\d{1,3})?\\d{9}");

    /**
     * util class for validating
     *
     * @param clientName    client who send the request
     * @param remoteAddress ip of the sender
     * @param smsRequest    incoming request object
     * @throws MessageGatewayException If any of validation fails.
     * @author Luka Nemsitsveridze
     * @role validates Client Ip in Database, its phone number and Channel
     */
    @SneakyThrows
    public void validateRequest(String clientName, SmsRequest smsRequest, String remoteAddress) {
        log.trace("validation started");
        clientRepository.findById(clientName).orElseThrow(() -> new MessageGatewayException("Invalid Client"));
        ClientIP clientIP = clientIPRepository.findById(remoteAddress).orElseThrow(() -> new MessageGatewayException("Invalid IP address"));
        if (!clientIP.getClient().getClientName().equals(smsRequest.getRequesterName()))
            throw new MessageGatewayException("IP not compatible with requester");
        if (!pattern.matcher(smsRequest.getPhoneNumber()).matches())
            throw new MessageGatewayException("Invalid PhoneNumber format");
        if (!clientProperties.getChannels().get(smsRequest.getRequesterName()).contains(String.valueOf(smsRequest.getChannel())))
            throw new MessageGatewayException("Invalid channel");
        log.trace("validation ended");
    }

}