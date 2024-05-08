package ru.itis.healthserviceapi.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Модель для получения Ingredient")
public record IngredientResponse(
        @ApiModelProperty(value = "название") String name,

        @ApiModelProperty(value = "вес") float amount,

        @ApiModelProperty(value = "единица измерения") String unit
) {
}
