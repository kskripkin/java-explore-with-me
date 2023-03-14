package ru.practicum.server.service;

import ru.practicum.model.EndpointHit;
import ru.practicum.model.ViewStats;

public interface StatsService {

    EndpointHit hit(EndpointHit endpointHit);

    ViewStats stats(String start,
                    String end,
                    String[] uris,
                    boolean unique
    );
}
