package ru.itis.healthauthimpl.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.healthauthapi.dto.AccountRequest;
import ru.itis.healthauthapi.dto.TokenCoupleRequest;
import ru.itis.healthauthapi.dto.TokenCoupleResponse;
import ru.itis.healthauthimpl.provider.JwtAccessTokenProvider;
import ru.itis.healthauthimpl.provider.JwtRefreshTokenProvider;

import java.util.Collection;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class JwtTokenServiceImpl implements JwtTokenService {

    private final JwtAccessTokenProvider jwtAccessTokenProvider;

    private final JwtRefreshTokenProvider jwtRefreshTokenProvider;


    @Override
    public TokenCoupleResponse generateTokenCouple(AccountRequest accountRequest) {

        // TODO: нужно аккаунт реквест данные спарсить в Map<String, Object>
        //                 Collections.singletonMap(ROLE, accountResponse
        //                        .getRole())
        String accessToken = jwtAccessTokenProvider.generateAccessToken(
                accountRequest.subject(),
                Collections.singletonMap("role", accountRequest.roles())
        );

        String refreshToken = jwtRefreshTokenProvider.generateRefreshToken(accountRequest);

        return new TokenCoupleResponse(accessToken, refreshToken); // TODO: надо ли BEARER.concat(" ").concat(accessToken))
    }

    @Override
    public TokenCoupleResponse refreshAccessToken(TokenCoupleRequest tokenCoupleRequest) {

        String refreshToken = tokenCoupleRequest.refreshToken();

//        if (!jwtRefreshTokenProvider.isRefreshTokenExpired(refreshToken)) {
//            String accessToken = jwtAccessTokenProvider.generateAccessToken();
//            String refreshToken = jwtRefreshTokenProvider.generateRefreshToken();
//            return new TokenCoupleResponse(accessToken, refreshToken);
//        } else {
//            throw new RuntimeException("Refresh token expired");
//        }

        return null;

    }
}
