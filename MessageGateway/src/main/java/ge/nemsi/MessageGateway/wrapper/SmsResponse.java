package ge.nemsi.MessageGateway.wrapper;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author Luka Nemsitsveridze
 * @role entity that is sent to the client as a service response
 */
@Data
public class SmsResponse {
    private LocalDateTime localDateTime;
    private String text;
    private int statusCode;
}