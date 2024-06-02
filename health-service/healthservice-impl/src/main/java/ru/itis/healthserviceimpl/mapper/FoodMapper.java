package ru.itis.healthserviceimpl.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.healthserviceapi.dto.request.FoodRequest;
import ru.itis.healthserviceapi.dto.response.FoodResponse;
import ru.itis.healthserviceimpl.model.Food;



@Mapper(componentModel = "spring")
public interface FoodMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "food", ignore = true)
    Food toEntity(FoodRequest foodRequest);

    FoodResponse toResponse(Food foodEntity);

}
