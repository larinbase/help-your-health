package ru.itis.healthserviceimpl.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceimpl.service.CacheService;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@EnableScheduling
public class CacheServiceImpl implements CacheService {

    private final CacheManager cacheManager;

    @Override
    @Scheduled(fixedRate = 6000)
    public void evictAllCaches() {
        cacheManager.getCacheNames()
                .forEach(cacheName ->
                        Objects.requireNonNull(cacheManager.getCache(cacheName)).clear());
    }
}
