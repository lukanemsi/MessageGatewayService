package ge.nemsi.MessageGateway.wrapper;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Luka Nemsitsveridze
 * @role Component class for holding property values related to clients.
 *
 * <p>This class is responsible for retrieving and managing client properties configured in the application.
 * It maps properties prefixed with "client" into the fields of this class.
 */
@Component
@ConfigurationProperties(prefix = "client")
@Data
public class ClientProperties {
    /**
     * A map containing client names as keys and their associated channels as values.
     *
     * @return The map consisting of client names as keys and their channels as values.
     */
    private final Map<String, String> channels = new HashMap<>();
}