package ru.itis.healthserviceapi.dto.request;

public record UserSave(String username,
                       String password,
                       String firstname,
                       String lastname,
                       String sex,
                       int age,
                       int weight,
                       int height,
                       String activityCoefficient,
                       String role) {
}
