package ru.itis.healthserviceimpl.scheduling;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@EnableScheduling
public class CacheEvicting {

    private final CacheManager cacheManager;

    @Scheduled(fixedRate = 10000)
    public void evictAllCaches() {
        cacheManager.getCacheNames()
                .forEach(cacheName ->
                        Objects.requireNonNull(cacheManager.getCache(cacheName)).clear());
    }
}

