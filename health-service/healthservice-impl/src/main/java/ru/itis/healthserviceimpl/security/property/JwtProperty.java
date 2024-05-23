package ru.itis.healthserviceimpl.security.property;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class JwtProperty {
    private final long expirationAccessInMills;
    private final String jwtSecret;

    public JwtProperty(@Value("${jwt.expiration.millis}") long expirationAccessInMills,
                       @Value("${jwt.secret}") String jwtSecret) {
        this.expirationAccessInMills = expirationAccessInMills;
        this.jwtSecret = jwtSecret;
    }
}
