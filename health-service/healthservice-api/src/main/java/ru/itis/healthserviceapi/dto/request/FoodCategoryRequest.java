package ru.itis.healthserviceapi.dto.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Модель для создания Food-Category-request")
public record FoodCategoryRequest(

        @Schema(description = "Название")
        @NotBlank
        String name
) {
}
