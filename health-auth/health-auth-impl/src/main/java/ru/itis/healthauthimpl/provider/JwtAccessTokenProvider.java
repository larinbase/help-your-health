package ru.itis.healthauthimpl.provider;

import java.util.Map;

public interface JwtAccessTokenProvider {

    String generateAccessToken(String subject, Map<String, Object> data);

    boolean isValidToken(String token);
}
