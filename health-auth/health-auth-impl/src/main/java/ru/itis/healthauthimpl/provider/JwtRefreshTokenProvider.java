package ru.itis.healthauthimpl.provider;

import ru.itis.healthauthapi.dto.AccountRequest;

public interface JwtRefreshTokenProvider {

    String generateRefreshToken(AccountRequest accountRequest);

    Boolean isRefreshTokenExpired(String refreshToken);
}
