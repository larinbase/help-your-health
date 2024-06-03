package ru.itis.healthserviceimpl.security.service.impl;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceimpl.security.provider.JwtTokenProvider;
import ru.itis.healthserviceimpl.security.dto.request.JwtTokenCreateRequest;
import ru.itis.healthserviceimpl.security.dto.response.JwtTokenPayloadResponse;
import ru.itis.healthserviceimpl.security.service.JwtTokenService;

import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
public class JwtTokenServiceImpl implements JwtTokenService {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public JwtTokenPayloadResponse parseToken(String token) {
        Claims claims = jwtTokenProvider.parseToken(token);
        String username = (String) claims.get("username");
        Date expiredDate = claims.getExpiration();
        Date now = Date.from(Instant.now());
        boolean isExpired = now.after(expiredDate);
        return new JwtTokenPayloadResponse(username, isExpired);
    }

    @Override
    public String create(JwtTokenCreateRequest request) {
        String username = request.username();
        Map<String, Object> data = new HashMap<>();
        data.put("username", username);
        return jwtTokenProvider.createToken(data);
    }
}
