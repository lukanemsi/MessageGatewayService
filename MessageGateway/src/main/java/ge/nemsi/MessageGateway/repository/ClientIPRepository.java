package ge.nemsi.MessageGateway.repository;

import ge.nemsi.MessageGateway.entity.ClientIP;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing ClientIP entities.
 * Extends Spring Data's JpaRepository for basic CRUD operations.
 *
 * @author Luka Nemsitsveridze
 * This repository manages {@link ClientIP} entities identified by their unique identifier ({@link String}).
 */
public interface ClientIPRepository extends JpaRepository<ClientIP, String> {
}
