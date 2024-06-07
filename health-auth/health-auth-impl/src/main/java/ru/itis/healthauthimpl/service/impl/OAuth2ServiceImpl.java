package ru.itis.healthauthimpl.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import ru.itis.healthauthapi.dto.AccountRequest;
import ru.itis.healthauthapi.dto.Role;
import ru.itis.healthauthapi.dto.TokenCoupleResponse;
import ru.itis.healthauthapi.dto.UserResponse;
import ru.itis.healthauthimpl.controller.feignclient.GithubOAuth2ClientToken;
import ru.itis.healthauthimpl.controller.feignclient.GithubOAuth2ClientUserInfo;
import ru.itis.healthauthimpl.property.GithubOAuth2Property;
import ru.itis.healthauthimpl.service.JwtTokenService;
import ru.itis.healthauthimpl.service.OAuth2Service;
import ru.itis.healthauthimpl.service.UserService;
import ru.itis.healthauthimpl.util.GithubTokenParser;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OAuth2ServiceImpl implements OAuth2Service {

    private final GithubOAuth2ClientToken tokenClient;
    private final GithubOAuth2ClientUserInfo userInfoClient;
    private final GithubOAuth2Property property;
    private final UserService userService;
    private final JwtTokenService jwtTokenService;

    @Override
    public TokenCoupleResponse signIn(String code) {
        String accessTokenResponse;
        try {
            accessTokenResponse =
                    tokenClient.getAccessToken(property.getClientId(), property.getClientSecret(), code);
        } catch (Exception e) {
            throw new BadCredentialsException("Bad credentials");
        }
        log.info("current user accessTokenResponse: {}", accessTokenResponse);
        String accessToken = GithubTokenParser.parseAccessToken(accessTokenResponse);
        log.info("current user accessToken: {}", accessToken);
        String tokenWithBearer = "%s %s".formatted("Bearer", accessToken);
        log.info("current user tokenWithBearer: {}", tokenWithBearer);
        String userInfoResponse;
        try {
            userInfoResponse = userInfoClient.get(tokenWithBearer);
        } catch (Exception e) {
            throw new BadCredentialsException("Bad credentials");
        }
        log.info("current user oAuth2: {}", userInfoResponse);
        GithubTokenParser.UserInfo userInfo = GithubTokenParser.parseUserJson(userInfoResponse);
        String username = userInfo.getLogin();
        UserResponse userResponse;
        try {
            userResponse = userService.findByUsername(username);

        } catch (Exception e) {
            throw new BadCredentialsException("Bad credentials");
        }
        if (userResponse == null || userResponse.username() == null) {
            throw new BadCredentialsException("Bad credentials");
        }
        return jwtTokenService.generateTokenCouple(new AccountRequest(username, List.of(Role.USER)));
    }
}
