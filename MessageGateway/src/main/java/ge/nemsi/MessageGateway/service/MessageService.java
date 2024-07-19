package ge.nemsi.MessageGateway.service;

import ge.nemsi.MessageGateway.entity.Message;
import ge.nemsi.MessageGateway.entity.MessagePK;
import ge.nemsi.MessageGateway.exception.MessageGatewayException;
import ge.nemsi.MessageGateway.gateway.GatewaySender;
import ge.nemsi.MessageGateway.repository.MessageRepository;
import ge.nemsi.MessageGateway.util.RequestValidator;
import ge.nemsi.MessageGateway.wrapper.SmsRequest;
import ge.nemsi.MessageGateway.wrapper.SmsResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Luka Nemsitsveridze
 * @role Service class for handling SMS gateway operations.
 * It validates incoming SMS requests using {@link RequestValidator} and sends SMS messages through {@link GatewaySender}.
 */
@Service
@Log4j2
@RequiredArgsConstructor
public class MessageService {

    private final RequestValidator clientValidator;
    private final GatewaySender gatewaySender;
    private final MessageRepository messageRepository;

    @SneakyThrows
    public ResponseEntity<SmsResponse> smsGateway(String clientName, SmsRequest smsRequest, HttpServletRequest request) {
        clientValidator.validateRequest(clientName, smsRequest, request.getRemoteAddr());

        MessagePK messagePK = new MessagePK(clientName, smsRequest.getChannel(), smsRequest.getLanguage());
        Message message = messageRepository.findById(messagePK).orElseThrow(() -> new MessageGatewayException("Message not Found for pk:" + messagePK));

        return gatewaySender.send(smsRequest, message);
    }
}
