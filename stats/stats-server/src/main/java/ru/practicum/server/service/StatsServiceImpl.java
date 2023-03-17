package ru.practicum.server.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.server.dao.EndpointHitRepository;
import ru.practicum.server.model.EndpointHit;
import ru.practicum.model.ViewStats;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@Slf4j
@RequiredArgsConstructor
@Service
public class StatsServiceImpl implements StatsService {

    private final EndpointHitRepository endpointHitRepository;

    @Override
    public EndpointHit hit(EndpointHit endpointHit) {
        return endpointHitRepository.save(endpointHit);
    }

    @Override
    public Collection<ViewStats> stats(String start, String end, String[] uris, boolean unique) {
        ArrayList<String> listUris = new ArrayList<>(Arrays.asList(uris));
        System.out.println(LocalDateTime.parse(start, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        if (listUris.isEmpty()) {
            if (unique) {
                return endpointHitRepository.getAllUniqueIp(start, end);
            } else {
                return endpointHitRepository.getAll(start, end);
            }
        } else {
            if (unique) {
                return endpointHitRepository.getAllUniqueIp(start, end, listUris);
            } else {
                return endpointHitRepository.getAll(start, end, listUris);
            }
        }
    }
}
