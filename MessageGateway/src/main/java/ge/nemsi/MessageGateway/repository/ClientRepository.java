package ge.nemsi.MessageGateway.repository;

import ge.nemsi.MessageGateway.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Client entities.
 * Extends Spring Data's JpaRepository for basic CRUD operations.
 *
 * @author Luka Nemsitsveridze
 * This repository manages {@link Client} entities identified by their unique client name ({@link String}).
 */
public interface ClientRepository extends JpaRepository<Client, String> {
}