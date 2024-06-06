package ru.itis.healthauthimpl.provider;

public interface JwtRefreshTokenProvider {

    String generateRefreshToken();

    Boolean isRefreshTokenExpired(String refreshToken);
}
