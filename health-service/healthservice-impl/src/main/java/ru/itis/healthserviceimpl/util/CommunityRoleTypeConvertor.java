package ru.itis.healthserviceimpl.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ru.itis.healthserviceimpl.model.roles.CommunityRoleType;

@Converter(autoApply = true)
public class CommunityRoleTypeConvertor implements AttributeConverter<CommunityRoleType, String> {
    @Override
    public String convertToDatabaseColumn(CommunityRoleType communityRoleType) {
        if (communityRoleType == null) {
            return null;
        }
        return communityRoleType.name();
    }

    @Override
    public CommunityRoleType convertToEntityAttribute(String string) {
        if (string == null) {
            return null;
        }
        return CommunityRoleType.valueOf(string);
    }
}
