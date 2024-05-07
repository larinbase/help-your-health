package ru.itis.healthserviceapi.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Модель для получения NutritionalInfo")
public record NutritionalInfoResponse(
        @ApiModelProperty(value = "калории") int calories,

        @ApiModelProperty(value = "белки") int protein,

        @ApiModelProperty(value = "жиры") int fat,

        @ApiModelProperty(value = "углеводы") int carbohydrates
) {
}
