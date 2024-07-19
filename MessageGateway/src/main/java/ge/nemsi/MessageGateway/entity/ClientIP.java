package ge.nemsi.MessageGateway.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author Luka Nemsitsveridze
 * Entity class representing Client IP addresses stored in the CLIENT_IP table.
 *
 * <p>This class maps to the CLIENT_IP table in the database.
 * It contains an IP address as its primary key and maintains a many-to-one relationship with {@link Client}.
 */
@Entity
@Table(name = "CLIENT_IP")
@Data
public class ClientIP {
    @Id
    @Column(name = "IP", nullable = false)
    private String ip;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CLIENT_NAME", referencedColumnName = "CLIENT_NAME", nullable = false)
    private Client client;
}
