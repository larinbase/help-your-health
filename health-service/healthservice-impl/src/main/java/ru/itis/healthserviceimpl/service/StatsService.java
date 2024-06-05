package ru.itis.healthserviceimpl.service;

import ru.itis.healthserviceapi.dto.request.DayStatsRequest;
import ru.itis.healthserviceapi.dto.response.DateStatsResponse;

public interface StatsService {
    DateStatsResponse getDateStats(DayStatsRequest dayStatsRequest);
}
