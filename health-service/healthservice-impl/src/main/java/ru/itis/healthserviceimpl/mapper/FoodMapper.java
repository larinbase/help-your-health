package ru.itis.healthserviceimpl.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.itis.healthserviceapi.dto.request.FoodRequest;
import ru.itis.healthserviceapi.dto.response.FoodResponse;
import ru.itis.healthserviceimpl.model.Food;



@Mapper(componentModel = "spring", uses = {FoodCategoryMapper.class})
public interface FoodMapper {

    @Mapping(target = "id", ignore = true)
    Food toEntity(FoodRequest foodRequest);

    @Mapping(source = "category.id", target = "categoryId")
    FoodResponse toResponse(Food foodEntity);

}
