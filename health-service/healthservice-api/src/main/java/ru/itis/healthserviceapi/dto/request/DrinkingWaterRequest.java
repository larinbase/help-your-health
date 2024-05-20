package ru.itis.healthserviceapi.dto.request;

import java.util.UUID;

public record DrinkingWaterRequest(int milliliters, UUID accountId) {
}
