package ru.itis.healthserviceapi.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description = "Модель для создания Recipe")
public record RecipeRequest(
        @ApiModelProperty(value = "название") String title,

        @ApiModelProperty(value = "категории") List<String> categories,

        @ApiModelProperty(value = "время готовки") int cookingTime,

        @ApiModelProperty(value = "ингредиенты") List<IngredientRequest> ingredients,

        @ApiModelProperty(value = "инструкции") List<String> instructions,

        @ApiModelProperty(value = "изображения") List<String> images,

        @ApiModelProperty(value = "кбжу") NutritionalInfoRequest nutritionalInfo

) {
}
