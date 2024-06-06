package ru.itis.healthserviceapi.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.healthserviceapi.dto.request.DayStatsRequest;
import ru.itis.healthserviceapi.dto.response.DateStatsResponse;

@Tag(name = "stats")
@Schema(description = "Статистика")
@RequestMapping("/api/v1/stats")
public interface StatsApi {

    @Operation(summary = "получение дневной статистики")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Статистика собрана"),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации"),
            @ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
            @ApiResponse(responseCode = "500", description = "Ведутся технические работы")
    })
    @GetMapping("/day")
    DateStatsResponse getDateStats(DayStatsRequest dayStatsRequest);
    
}
