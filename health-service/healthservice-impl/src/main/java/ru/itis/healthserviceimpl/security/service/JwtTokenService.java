package ru.itis.healthserviceimpl.security.service;

import ru.itis.healthserviceimpl.security.dto.request.JwtTokenCreateRequest;
import ru.itis.healthserviceimpl.security.dto.response.JwtTokenPayloadResponse;

public interface JwtTokenService {
    JwtTokenPayloadResponse parseToken(String token);

    String create(JwtTokenCreateRequest request);
}
