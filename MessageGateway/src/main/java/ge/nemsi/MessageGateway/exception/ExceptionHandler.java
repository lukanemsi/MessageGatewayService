package ge.nemsi.MessageGateway.exception;

import ge.nemsi.MessageGateway.wrapper.SmsResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/**
 * @author Luka Nemsitsveridze
 * @role Global exception handler for handling specific exceptions in the Message Gateway application.
 * <p>
 * for {@link MessageGatewayException} and {@link ExternalGatewayException}.
 * creates an appropriate {@link SmsResponse} with error details and returns corresponding HTTP status codes.
 */
@Log4j2
@ControllerAdvice
public class ExceptionHandler {

    /**
     * Exception handler for {@link MessageGatewayException}.
     *
     * @param e          The exception instance of {@link MessageGatewayException} thrown.
     * @param webRequest The current web request.
     * @return A ResponseEntity with an {@link SmsResponse} containing error details and HTTP status code.
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(MessageGatewayException.class)
    public ResponseEntity<SmsResponse> handleMessageGatewayException(MessageGatewayException e, WebRequest webRequest) {
        log.error(e);
        SmsResponse smsResponse = new SmsResponse();
        smsResponse.setLocalDateTime(LocalDateTime.now());
        smsResponse.setText(e.getMessage());
        smsResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(smsResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Exception handler for {@link ExternalGatewayException}.
     *
     * @param e          The exception instance of {@link ExternalGatewayException} thrown.
     * @param webRequest The current web request.
     * @return A ResponseEntity with an {@link SmsResponse} containing error details and HTTP status code.
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(ExternalGatewayException.class)
    public ResponseEntity<SmsResponse> handleExternalGatewayException(ExternalGatewayException e, WebRequest webRequest) {
        log.error(e);
        SmsResponse smsResponse = new SmsResponse();
        smsResponse.setLocalDateTime(LocalDateTime.now());
        smsResponse.setText(e.getMessage());
        smsResponse.setStatusCode(HttpStatus.BAD_GATEWAY.value());
        return new ResponseEntity<>(smsResponse, HttpStatus.BAD_GATEWAY);
    }

    /**
     * Handles generic {@link Exception}s that are not specifically handled by other methods.
     *
     * @param e          The exception instance.
     * @param webRequest The current web request.
     * @return A ResponseEntity with an {@link SmsResponse} containing error details and HTTP status code.
     */
    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<SmsResponse> handleException(Exception e, WebRequest webRequest) {
        log.error(e);
        SmsResponse smsResponse = new SmsResponse();
        smsResponse.setLocalDateTime(LocalDateTime.now());
        smsResponse.setText(e.getMessage());
        smsResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(smsResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}