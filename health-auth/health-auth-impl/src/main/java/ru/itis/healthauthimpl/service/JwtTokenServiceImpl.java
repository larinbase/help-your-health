package ru.itis.healthauthimpl.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itis.healthauthapi.dto.AccountRequest;
import ru.itis.healthauthapi.dto.TokenCoupleRequest;
import ru.itis.healthauthapi.dto.TokenCoupleResponse;
import ru.itis.healthauthimpl.exception.AuthenticationException;
import ru.itis.healthauthimpl.provider.JwtAccessTokenProvider;
import ru.itis.healthauthimpl.provider.JwtRefreshTokenProvider;

import java.util.Collections;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtTokenServiceImpl implements JwtTokenService {

    private final JwtAccessTokenProvider jwtAccessTokenProvider;

    private final JwtRefreshTokenProvider jwtRefreshTokenProvider;

    private final SessionService sessionService;


    @Override
    public TokenCoupleResponse generateTokenCouple(AccountRequest accountRequest) {
        log.info("generateTokenCouple: {}", accountRequest);

        String accessToken = jwtAccessTokenProvider.generateAccessToken(
                accountRequest.subject(),
                Collections.singletonMap("role", accountRequest.roles())
        );

        String refreshToken = jwtRefreshTokenProvider.generateRefreshToken();

        sessionService.save(refreshToken, accountRequest.subject());

        return new TokenCoupleResponse(accessToken, refreshToken); // TODO: надо ли BEARER.concat(" ").concat(accessToken))
    }

    @Override
    public TokenCoupleResponse refreshAccessToken(TokenCoupleRequest tokenCoupleRequest) {
        if (!jwtRefreshTokenProvider.isRefreshTokenExpired(tokenCoupleRequest.refreshToken())) {
            AccountRequest accountRequest = jwtAccessTokenProvider.userInfoByToken(tokenCoupleRequest.accessToken());
            String accessToken = jwtAccessTokenProvider.generateAccessToken(
                    accountRequest.subject(),
                    Collections.singletonMap("role", accountRequest.roles())
            );

            String newRefreshToken = jwtRefreshTokenProvider.generateRefreshToken();

            sessionService.updateRefreshToken(tokenCoupleRequest.refreshToken(), newRefreshToken);

            return new TokenCoupleResponse(accessToken, newRefreshToken);
        } else {
            throw new AuthenticationException("Refresh token expired");
        }
    }


}
