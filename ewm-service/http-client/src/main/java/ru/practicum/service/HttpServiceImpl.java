package ru.practicum.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.practicum.client.StatsClient;
import ru.practicum.model.EndpointHit;

@Service
@RequiredArgsConstructor
public class HttpServiceImpl implements HttpService{

    private final StatsClient statsClient;

    @Override
    public ResponseEntity<Object> hit(EndpointHit endpointHit) {
        return statsClient.hit(endpointHit);
    }

    @Override
    public ResponseEntity<Object> stats(String start, String end, String[] uris, boolean unique) {
        return statsClient.stats(start, end, uris, unique);
    }
}
