package ru.itis.healthserviceapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record UserUpdate(

        @NotBlank
        String firstname,

        @NotBlank
        String lastname,

        @NotNull
        @Positive
        int age,

        @NotBlank
        String sex,

        @NotNull
        @Positive
        int weight,

        @NotNull
        @Positive
        int height,

        @NotBlank
        String activityCoefficient) {
}
