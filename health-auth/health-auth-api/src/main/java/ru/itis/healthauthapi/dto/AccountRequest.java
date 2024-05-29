package ru.itis.healthauthapi.dto;

import java.util.List;

public record AccountRequest(String subject, List<Role> roles) {
}
