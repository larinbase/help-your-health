package ru.itis.healthauthimpl.service;

import ru.itis.healthauthapi.dto.AccountRequest;
import ru.itis.healthauthapi.dto.TokenCoupleRequest;
import ru.itis.healthauthapi.dto.TokenCoupleResponse;

public interface JwtTokenService {

    TokenCoupleResponse generateTokenCouple(AccountRequest accountRequest);

    TokenCoupleResponse refreshAccessToken(TokenCoupleRequest tokenCoupleRequest);
}
