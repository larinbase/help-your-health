package ru.itis.healthauthapi.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.healthauthapi.dto.AuthenticationRequest;
import ru.itis.healthauthapi.dto.TokenCoupleRequest;
import ru.itis.healthauthapi.dto.TokenCoupleResponse;

@Tag(name = "auth-api")
@RequestMapping("auth-api/v1/auth")
public interface AuthApi {
    @PostMapping("/sign-in")
    TokenCoupleResponse signIn(@RequestBody AuthenticationRequest authenticationRequest);

    @PostMapping("/refresh-tokens")
    TokenCoupleResponse refreshToken(@RequestBody TokenCoupleRequest tokenCoupleRequest);

    @PostMapping("/logout")
    void logout(@RequestBody TokenCoupleRequest tokenCoupleRequest);

}
