package ru.itis.healthserviceimpl.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ru.itis.healthserviceimpl.model.roles.RecipeRoleType;

import java.util.UUID;

@Converter(autoApply = false)
public class UuidConvertor implements AttributeConverter<UUID, String> {
    @Override
    public String convertToDatabaseColumn(UUID uuid) {
        return uuid.toString();
    }

    @Override
    public UUID convertToEntityAttribute(String string) {
        if (string == null) {
            return null;
        }
        return UUID.fromString(string);
    }
}