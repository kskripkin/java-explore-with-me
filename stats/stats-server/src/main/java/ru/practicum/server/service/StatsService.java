package ru.practicum.server.service;

import ru.practicum.model.ViewStats;
import ru.practicum.server.model.EndpointHit;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface StatsService {

    EndpointHit hit(EndpointHit endpointHit);

    Collection<ViewStats> stats(LocalDateTime start,
                                LocalDateTime end,
                                String[] uris,
                                boolean unique
    );
}
