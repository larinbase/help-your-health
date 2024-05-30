package ru.itis.healthauthapi.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.healthauthapi.dto.AccountRequest;
import ru.itis.healthauthapi.dto.TokenCoupleRequest;
import ru.itis.healthauthapi.dto.TokenCoupleResponse;

@Tag(name = "auth-api")
@RequestMapping("auth-api/v1/auth")
public interface JwtTokenApi {

    @PostMapping("/access-token/refresh")
    TokenCoupleResponse refreshAccessToken(@RequestBody TokenCoupleRequest tokenCoupleRequest);

    @PostMapping("/token-couple/generate")
    TokenCoupleResponse generateTokenCouple(@RequestBody AccountRequest accountRequest);

    @DeleteMapping("/refresh-token")
    void deleteRefreshToken(@RequestBody TokenCoupleResponse tokenCoupleResponse);
}
