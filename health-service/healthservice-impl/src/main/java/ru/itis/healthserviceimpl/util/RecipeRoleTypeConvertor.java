package ru.itis.healthserviceimpl.util;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import ru.itis.healthserviceimpl.model.roles.RecipeRoleType;

@Converter(autoApply = true)
public class RecipeRoleTypeConvertor implements AttributeConverter<RecipeRoleType, String> {
    @Override
    public String convertToDatabaseColumn(RecipeRoleType recipeRoleType) {
        if (recipeRoleType == null) {
            return null;
        }
        return recipeRoleType.name();
    }

    @Override
    public RecipeRoleType convertToEntityAttribute(String string) {
        if (string == null) {
            return null;
        }
        return RecipeRoleType.valueOf(string); //ToDo: тут может выкидываться эксепшн, мб нужно отработать
    }
}
