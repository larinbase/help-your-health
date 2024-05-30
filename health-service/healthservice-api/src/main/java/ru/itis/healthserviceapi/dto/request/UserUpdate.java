package ru.itis.healthserviceapi.dto.request;

public record UserUpdate(String firstname,
                         String lastname,
                         int age,
                         String sex,
                         int weight,
                         int height,
                         String activityCoefficient) {
}
