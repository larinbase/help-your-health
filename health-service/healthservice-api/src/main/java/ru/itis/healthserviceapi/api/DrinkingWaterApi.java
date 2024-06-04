package ru.itis.healthserviceapi.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itis.healthserviceapi.dto.request.DrinkingWaterRequest;
import ru.itis.healthserviceapi.dto.response.DrinkingWaterResponse;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Tag(name = "drinking-water-api")
@RequestMapping("api/v1/drinking-water")
public interface DrinkingWaterApi {

    @Operation(summary = "Получение выпитой воды по id", method = "get-drinking-water-by-id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно получено"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Не достаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    DrinkingWaterResponse getDrinkingWaterById(@PathVariable UUID id);

    @Operation(summary = "Получение последней выпитой пользователем воды по id пользователя", method = "get-last-drinking-water-by-user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно получено"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Не достаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @GetMapping("/user/{userId}/last")
    @ResponseStatus(HttpStatus.OK)
    DrinkingWaterResponse getLastDrinkingWaterByUser(@PathVariable UUID userId);

    @Operation(summary = "Получение всей выпитой пользователем воды по id пользователя", method = "get-all-drinking-water-by-user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно получено"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Не достаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @GetMapping("/user/{userId}/all")
    @ResponseStatus(HttpStatus.OK)
    List<DrinkingWaterResponse> getAllDrinkingWaterByUser(@PathVariable("userId") UUID userId);

    @Operation(summary = "Сохранение выпитой воды", method = "save-drinking-water")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Успешно сохранено"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Не достаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    void saveDrinkingWater(@RequestBody DrinkingWaterRequest request);

    @Operation(summary = "Удалений выпитой воды", method = "delete-drinking-water")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно удалено"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Не достаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    void deleteDrinkingWater(UUID id);
    @Operation(summary = "Получение выпитой воды за определеннный период", method = "get-drinking-water-for-time-period")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Успешно получено"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Не достаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @GetMapping("/user/{userId}/time-period")
    @ResponseStatus(HttpStatus.OK)
    List<DrinkingWaterResponse> getDrinkingWaterForTimePeriod(@PathVariable("userId") UUID userId, @RequestParam(value = "from") Instant from, @RequestParam("to") Instant to);
}
