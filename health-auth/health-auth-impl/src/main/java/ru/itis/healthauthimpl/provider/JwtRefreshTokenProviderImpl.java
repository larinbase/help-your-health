package ru.itis.healthauthimpl.provider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.itis.healthauthapi.dto.AccountRequest;
import ru.itis.healthauthimpl.model.SessionEntity;
import ru.itis.healthauthimpl.service.SessionService;

import java.time.Instant;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtRefreshTokenProviderImpl implements JwtRefreshTokenProvider {

    private final SessionService sessionService;

    @Override
    public String generateRefreshToken() {
//        String role = accountResponse.getRole().name();
//        return String.valueOf
//        (accountProvider.getAccountRefreshTokenService(roles)
//                .generateRefreshToken(accountResponse).getId());
//        refreshTokenService.save(UUID.randomUUID().toString(), accountRequest.getSubject());
        return UUID.randomUUID().toString();
    }


    @Override
    public Boolean isRefreshTokenExpired(String refreshToken) {
        SessionEntity sessionEntity = sessionService.findByRefreshToken(refreshToken);
        log.info(sessionEntity.toString());
        if (sessionEntity != null) {
            log.info("Refresh token expired: {}", sessionEntity.getExpiresIn().isBefore(Instant.now()));
            return !sessionEntity.getExpiresIn().isAfter(Instant.now());
        }
        return true;
    }

}

//    @Override
//    public RefreshTokenEntity verifyRefreshTokenExpiration(String refreshToken, List<String> roles) {
//        return accountProvider.getAccountRefreshTokenService(roles).verifyRefreshTokenExpiryDate(refreshToken);
//    }

