package ru.itis.healthauthimpl.property;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class GithubOAuth2Property {
    private final String clientId;
    private final String clientSecret;
    private final String scope;
    private final String tokenType;

    public GithubOAuth2Property(@Value("${api.github.clientId}") String clientId,
                                @Value("${api.github.clientSecret}") String clientSecret,
                                @Value("${api.github.scope}") String scope,
                                @Value("${api.github.tokenType}") String token_type) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.scope = scope;
        this.tokenType = token_type;
    }
}
