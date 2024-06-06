package ru.itis.healthserviceapi.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

public record DrinkingWaterRequest(

        @NotNull
        @Positive
        int milliliters,

        @NotNull
        UUID accountId
) {
}
