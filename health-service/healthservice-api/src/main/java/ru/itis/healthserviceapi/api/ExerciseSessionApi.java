package ru.itis.healthserviceapi.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itis.healthserviceapi.dto.request.ExerciseSessionRequest;
import ru.itis.healthserviceapi.dto.response.ExerciseSessionResponse;

import java.util.List;
import java.util.UUID;

@Tag(name = "exerciseSessionsApi")
@Schema(description = "Выполненные упражнения")
@RequestMapping("/api/v1/exercises/sessions")
public interface ExerciseSessionApi {

    @GetMapping("/{date}")
    @Operation(summary = "Получение упражнений выполненных за день")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Выполненные упражнения получены"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Не достаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @ResponseStatus(HttpStatus.OK)
    List<ExerciseSessionResponse> getExercisesAtDay(@PathVariable String date);

    @PostMapping
    @Operation(summary = "Добавить выполненное упражнение")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Выполненное упражнение добавлено"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Не достаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @ResponseStatus(HttpStatus.OK)
    void addExercise(@RequestBody ExerciseSessionRequest request);

    @PutMapping("/{id}")
    @Operation(summary = "Обновить выполненное упражнение")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Выполненное упражнение обновлено"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Не достаточно прав"),
            @ApiResponse(responseCode = "404", description = "Выполненное упражнение не найдено"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @ResponseStatus(HttpStatus.OK)
    void updateExercise(@PathVariable UUID id, @RequestBody ExerciseSessionRequest request);

    @DeleteMapping("/{id}")
    @Operation(summary = "Удалить выполненное упражнение")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Выполненное упражнение удалено"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Не достаточно прав"),
            @ApiResponse(responseCode = "404", description = "Выполненное упражнение не найдено"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @ResponseStatus(HttpStatus.OK)
    void deleteExercise(@PathVariable UUID id);
}
