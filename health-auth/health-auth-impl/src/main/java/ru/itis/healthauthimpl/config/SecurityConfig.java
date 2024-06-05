package ru.itis.healthauthimpl.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2ClientConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private static final String[] SWAGGER_URLS = { "/swagger-ui/**", "/v3/api-docs/**", "/v2/api-docs/**",
            "/swagger-resources/**", "/swagger-ui.html", "/webjars/**" };
    private static final String[] PERMIT_ALL = {"/auth-api/**, gitlab/v1/auth/**", "/oauth2/**"};

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        try {
            return http
                    .csrf(CsrfConfigurer::disable)
                    .sessionManagement(managementConfigurer -> {
                        managementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
                    })
                    .authorizeHttpRequests(request -> request
                            .requestMatchers(PERMIT_ALL).permitAll()
                            .requestMatchers(SWAGGER_URLS).permitAll()
                            .requestMatchers(HttpMethod.POST, "/gitlab/v1/auth/**").permitAll()
                    )
                    .httpBasic(HttpBasicConfigurer::disable)
                    .logout(LogoutConfigurer::disable)
                    .cors(CorsConfigurer::disable)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Bean
    public PasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
