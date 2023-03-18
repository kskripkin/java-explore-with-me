package ru.practicum.server.service;

import ru.practicum.server.model.EndpointHit;
import ru.practicum.server.model.ViewStats;

import java.util.Collection;

public interface StatsService {

    EndpointHit hit(EndpointHit endpointHit);

    Collection<ViewStats> stats(String start,
                                String end,
                                String[] uris,
                                boolean unique
    );
}
