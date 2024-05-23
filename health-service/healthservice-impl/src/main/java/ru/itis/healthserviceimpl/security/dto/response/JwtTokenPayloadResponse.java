package ru.itis.healthserviceimpl.security.dto.response;

import ru.itis.healthserviceimpl.model.roles.CommunityRoleType;

public record JwtTokenPayloadResponse(String username,
                                      CommunityRoleType roleType,
                                      boolean isExpired) {
}
