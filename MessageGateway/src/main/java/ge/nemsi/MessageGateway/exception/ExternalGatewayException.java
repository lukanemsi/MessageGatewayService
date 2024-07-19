package ge.nemsi.MessageGateway.exception;

/**
 * @author Luka Nemsitvseridze
 * @role Exception class for representing errors related to external gateway operations.
 *
 * <p>Used to indicate issues with external gateways (HTTP status code 5xx) during message operations.
 */
public class ExternalGatewayException extends Exception {
    public ExternalGatewayException() {
    }

    public ExternalGatewayException(String message) {
        super(message);
    }

    public ExternalGatewayException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExternalGatewayException(Throwable cause) {
        super(cause);
    }
}
