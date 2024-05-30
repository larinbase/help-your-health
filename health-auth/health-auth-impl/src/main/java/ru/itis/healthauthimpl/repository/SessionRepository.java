package ru.itis.healthauthimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.healthauthimpl.model.SessionEntity;

import java.util.Optional;

public interface SessionRepository extends JpaRepository<SessionEntity, String> {

    Optional<SessionEntity> findByRefreshToken(String refreshToken);

    void deleteByRefreshToken(String refreshToken);
}
