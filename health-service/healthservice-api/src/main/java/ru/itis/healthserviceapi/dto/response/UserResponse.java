package ru.itis.healthserviceapi.dto.response;

public record UserResponse(String username,
                           String password,
                           String firstname,
                           String lastname,
                           String sex,
                           int age,
                           int weight,
                           int height,
                           int calorieAllowance,
                           int proteins,
                           int fats,
                           int carbohydrates,
                           int waterNorm,
                           String activityCoefficient) {
}
