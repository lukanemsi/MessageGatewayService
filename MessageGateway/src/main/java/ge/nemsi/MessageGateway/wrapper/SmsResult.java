package ge.nemsi.MessageGateway.wrapper;

import lombok.Data;

/**
 * @author Luka Nemsitsveridze
 * @role entity that is sent to the client via gateway
 */
@Data
public class SmsResult {
    private String phoneNumber;
    private boolean isDecoded;
    private String message;
}
