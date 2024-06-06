package ru.itis.healthserviceimpl.mapper;

import org.mapstruct.Mapper;
import ru.itis.healthserviceapi.dto.request.IngredientRequest;
import ru.itis.healthserviceapi.dto.response.IngredientResponse;
import ru.itis.healthserviceimpl.model.additionalinfo.Ingredient;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IngredientMapper {

    List<Ingredient> toEntity(List<IngredientRequest> requests);

    List<IngredientResponse> toResponse(List<Ingredient> ingredients);
}
