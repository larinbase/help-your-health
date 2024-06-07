package ru.itis.healthauthimpl.service;

import ru.itis.healthauthapi.dto.TokenCoupleResponse;

public interface OAuth2Service {
    TokenCoupleResponse signIn(String code);
}
