package ru.itis.healthauthimpl.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.itis.healthauthimpl.exception.AuthenticationException;
import ru.itis.healthauthimpl.model.SessionEntity;
import ru.itis.healthauthimpl.repository.SessionRepository;

import java.time.Instant;

@Slf4j
@Service
@RequiredArgsConstructor
public class SessionService {

    private final SessionRepository sessionRepository;

    @Value("${jwt.expiration.refresh.mills}")
    private long expirationRefreshInMills;

    public void save(String refreshToken, String subject) {
        SessionEntity sessionEntity = SessionEntity.builder()
                .subject(subject)
                .refreshToken(refreshToken)
                .createdAt(Instant.now())
                .expiresIn(Instant.now().plusMillis(expirationRefreshInMills))
                .build();
        sessionRepository.save(sessionEntity);
    }

    public SessionEntity findByRefreshToken(String refreshToken) {
        return sessionRepository.findByRefreshToken(refreshToken)
                .orElseThrow(() -> new AuthenticationException("Refresh token is not found"));
    }

    public void updateRefreshToken(String oldRefreshToken ,String newRefreshToken){
        SessionEntity sessionEntity = sessionRepository.findByRefreshToken(oldRefreshToken)
                .orElseThrow(() -> new AuthenticationException("Refresh token is not found"));
        sessionEntity.setExpiresIn(Instant.now().plusMillis(expirationRefreshInMills));
        sessionEntity.setRefreshToken(newRefreshToken);
        sessionRepository.save(sessionEntity);
    }

    public void deleteByRefreshToken(String refreshToken) {
        sessionRepository.deleteByRefreshToken(refreshToken);
    }
}
