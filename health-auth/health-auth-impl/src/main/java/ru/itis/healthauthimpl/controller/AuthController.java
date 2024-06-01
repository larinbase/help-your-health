package ru.itis.healthauthimpl.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.healthauthapi.api.AuthApi;
import ru.itis.healthauthapi.dto.AuthenticationRequest;
import ru.itis.healthauthapi.dto.TokenCoupleRequest;
import ru.itis.healthauthapi.dto.TokenCoupleResponse;
import ru.itis.healthauthimpl.service.AuthService;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final AuthService authService;

    @Override
    public TokenCoupleResponse signIn(AuthenticationRequest authenticationRequest) {
        return authService.signIn(authenticationRequest);
    }

    @Override
    public TokenCoupleResponse refreshToken(TokenCoupleRequest tokenCoupleRequest) {
        return authService.refreshToken(tokenCoupleRequest);
    }

    @Override
    public void logout(TokenCoupleRequest tokenCoupleRequest) {
        authService.logout(tokenCoupleRequest);
    }
}