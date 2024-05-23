package ru.itis.healthserviceimpl.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.*;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;
    private static final String[] SWAGGER_URLS = { "/swagger-ui/**", "/v3/api-docs/**", "/v2/api-docs/**",
            "/swagger-resources/**", "/swagger-ui.html", "/webjars/**" };
    private static final String[] PERMIT_ALL = {};
    private static final String[] AUTHENTICATED = {"/api/v1/**"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        try {

            return http
                    .csrf(CsrfConfigurer::disable)
                    .cors(CorsConfigurer::disable) // поизучать
                    .sessionManagement(managementConfigurer -> {
                        managementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                    })
                    .authorizeHttpRequests(request -> request
                            .requestMatchers(PERMIT_ALL).permitAll()
                            .requestMatchers(SWAGGER_URLS).permitAll()
                            .requestMatchers(AUTHENTICATED).authenticated()
                    )
                    .formLogin(FormLoginConfigurer::disable)
                    .cors(CorsConfigurer::disable)
                    .httpBasic(HttpBasicConfigurer::disable)
                    .logout(LogoutConfigurer::disable)
                    //.addFilterAfter()
                    //.authenticationProvider(authenticationProvider())
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(bCryptPasswordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }
}
