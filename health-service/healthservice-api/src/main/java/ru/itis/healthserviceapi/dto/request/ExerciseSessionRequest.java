package ru.itis.healthserviceapi.dto.request;

import java.util.Date;
import java.util.UUID;

public record ExerciseSessionRequest(
        UUID exerciseTemplateId,
        float metricAmount,
        Date date
) {
}
