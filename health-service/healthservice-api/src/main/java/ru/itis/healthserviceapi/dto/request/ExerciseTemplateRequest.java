package ru.itis.healthserviceapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ExerciseTemplateRequest(

        @NotBlank
        @Size(min = 10)
        String description,

        @NotNull
        String image,

        @NotNull
        @Positive
        float caloriesPerUnit,

        @NotBlank
        String metricUnit,

        @NotNull
        boolean isCustom
) {
}
