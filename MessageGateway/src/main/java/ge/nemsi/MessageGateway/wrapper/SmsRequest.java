package ge.nemsi.MessageGateway.wrapper;

import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

/**
 * @author Luka Nemsitsveridze
 * @role entity that is sent from client to this service
 */
@Data
@XmlRootElement(name = "SmsRequest")
public class SmsRequest implements Cloneable {

    private String phoneNumber;

    private Long requestId;

    private String requesterName;

    private Integer channel;

    private boolean decode;

    private Integer language;

    @Override
    public SmsRequest clone() {
        try {
            return (SmsRequest) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
