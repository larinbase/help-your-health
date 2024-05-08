package ru.itis.healthserviceapi.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Модель для создания Ingredient")
public record IngredientRequest(
        @ApiModelProperty(value = "название") String name,

        @ApiModelProperty(value = "вес") float amount,

        @ApiModelProperty(value = "единица измерения") String unit
) {
}
