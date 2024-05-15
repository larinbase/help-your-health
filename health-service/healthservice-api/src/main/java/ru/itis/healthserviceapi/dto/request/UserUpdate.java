package ru.itis.healthserviceapi.dto.request;

public record UserUpdate(String firstname,
                         String lastname,
                         int age,
                         int weight,
                         int height) {
}
