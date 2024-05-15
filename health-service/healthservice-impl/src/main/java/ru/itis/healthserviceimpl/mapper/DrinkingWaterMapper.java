package ru.itis.healthserviceimpl.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.healthserviceapi.dto.request.DrinkingWaterRequest;
import ru.itis.healthserviceapi.dto.response.DrinkingWaterResponse;
import ru.itis.healthserviceimpl.model.DrinkingWater;

@Mapper(componentModel = "spring")
public interface DrinkingWaterMapper {

    @Mapping(target = "id", ignore = true)
    DrinkingWaterResponse toResponse(DrinkingWater drinkingWater);

    DrinkingWater toEntity(DrinkingWaterRequest request);
}
