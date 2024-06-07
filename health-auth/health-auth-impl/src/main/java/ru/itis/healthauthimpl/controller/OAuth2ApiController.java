package ru.itis.healthauthimpl.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RestController;
import ru.itis.healthauthapi.api.OAuth2Api;
import ru.itis.healthauthapi.dto.TokenCoupleResponse;
import ru.itis.healthauthimpl.service.OAuth2Service;

@RestController
@RequiredArgsConstructor
public class OAuth2ApiController implements OAuth2Api {

    private final OAuth2Service service;

    @Override
    public TokenCoupleResponse signIn(String code) {
        return service.signIn(code);
    }
}
