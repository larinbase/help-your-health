package ru.itis.healthauthimpl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.healthauthapi.api.AuthApi;
import ru.itis.healthauthapi.dto.AccountRequest;
import ru.itis.healthauthapi.dto.TokenCoupleRequest;
import ru.itis.healthauthapi.dto.TokenCoupleResponse;
import ru.itis.healthauthimpl.service.JwtTokenService;
import ru.itis.healthauthimpl.service.RefreshTokenService;

@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final JwtTokenService jwtTokenService;
    private final RefreshTokenService refreshTokenService;

    @Override
    public TokenCoupleResponse refreshAccessToken(TokenCoupleRequest tokenCoupleRequest) {
        return jwtTokenService.refreshAccessToken(tokenCoupleRequest);
    }

    @Override
    public TokenCoupleResponse generateTokenCouple(AccountRequest accountRequest) {
        return jwtTokenService.generateTokenCouple(accountRequest);
    }

    @Override
    public void deleteRefreshToken(TokenCoupleResponse tokenCoupleResponse) {
//        refreshTokenService.delete(tokenCoupleResponse.refreshToken());
    }
}
