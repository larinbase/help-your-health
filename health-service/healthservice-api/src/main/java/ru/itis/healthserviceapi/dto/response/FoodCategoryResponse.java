package ru.itis.healthserviceapi.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.UUID;

@Schema(description = "Модель для создания Food-Category-Response")
public record FoodCategoryResponse(@Schema(description = "Id") UUID id,
								   @Schema(description = "Дата создания") Instant createdDate,
								   @Schema(description = "Дата обновления") Instant updatedDate,
                                   @Schema(description = "Название") String name) {
}
