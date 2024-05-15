package ru.itis.healthserviceapi.dto.response;

public record UserResponse(String username,
                           String firstname,
                           String lastname,
                           int age,
                           int weight,
                           int height,
                           int calorieAllowance,
                           int waterNorm) {
}
