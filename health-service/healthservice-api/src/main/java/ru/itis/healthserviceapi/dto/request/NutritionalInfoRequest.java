package ru.itis.healthserviceapi.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Модель для создания NutritionalInfo")
public record NutritionalInfoRequest(
        @ApiModelProperty(value = "калории") int calories,

        @ApiModelProperty(value = "белки") int protein,

        @ApiModelProperty(value = "жиры") int fat,

        @ApiModelProperty(value = "углеводы") int carbohydrates
) {
}
