package ru.itis.healthserviceimpl.security.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceimpl.model.CommunityRole;
import ru.itis.healthserviceimpl.model.User;
import ru.itis.healthserviceimpl.model.roles.CommunityRoleType;
import ru.itis.healthserviceimpl.repository.UserRepository;
import ru.itis.healthserviceimpl.security.userdetails.BaseUserDetails;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BaseUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User with username %s is not found".formatted(username));
        }
        User user = userOptional.get();
        CommunityRole role = user.getRole();
        CommunityRoleType roleType = role.getType();
        String roleTypeName = roleType.name();
        List<SimpleGrantedAuthority> roles = List.of(new SimpleGrantedAuthority(roleTypeName));
        return BaseUserDetails.builder()
                .id(user.getId())
                .password(user.getPassword())
                .username(user.getUsername())
                .firstName(user.getFirstname())
                .lastName(user.getUsername())
                .isAccountNonExpired(true)
                .isAccountNonLocked(true)
                .isCredentialsNonExpired(true)
                .isEnabled(true)
                .authorities(roles)
                .build();
    }
}
