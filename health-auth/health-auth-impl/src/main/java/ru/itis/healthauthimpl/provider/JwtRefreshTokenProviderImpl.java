package ru.itis.healthauthimpl.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.itis.healthauthapi.dto.AccountRequest;
import ru.itis.healthauthimpl.service.RefreshTokenService;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtRefreshTokenProviderImpl implements JwtRefreshTokenProvider {

//    private final AccountProvider accountProvider;
    private final RefreshTokenService refreshTokenService;

    @Override
    public String generateRefreshToken(AccountRequest accountRequest) {
//        String role = accountResponse.getRole().name();
//        return String.valueOf
//        (accountProvider.getAccountRefreshTokenService(roles)
//                .generateRefreshToken(accountResponse).getId());
//        refreshTokenService.save(UUID.randomUUID().toString(), accountRequest.getSubject());
        return UUID.randomUUID().toString();
    }


    @Override
    public Boolean isRefreshTokenExpired(String refreshToken) {
        return true;
    }

}

//    @Override
//    public RefreshTokenEntity verifyRefreshTokenExpiration(String refreshToken, List<String> roles) {
//        return accountProvider.getAccountRefreshTokenService(roles).verifyRefreshTokenExpiryDate(refreshToken);
//    }

