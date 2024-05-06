package ru.itis.healthserviceapi.api.exercises;

import java.util.UUID;

public record ExerciseTemplateResponse(
        UUID id,
        UUID metricId,
        String description,
        String image,
        float caloriesPerUnit,
        String metricUnit,
        boolean isCustom
) {
}
