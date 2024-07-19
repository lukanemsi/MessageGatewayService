package ge.nemsi.MessageGateway.gateway;

import ge.nemsi.MessageGateway.wrapper.SmsRequest;


/**
 * @author Luka Nemsitsveridze
 * @role Interface for defining methods to send SMS messages.
 *
 * <p>Implementing classes should provide an implementation of the {@code send} method,
 */
public interface GatewayI {
    /**
     * @param url        The URL of the gateway to send the SMS message.
     * @param smsRequest The SMS request details encapsulated in {@link SmsRequest}.
     * @param message    The message content to send.
     * @return The HTTP status code indicating the success or failure of the message send operation.
     * @role Sends an SMS message to the specified URL.
     */
    int send(String url, SmsRequest smsRequest, String message);
}
