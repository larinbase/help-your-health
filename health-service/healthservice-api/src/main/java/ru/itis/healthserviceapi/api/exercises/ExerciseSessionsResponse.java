package ru.itis.healthserviceapi.api.exercises;

import java.util.Date;
import java.util.UUID;

public record ExerciseSessionsResponse(
        UUID id,
        UUID userId,
        ExerciseTemplateResponse exerciseTemplate,
        float metricAmount,
        Date date
) {
}
