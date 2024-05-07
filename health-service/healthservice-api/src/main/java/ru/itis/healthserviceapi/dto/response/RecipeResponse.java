package ru.itis.healthserviceapi.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description = "Модель для получения Recipe")
public record RecipeResponse(

        // TODO: автор - дто юзера
        @ApiModelProperty(value = "автор") String author,

        @ApiModelProperty(value = "название") String title,

        @ApiModelProperty(value = "категория") String category,

        // TODO: сделать список дто еды, как ингредиенты
        @ApiModelProperty(value = "ингредиенты") List<String> ingredients,

        @ApiModelProperty(value = "инструкции") List<String> instructions,

        @ApiModelProperty(value = "изображения") List<String> images,

        @ApiModelProperty(value = "кбжу") NutritionalInfoResponse nutritionalInfo
) {
}
