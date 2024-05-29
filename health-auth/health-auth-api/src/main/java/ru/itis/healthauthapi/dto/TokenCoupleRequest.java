package ru.itis.healthauthapi.dto;

public record TokenCoupleRequest(String accessToken, String refreshToken) {
}
