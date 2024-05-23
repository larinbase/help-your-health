package ru.itis.healthserviceimpl.security.service.impl;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceimpl.model.CommunityRole;
import ru.itis.healthserviceimpl.model.User;
import ru.itis.healthserviceimpl.model.roles.CommunityRoleType;
import ru.itis.healthserviceimpl.repository.UserRepository;
import ru.itis.healthserviceimpl.security.JwtTokenProvider;
import ru.itis.healthserviceimpl.security.dto.request.JwtTokenCreateRequest;
import ru.itis.healthserviceimpl.security.dto.response.JwtTokenPayloadResponse;
import ru.itis.healthserviceimpl.security.service.JwtTokenService;
import ru.itis.healthserviceimpl.security.userdetails.BaseUserDetails;

import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
public class JwtTokenServiceImpl implements JwtTokenService {

    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public JwtTokenPayloadResponse parseToken(String token) {
        Claims claims = jwtTokenProvider.parseToken(token);
        String username = (String) claims.get("username");
        CommunityRoleType roleType = CommunityRoleType.valueOf((String) claims.get("communityRole"));
        Date expiredDate = claims.getExpiration();
        Date now = Date.from(Instant.now());
        boolean isExpired = now.after(expiredDate);
        return new JwtTokenPayloadResponse(username, roleType, isExpired);
    }

    @Override
    public String create(JwtTokenCreateRequest request) {
        String username = request.username();
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.username());
        Map<String, Object> data = new HashMap<>();
        Collection<? extends  GrantedAuthority> communityRoleTypes = userDetails.getAuthorities();
        String roleTypeName = communityRoleTypes.stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElseThrow();
        data.put("username", username);
        data.put("communityRoleType", roleTypeName);
        return jwtTokenProvider.createToken(data);
    }
}
