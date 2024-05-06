package ru.itis.healthserviceapi.api.exercises;

import java.util.Date;
import java.util.UUID;

public record ExerciseSessionsRequest(
        UUID exerciseTemplateId,
        float metricAmount,
        Date date
) {
}
