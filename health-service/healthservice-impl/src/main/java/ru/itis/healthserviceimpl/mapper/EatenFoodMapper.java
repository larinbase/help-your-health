package ru.itis.healthserviceimpl.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.healthserviceapi.dto.request.EatenFoodRequest;
import ru.itis.healthserviceapi.dto.response.EatenFoodResponse;
import ru.itis.healthserviceimpl.model.EatenFood;

@Mapper(componentModel = "spring")
public interface EatenFoodMapper {

    @Mapping(target = "id", ignore = true)
    EatenFood toEntity(EatenFoodRequest eatenFoodRequest);

    EatenFoodResponse toResponse(EatenFood eatenFoodEntity);

}
