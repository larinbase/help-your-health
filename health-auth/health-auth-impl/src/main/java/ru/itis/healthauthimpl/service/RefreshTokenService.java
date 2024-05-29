package ru.itis.healthauthimpl.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final Map<String, String> refreshStorage = new HashMap<>();

    public void save(String refreshToken, String subject) {
        refreshStorage.put(subject, refreshToken);
    }

    public String get(String subject){
        return refreshStorage.get(subject);
    }

}
