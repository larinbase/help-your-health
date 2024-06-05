package ru.itis.healthserviceapi.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Date;
import java.util.UUID;

public record ExerciseSessionRequest(

        @NotNull
        UUID templateId,

        @NotNull
        @Positive
        float metricAmount,

        @NotNull
        Date date
) {
}
