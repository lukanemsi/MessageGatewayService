package ge.nemsi.MessageGateway.repository;

import ge.nemsi.MessageGateway.entity.Message;
import ge.nemsi.MessageGateway.entity.MessagePK;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing Message entities.
 * Extends Spring Data's JpaRepository for basic CRUD operations.
 *
 * @author Luka Nemsitsveridze
 * This repository manages {@link Message} entities identified by {@link MessagePK}.
 */
public interface MessageRepository extends JpaRepository<Message, MessagePK> {

}