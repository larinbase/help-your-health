package ru.itis.healthauthimpl.controller.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@Component
@FeignClient(name = "github-oAuth2-userinfo", url = "https://api.github.com")
public interface GithubOAuth2ClientUserInfo {
    @GetMapping("/user")
    String get(@RequestHeader("Authorization") String accessToken);
}
