package ru.itis.healthserviceimpl.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.healthserviceapi.dto.request.FoodCategoryRequest;
import ru.itis.healthserviceapi.dto.response.FoodCategoryResponse;
import ru.itis.healthserviceimpl.model.FoodCategory;

@Mapper(componentModel = "spring")
public interface FoodCategoryMapper {

    @Mapping(target = "id", ignore = true)
    FoodCategory toEntity(FoodCategoryRequest foodCategoryRequest);

    FoodCategoryResponse toResponse(FoodCategory foodCategoryEntity);

}
