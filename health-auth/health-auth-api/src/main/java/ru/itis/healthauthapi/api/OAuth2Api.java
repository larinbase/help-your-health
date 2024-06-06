package ru.itis.healthauthapi.api;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import ru.itis.healthauthapi.dto.TokenCoupleResponse;

@Tag(name = "OAuth2")
@RequestMapping("githab/v1/auth")
public interface OAuth2Api {

    @PostMapping("/{code}")
    TokenCoupleResponse signIn(@PathVariable("code") String code);

}
