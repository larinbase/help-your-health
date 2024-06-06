package ru.itis.healthserviceapi.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itis.healthserviceapi.dto.request.ExerciseTemplateRequest;
import ru.itis.healthserviceapi.dto.response.ExerciseTemplateResponse;

import java.util.List;
import java.util.UUID;

@Tag(name = "Exercise templates")
@Schema(description = "Упражнения")
@RequestMapping("/api/v1/exercises/templates")
public interface ExerciseTemplateApi {

    @GetMapping
    @Operation(summary = "Получение всех упражнений")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "упражнения получены"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Не достаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @ResponseStatus(HttpStatus.OK)
    Page<ExerciseTemplateResponse> getTemplates(Pageable pageable);

    @GetMapping(params = {"q"})
    @Operation(summary = "Поиск всех упражнений")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "упражнения получены"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Не достаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @ResponseStatus(HttpStatus.OK)
    List<ExerciseTemplateResponse> searchTemplates(@RequestParam("q") String query);

    @PostMapping()
    @Operation(summary = "Создание упражнение")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "упражнение создано"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Не достаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @ResponseStatus(HttpStatus.CREATED)
    UUID createTemplate(@RequestBody ExerciseTemplateRequest templateRequest);

    @PutMapping("/{templateId}")
    @Operation(summary = "Обновление упражнения")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "упражнение обновлено"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Не достаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @ResponseStatus(HttpStatus.OK)
    void updateTemplate(@PathVariable("templateId") UUID templateId, @RequestBody  ExerciseTemplateRequest templateRequest);

    @DeleteMapping("/{templateId}")
    @Operation(summary = "Удаление упражнения")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "упражнение удалено"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "403", description = "Не достаточно прав"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @ResponseStatus(HttpStatus.OK)
    void deleteTemplate(@PathVariable("templateId")  UUID templateId);
}
