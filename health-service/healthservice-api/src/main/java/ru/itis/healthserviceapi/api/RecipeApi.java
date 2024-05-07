package ru.itis.healthserviceapi.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.web.bind.annotation.*;
import ru.itis.healthserviceapi.dto.request.RecipeRequest;
import ru.itis.healthserviceapi.dto.response.RecipeResponse;

import java.util.List;
import java.util.UUID;

@Api("RecipeApi")
@RequestMapping("api/v1/recipe")
public interface RecipeApi {

    @ApiOperation(value = "Создание рецепта", nickname = "create")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Рецепт создан"),
            @ApiResponse(code = 400, message = "Ошибка валидации"),
            @ApiResponse(code = 401, message = "Не пройдена авторизация"),
            @ApiResponse(code = 403, message = "Не достаточно прав"),
            @ApiResponse(code = 500, message = "Ведутся технические работы")
    })
    @PostMapping
    void create(@RequestBody RecipeRequest request);

    @ApiOperation(value = "Получение всех рецептов", nickname = "find-all")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Рецепты получены", response = List.class),
            @ApiResponse(code = 400, message = "Ошибка валидации"),
            @ApiResponse(code = 401, message = "Не пройдена авторизация"),
            @ApiResponse(code = 403, message = "Не достаточно прав"),
            @ApiResponse(code = 500, message = "Ведутся технические работы")
    })
    @GetMapping
    List<RecipeResponse> findAll();

    @ApiOperation(value = "Получение рецепта по id", nickname = "find-by-id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Рецепт получен", response = RecipeResponse.class),
            @ApiResponse(code = 400, message = "Ошибка валидации"),
            @ApiResponse(code = 401, message = "Не пройдена авторизация"),
            @ApiResponse(code = 403, message = "Не достаточно прав"),
            @ApiResponse(code = 500, message = "Ведутся технические работы")
    })
    @GetMapping("/{id}")
    RecipeResponse findById(@PathVariable("id") UUID id);

    @ApiOperation(value = "Получение рецепта по названию", nickname = "find-by-title")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Рецепт получен", response = RecipeResponse.class),
            @ApiResponse(code = 400, message = "Ошибка валидации"),
            @ApiResponse(code = 401, message = "Не пройдена авторизация"),
            @ApiResponse(code = 403, message = "Не достаточно прав"),
            @ApiResponse(code = 500, message = "Ведутся технические работы")
    })
    @GetMapping("/{title}")
    RecipeResponse findByTitle(@PathVariable("title") String title);

    @ApiOperation(value = "Обновление рецепта", nickname = "update")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Рецепт обновлен"),
            @ApiResponse(code = 400, message = "Ошибка валидации"),
            @ApiResponse(code = 401, message = "Не пройдена авторизация"),
            @ApiResponse(code = 403, message = "Не достаточно прав"),
            @ApiResponse(code = 500, message = "Ведутся технические работы")
    })
    @PutMapping
    void update(@RequestBody RecipeRequest request);

    @ApiOperation(value = "Удаление рецепта по id", nickname = "delete-by-id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Рецепт удален"),
            @ApiResponse(code = 400, message = "Ошибка валидации"),
            @ApiResponse(code = 401, message = "Не пройдена авторизация"),
            @ApiResponse(code = 403, message = "Не достаточно прав"),
            @ApiResponse(code = 500, message = "Ведутся технические работы")
    })
    @DeleteMapping("/{id}")
    void deleteById(@PathVariable("id") UUID id);
}
