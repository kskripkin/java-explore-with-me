package ru.practicum.server.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.model.EndpointHit;
import ru.practicum.model.ViewStats;
import ru.practicum.server.dao.StatsDaoEndpointHit;
import ru.practicum.server.dao.StatsDaoViewStats;

@Slf4j
@RequiredArgsConstructor
@Service
public class StatsServiceImpl implements StatsService{

    private final StatsDaoEndpointHit statsDaoEndpointHit;
    private final StatsDaoViewStats statsDaoViewStats;

    @Override
    public EndpointHit hit(EndpointHit endpointHit) {
        return statsDaoEndpointHit.save(endpointHit);
    }

    @Override
    public ViewStats stats(String start, String end, String[] uris, boolean unique) {
        return statsDaoViewStats.getStats(start, end, uris, unique);
    }
}
