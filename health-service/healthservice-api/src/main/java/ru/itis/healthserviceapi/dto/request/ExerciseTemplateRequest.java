package ru.itis.healthserviceapi.dto.request;

public record ExerciseTemplateRequest(
        String description,
        String image,
        float caloriesPerUnit,
        String metricUnit,
        boolean isCustom
) {
}
