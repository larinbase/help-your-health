package ru.itis.healthauthimpl.controller.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(name = "github-oAuth2-token", url = "https://github.com")
public interface GithubOAuth2ClientToken {
    @PostMapping("/login/oauth/access_token?client_id={var1}&client_secret={var2}&code={var3}")
    String getAccessToken(@PathVariable("var1") String clientId,
                          @PathVariable("var2") String clientSecret,
                          @PathVariable("var3") String code);
}
