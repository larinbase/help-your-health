package ru.itis.healthserviceapi.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.bson.types.ObjectId;

import java.util.List;

@ApiModel(description = "Модель для получения Recipe")
public record RecipeResponse(

        // TODO: автор - дто юзера
        @ApiModelProperty(value = "автор") ObjectId author,

        @ApiModelProperty(value = "название") String title,

        @ApiModelProperty(value = "категории") List<String> categories,

        @ApiModelProperty(value = "время готовки") int cookingTime,

        @ApiModelProperty(value = "ингредиенты") List<IngredientResponse> ingredients,

        @ApiModelProperty(value = "инструкции") List<String> instructions,

        @ApiModelProperty(value = "изображения") List<String> images,

        @ApiModelProperty(value = "кбжу") NutritionalInfoResponse nutritionalInfo
) {
}
