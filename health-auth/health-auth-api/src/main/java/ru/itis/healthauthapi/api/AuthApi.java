package ru.itis.healthauthapi.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.healthauthapi.dto.AccountRequest;
import ru.itis.healthauthapi.dto.TokenCoupleRequest;
import ru.itis.healthauthapi.dto.TokenCoupleResponse;

@Tag(name = "auth-api")
@RequestMapping("auth-api/v1/auth")
public interface AuthApi {

    @PostMapping("/access-token/refresh")
    TokenCoupleResponse refreshAccessToken(TokenCoupleRequest tokenCoupleRequest);

    @PostMapping("/token-couple/generate")
    TokenCoupleResponse generateTokenCouple(AccountRequest accountRequest);

    @DeleteMapping("/refresh-token")
    void deleteRefreshToken(TokenCoupleResponse tokenCoupleResponse);
}
