package ru.itis.healthserviceapi.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itis.healthserviceapi.dto.request.EatenFoodRequest;
import ru.itis.healthserviceapi.dto.response.EatenFoodResponse;


import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/eaten-foods")
public interface EatenFoodApi {

    @Operation(summary = "Создание съеденного продукта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Съеденный продукт создан",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UUID.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав пользователя"),
            @ApiResponse(responseCode = "500", description = "Серверная ошибка")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    UUID save(@RequestBody EatenFoodRequest eatenFoodRequest);

    @Operation(summary = "Получение съеденного продукта по Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Съеденный продукт найден",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = EatenFoodResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав пользователя"),
            @ApiResponse(responseCode = "404", description = "Съеденный продукт с данным Id не найден"),
            @ApiResponse(responseCode = "500", description = "Серверная ошибка")
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    EatenFoodResponse getById(@PathVariable("id") UUID uuid);

    @Operation(summary = "Получение набора всех съеденных продуктов")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Набор съеденных продуктов успешно предоставлен",
                    content = {@Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = EatenFoodResponse.class)))}),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав пользователя"),
            @ApiResponse(responseCode = "500", description = "Серверная ошибка")
    })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    Set<EatenFoodResponse> getAll();

    @Operation(summary = "Удаление съеденного продукта по Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Съеденный продукт успешно удален"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав пользователя"),
            @ApiResponse(responseCode = "404", description = "Съеденный продукт с данным Id не найден"),
            @ApiResponse(responseCode = "500", description = "Серверная ошибка")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable("id") UUID uuid);

    @Operation(summary = "Полное обновление съеденного продукта по Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Съеденный продукт успешно обновлен"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "403", description = "Недостаточно прав пользователя"),
            @ApiResponse(responseCode = "404", description = "Съеденный продукт не найден"),
            @ApiResponse(responseCode = "500", description = "Серверная ошибка")
    })
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void putById(@PathVariable("id") UUID uuid, @RequestBody EatenFoodRequest eatenFoodRequest);
}
