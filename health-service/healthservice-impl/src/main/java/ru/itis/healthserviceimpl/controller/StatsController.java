package ru.itis.healthserviceimpl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.healthserviceapi.api.StatsApi;
import ru.itis.healthserviceapi.dto.request.DayStatsRequest;
import ru.itis.healthserviceapi.dto.response.DateStatsResponse;
import ru.itis.healthserviceimpl.service.StatsService;

@RestController
@RequiredArgsConstructor
public class StatsController implements StatsApi {

    private final StatsService statsService;

    @Override
    public DateStatsResponse getDateStats(DayStatsRequest dayStatsRequest) {
        return statsService.getDateStats(dayStatsRequest);
    }
}
