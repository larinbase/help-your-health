package ru.itis.healthauthimpl.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.healthauthapi.dto.*;
import ru.itis.healthauthimpl.service.JwtTokenService;
import ru.itis.healthauthimpl.service.UserService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtTokenService jwtTokenService;
    private final SessionService sessionService;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    public TokenCoupleResponse signIn(AuthenticationRequest authenticationRequest) {
        String username = authenticationRequest.username();
        UserResponse user = userService.findByUsername(username);
        checkPassword(authenticationRequest.password(), user.password());
        return jwtTokenService.generateTokenCouple(new AccountRequest(username, List.of(Role.USER)));
    }

    private void checkPassword(final String presentedPassword, final String currentHashPassword) {
        if (!passwordEncoder.matches(presentedPassword, currentHashPassword)) {
            log.debug("Failed to authenticate since password does not match stored value");
            throw new BadCredentialsException("Bad credentials");
        }
    }

    public TokenCoupleResponse refreshToken(TokenCoupleRequest tokenCoupleRequest) {
        return jwtTokenService.refreshAccessToken(tokenCoupleRequest);
    }

    public void logout(TokenCoupleRequest tokenCoupleRequest) {
        sessionService.deleteByRefreshToken(tokenCoupleRequest.refreshToken());
    }
}