package ru.itis.healthserviceapi.dto.response;

import java.time.Instant;
import java.util.UUID;

public record DrinkingWaterResponse(UUID id, Instant createDate, int milliliters) {
}
