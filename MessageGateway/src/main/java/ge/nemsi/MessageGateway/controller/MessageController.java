package ge.nemsi.MessageGateway.controller;

import ge.nemsi.MessageGateway.service.MessageService;
import ge.nemsi.MessageGateway.wrapper.SmsRequest;
import ge.nemsi.MessageGateway.wrapper.SmsResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Luka Nemsitsveridze
 * @role Controller class that handles HTTP requests related to message handling.
 *
 * <p>This class maps incoming HTTP requests to methods that interact with {@link MessageService}
 * to process SMS gateway requests for different clients.
 */
@Controller
@RequestMapping("/message")
@RequiredArgsConstructor
public class MessageController {
    private final MessageService messageService;

    /**
     * Handles HTTP POST requests for SMS gateway functionality.
     *
     * @param clientName The name of the client requesting the SMS gateway service.
     * @param smsRequest The SMS request {@link SmsRequest} payload containing message details.
     * @param request    The HTTP servlet request object to retrieve client details.
     * @return ResponseEntity containing the SMS response {@link SmsResponse} after processing the request.
     */

    @PostMapping(value = "/{clientName}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<SmsResponse> smsGateway(@PathVariable("clientName") String clientName, @RequestBody SmsRequest smsRequest, HttpServletRequest request) {
        return messageService.smsGateway(clientName, smsRequest, request);
    }
}