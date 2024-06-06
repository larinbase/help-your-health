package ru.itis.healthserviceapi.dto.response;


import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.UUID;

@Schema(description = "Модель для создания Eaten-Food-response")
public record EatenFoodResponse(@Schema(description = "Id") UUID id,
								@Schema(description = "Дата создания") Instant createdDate,
								@Schema(description = "Дата обновления") Instant updatedDate,
                                @Schema(description = "Id пользователя") UUID userId,
                                @Schema(description = "Id продукта питания") UUID foodId,
								@Schema(description = "Id рецепта") UUID recipeId,
								@Schema(description = "Вес") Short weight,
								@Schema(description = "Потребленные нутриенты") NutritionalInfoResponse nutrients) { }
