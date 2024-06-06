package ru.itis.healthserviceimpl.security.dto.response;

public record JwtTokenPayloadResponse(String username,
                                      boolean isExpired) {
}
