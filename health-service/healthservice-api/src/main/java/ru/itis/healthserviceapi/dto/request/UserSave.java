package ru.itis.healthserviceapi.dto.request;

public record UserSave(String username,
                       String firstname,
                       String lastname,
                       String sex,
                       int age,
                       int weight,
                       int height,
                       String activityCoefficient) {
}
