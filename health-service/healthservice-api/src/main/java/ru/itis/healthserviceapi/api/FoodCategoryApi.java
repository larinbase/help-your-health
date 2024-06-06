package ru.itis.healthserviceapi.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itis.healthserviceapi.dto.request.FoodCategoryRequest;
import ru.itis.healthserviceapi.dto.response.FoodCategoryResponse;


import java.util.Set;
import java.util.UUID;

@RestController
@Tag(name = "Food categories")
@RequestMapping("/api/v1/food-categories")
public interface FoodCategoryApi {

    @Operation(summary = "Создание категории продуктов питания")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Категория продуктов питания создана",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UUID.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав пользователя"),
            @ApiResponse(responseCode = "500", description = "Серверная ошибка")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UUID save(@RequestBody FoodCategoryRequest foodCategoryRequest);

    @Operation(summary = "Получение категории продукта питания по Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Категория продукта питания найдена",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FoodCategoryResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав пользователя"),
            @ApiResponse(responseCode = "404", description = "Категория продукта питания с данным Id не найдена"),
            @ApiResponse(responseCode = "500", description = "Серверная ошибка")
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    FoodCategoryResponse getById(@PathVariable("id") UUID uuid);

    @Operation(summary = "Получение набора всех категорий продуктов питания")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Набор категорий продуктов питания успешно предоставлен",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = FoodCategoryResponse.class)))}),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав пользователя"),
            @ApiResponse(responseCode = "500", description = "Серверная ошибка")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Set<FoodCategoryResponse> getAll();

    @Operation(summary = "Удаление категории продукта питания по Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Категория продукта питания успешно удалена"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав пользователя"),
            @ApiResponse(responseCode = "404", description = "Категория продукта питания с данным Id не найдена"),
            @ApiResponse(responseCode = "500", description = "Серверная ошибка")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable("id") UUID uuid);

    @Operation(summary = "Полное обновление категории продукта питания по Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Категория продукта питания успешно обновлена"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав пользователя"),
            @ApiResponse(responseCode = "404", description = "Категория продукта питания с данным Id не найдена"),
            @ApiResponse(responseCode = "500", description = "Серверная ошибка")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void putById(@PathVariable("id") UUID uuid, @RequestBody FoodCategoryRequest foodCategoryRequest);
}
