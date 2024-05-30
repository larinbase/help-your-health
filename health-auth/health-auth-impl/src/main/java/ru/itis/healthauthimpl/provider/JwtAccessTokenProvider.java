package ru.itis.healthauthimpl.provider;

import io.jsonwebtoken.Claims;
import ru.itis.healthauthapi.dto.AccountRequest;
import ru.itis.healthauthapi.dto.Role;

import java.util.List;
import java.util.Map;

public interface JwtAccessTokenProvider {

    String generateAccessToken(String subject, Map<String, Object> data);

    boolean isValidToken(String token);

    AccountRequest userInfoByToken(String token);

    Claims parseAccessToken(String accessToken);

    List<Role> getRolesFromAccessToken(String accessToken);
}
