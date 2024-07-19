package ge.nemsi.MessageGateway.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author Luka Nemsitsveridze
 * Embeddable class representing the primary key for the {@link Message} entity.
 * <p>This class is used as the composite key in JPA entities
 */
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessagePK implements Serializable {

    @Column(name = "CLIENT_NAME", nullable = false)
    private String clientName;

    @Column(name = "CLIENT_CHANNEL", nullable = false)
    private int clientChannel;

    @Column(name = "LANGUAGE")
    private int language;

}