package ge.nemsi.MessageGateway.exception;

/**
 * @author Luka Nemsitsveridze
 * @role Exception class for representing errors related to message gateway operations.
 * <p>Used to indicate internal server errors (HTTP status code 4xx) during message gateway operations.
 */
public class MessageGatewayException extends Exception {
    public MessageGatewayException() {
    }

    public MessageGatewayException(String message) {
        super(message);
    }

    public MessageGatewayException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessageGatewayException(Throwable cause) {
        super(cause);
    }
}
