package ge.nemsi.MessageGateway.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @author Luka Nemsitsveridze
 * Entity class representing Client information stored in the CLIENT table.
 *
 * <p>This class maps to the CLIENT table in the database.
 * It contains a client name as its primary key and a URL representing the client's endpoint.
 */
@Entity
@Table(name = "CLIENT")
@Data
public class Client {
    @Id
    @Column(name = "CLIENT_NAME", nullable = false)
    private String clientName;

    @Column(name = "ULR", nullable = false)
    private String url;

}
