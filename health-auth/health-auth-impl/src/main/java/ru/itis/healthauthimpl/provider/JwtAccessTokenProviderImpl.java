package ru.itis.healthauthimpl.provider;


import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itis.healthauthapi.dto.AccountRequest;
import ru.itis.healthauthapi.dto.Role;
import ru.itis.healthauthimpl.exception.AuthenticationException;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAccessTokenProviderImpl implements JwtAccessTokenProvider {

    @Value("${jwt.expiration.access.mills}")
    private long expirationAccessInMills;

    @Value("${jwt.secret}")
    private String jwtSecret;


    @Override
    public String generateAccessToken(String subject, Map<String, Object> data) {
        Map<String, Object> claims = new HashMap<>(data);
//        claims.put(Claims.SUBJECT, subject);
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(Date.from(Instant.now().plusMillis(expirationAccessInMills)))
                .signWith(new SecretKeySpec(jwtSecret.getBytes(), "HmacSHA512"))
                .compact();
    }

    @Override
    public boolean isValidToken(String token) {
        try {
            Key secretKey = new SecretKeySpec(jwtSecret.getBytes(), "HmacSHA512");
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public AccountRequest userInfoByToken(String token) {
        try {
            Claims claims = parseAccessToken(token);
            log.info("claims: {}", claims);
            List<Role> roles = getRolesFromAccessToken(token);
            log.info("roles: {}", roles);
            String subject = claims.getSubject();
            log.info("subject: {}", subject);
//            AccountService accountService = accountProvider.getAccountService(roles);
            return new AccountRequest(subject, roles);
        } catch (ExpiredJwtException e) {
            throw new AuthenticationException("Token expired"); //TODO: AuthenticationException
        }
    }

    @Override
    public Claims parseAccessToken(String accessToken) {
        Key secretKey = new SecretKeySpec(jwtSecret.getBytes(), "HmacSHA512");
        return Jwts
                .parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(accessToken)
                .getBody();
    }

    @Override
    public List<Role> getRolesFromAccessToken(String accessToken) {
        try {
            Key secretKey = new SecretKeySpec(jwtSecret.getBytes(), "HmacSHA512");
            return (List<Role>) Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build()
                    .parseClaimsJws(accessToken).getBody().get("role");
        } catch (ExpiredJwtException e) {
            return (List<Role>) e.getClaims().get("role");
        }
    }


}

