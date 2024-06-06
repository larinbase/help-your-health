package ru.itis.healthserviceapi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record UserSave(

        @NotBlank
        String username,

        @NotBlank
        @Size(min = 5)
        String password,

        @NotBlank
        String firstname,

        @NotBlank
        String lastname,

        @NotBlank
        String sex,

        @NotNull
        @Positive
        int age,

        @NotNull
        @Positive
        int weight,

        @NotNull
        @Positive
        int height,

        @NotBlank
        String activityCoefficient
){
}
