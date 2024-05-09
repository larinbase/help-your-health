package ru.itis.healthserviceapi.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.bson.types.ObjectId;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import ru.itis.healthserviceapi.dto.request.RecipeRequest;
import ru.itis.healthserviceapi.dto.response.RecipeResponse;

import java.util.List;

@Api("RecipeApi")
@RequestMapping("/api/v1/recipe")
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
    Page<RecipeResponse> findAll(@RequestParam(defaultValue = "0") int offset,
                                 @RequestParam(defaultValue = "10") int limit);

    @ApiOperation(value = "Получение рецепта по id", nickname = "find-by-id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Рецепт получен", response = RecipeResponse.class),
            @ApiResponse(code = 400, message = "Ошибка валидации"),
            @ApiResponse(code = 401, message = "Не пройдена авторизация"),
            @ApiResponse(code = 403, message = "Не достаточно прав"),
            @ApiResponse(code = 500, message = "Ведутся технические работы")
    })
    @GetMapping("/findById/{id}")
    RecipeResponse findById(@PathVariable("id") ObjectId id);

    @ApiOperation(value = "Получение рецепта по названию", nickname = "find-by-title")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Рецепты получены", response = RecipeResponse.class),
            @ApiResponse(code = 400, message = "Ошибка валидации"),
            @ApiResponse(code = 401, message = "Не пройдена авторизация"),
            @ApiResponse(code = 403, message = "Не достаточно прав"),
            @ApiResponse(code = 500, message = "Ведутся технические работы")
    })
    @GetMapping("/findByTitle/{title}")
    Page<RecipeResponse> findByTitle(@PathVariable("title") String title,
                               @RequestParam(defaultValue = "0") int offset,
                               @RequestParam(defaultValue = "10") int limit);

    @ApiOperation(value = "Получение рецепта по категории", nickname = "find-by-category")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Рецепты получены", response = RecipeResponse.class),
            @ApiResponse(code = 400, message = "Ошибка валидации"),
            @ApiResponse(code = 401, message = "Не пройдена авторизация"),
            @ApiResponse(code = 403, message = "Не достаточно прав"),
            @ApiResponse(code = 500, message = "Ведутся технические работы")
    })
    @GetMapping("/findByCategory/{category}")
    Page<RecipeResponse> findByCategory(@PathVariable("category") String category,
                                        @RequestParam(defaultValue = "0") int offset,
                                        @RequestParam(defaultValue = "10") int limit);

    @ApiOperation(value = "Получение рецепта по времени готовки", nickname = "find-by-cooking-time")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Рецепты получены", response = RecipeResponse.class),
            @ApiResponse(code = 400, message = "Ошибка валидации"),
            @ApiResponse(code = 401, message = "Не пройдена авторизация"),
            @ApiResponse(code = 403, message = "Не достаточно прав"),
            @ApiResponse(code = 500, message = "Ведутся технические работы")
    })
    @GetMapping("/findByCookingTime/{cookingTime}")
    Page<RecipeResponse> findByCookingTime(@PathVariable("cookingTime") int cookingTime,
                                        @RequestParam(defaultValue = "0") int offset,
                                        @RequestParam(defaultValue = "10") int limit);

    @ApiOperation(value = "Обновление рецепта", nickname = "update")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Рецепт обновлен"),
            @ApiResponse(code = 400, message = "Ошибка валидации"),
            @ApiResponse(code = 401, message = "Не пройдена авторизация"),
            @ApiResponse(code = 403, message = "Не достаточно прав"),
            @ApiResponse(code = 500, message = "Ведутся технические работы")
    })
    @PutMapping
    void update(ObjectId id, @RequestBody RecipeRequest request);

    @ApiOperation(value = "Удаление рецепта по id", nickname = "delete-by-id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Рецепт удален"),
            @ApiResponse(code = 400, message = "Ошибка валидации"),
            @ApiResponse(code = 401, message = "Не пройдена авторизация"),
            @ApiResponse(code = 403, message = "Не достаточно прав"),
            @ApiResponse(code = 500, message = "Ведутся технические работы")
    })
    @DeleteMapping("/{id}")
    void deleteById(@PathVariable("id") ObjectId id);
}
