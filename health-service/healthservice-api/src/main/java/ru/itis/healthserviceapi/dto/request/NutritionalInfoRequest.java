package ru.itis.healthserviceapi.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record NutritionalInfoRequest(

        @NotNull
        @Positive
        int calories,

        @NotNull
        @Positive
        int protein,

        @NotNull
        @Positive
        int fat,

        @NotNull
        @Positive
        int carbohydrates
) {
}
