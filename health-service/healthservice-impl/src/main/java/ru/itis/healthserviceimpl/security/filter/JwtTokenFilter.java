package ru.itis.healthserviceimpl.security.filter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.itis.healthserviceimpl.security.dto.response.JwtTokenPayloadResponse;
import ru.itis.healthserviceimpl.security.execption.AuthenticationHeaderException;
import ru.itis.healthserviceimpl.security.service.JwtTokenService;
import ru.itis.healthserviceimpl.util.HttpResponseUtil;
import ru.itis.healthserviceimpl.util.SecurityConstants;

import java.io.IOException;

@RequiredArgsConstructor
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenService jwtTokenService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            log.info("New request in JwtTokenFilter");
            String authHeader = request.getHeader("AUTHORIZATION");
            log.info("Get AUTHORIZATION header {}", authHeader);
            if (authHeader == null || !authHeader.startsWith(SecurityConstants.BEARER)) {
//                throw new AuthenticationHeaderException("Invalid authentication scheme found in Authorization header");
                doFilter(request,response,filterChain);
                return;
            }
            String jwtToken = StringUtils.delete(authHeader, SecurityConstants.BEARER);
            log.info("Get JWT token payload");
            JwtTokenPayloadResponse payload = jwtTokenService.parseToken(jwtToken);
            log.info("payload {}", payload);
            if (payload.username() == null) {
//                throw new AuthenticationHeaderException("username in token is null");
                doFilter(request,response,filterChain);
                return;
            }
            log.info("Get userdetails by username - %s".formatted(payload.username()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(payload.username());
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    payload.username(), null, userDetails.getAuthorities()
            );
            log.info("Set authentication in security context");
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            doFilter(request, response, filterChain);
        } catch (Exception exception) {
            //HttpResponseUtil.putExceptionInResponse(request,response, exception, HttpServletResponse.SC_UNAUTHORIZED);
            doFilter(request,response,filterChain);
        }
    }
}
