package ge.nemsi.MessageGateway.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Luka Nemsitsveridze
 * Entity class representing a Message stored in the MESSAGE table.
 *
 * <p>This class maps to the MESSAGE table in the database.
 * It contains a composite primary key {@link MessagePK} and a message content.
 */
@Entity
@Table(name = "MESSAGE")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @EmbeddedId
    private MessagePK messagePK;

    @Column(name = "MESSAGE")
    private String message;

}