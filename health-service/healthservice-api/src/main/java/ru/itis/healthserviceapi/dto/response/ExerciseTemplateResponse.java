package ru.itis.healthserviceapi.dto.response;

import java.util.UUID;

public record ExerciseTemplateResponse(
        UUID id,
        String description,
        String image,
        float caloriesPerUnit,
        String metricUnit,
        boolean isCustom
) {
}
