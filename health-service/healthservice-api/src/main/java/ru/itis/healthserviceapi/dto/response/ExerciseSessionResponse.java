package ru.itis.healthserviceapi.dto.response;

import java.util.Date;
import java.util.UUID;

public record ExerciseSessionResponse(
        UUID id,
        UUID userId,
        ExerciseTemplateResponse template,
        float metricAmount,
        Date date
) {
}
