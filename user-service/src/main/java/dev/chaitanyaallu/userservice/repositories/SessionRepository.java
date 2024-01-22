package dev.chaitanyaallu.userservice.repositories;

import dev.chaitanyaallu.userservice.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SessionRepository extends JpaRepository<Session, UUID> {
    Session save(Session session);
    Optional<Session> findByTokenEquals(String token);
    void deleteByTokenEquals(String token);
    Optional<Session> findByTokenAndUser_Id(String token, UUID userId);
}
