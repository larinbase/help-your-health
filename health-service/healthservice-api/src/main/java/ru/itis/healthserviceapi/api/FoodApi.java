package ru.itis.healthserviceapi.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itis.healthserviceapi.dto.request.FoodRequest;
import ru.itis.healthserviceapi.dto.response.FoodResponse;

import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/foods")
public interface FoodApi {

    @Operation(summary = "Создание продукта питания")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Продукт питания создан",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UUID.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав пользователя"),
            @ApiResponse(responseCode = "500", description = "Серверная ошибка")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UUID save(@RequestBody FoodRequest foodRequest);

    @Operation(summary = "Получение продукта питания по Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Продукт питания найден",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FoodResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав пользователя"),
            @ApiResponse(responseCode = "404", description = "Продукт питания с данным Id не найден"),
            @ApiResponse(responseCode = "500", description = "Серверная ошибка")
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    FoodResponse getById(@PathVariable("id") UUID uuid);

    @Operation(summary = "Получение набора всех продуктов питания")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Набор продуктов питания успешно предоставлен",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = FoodResponse.class)))}),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав пользователя"),
            @ApiResponse(responseCode = "500", description = "Серверная ошибка")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Set<FoodResponse> getAll();

    @Operation(summary = "Удаление продукта питания по Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Продукт питания успешно удален"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав пользователя"),
            @ApiResponse(responseCode = "404", description = "Продукт питания с данным Id не найден"),
            @ApiResponse(responseCode = "500", description = "Серверная ошибка")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable("id") UUID uuid);

    @Operation(summary = "Полное обновление продукта питания по Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Продукт питания успешно обновлен"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав пользователя"),
            @ApiResponse(responseCode = "404", description = "Продукт питания с данным Id не найден"),
            @ApiResponse(responseCode = "500", description = "Серверная ошибка")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void putById(@PathVariable("id") UUID uuid, @RequestBody FoodRequest foodRequest);
}
