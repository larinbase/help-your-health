package ru.itis.healthserviceapi.dto.request;

public record UserSave(String username,
                       String firstname,
                       String lastname,
                       int age,
                       int weight,
                       int height) {
}
