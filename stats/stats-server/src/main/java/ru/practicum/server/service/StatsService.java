package ru.practicum.server.service;

import ru.practicum.model.ViewStats;
import ru.practicum.server.model.EndpointHit;

import java.util.List;

public interface StatsService {

    EndpointHit hit(EndpointHit endpointHit);

    List<ViewStats> stats(String start,
                          String end,
                          List<String> uris,
                          boolean unique
    );
}
