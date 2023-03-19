package ru.practicum.server.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.server.dao.EndpointHitRepository;
import ru.practicum.model.ViewStats;
import ru.practicum.server.model.EndpointHit;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class StatsServiceImpl implements StatsService {

    private final EndpointHitRepository endpointHitRepository;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Override
    public EndpointHit hit(EndpointHit endpointHit) {
        return endpointHitRepository.save(endpointHit);
    }

    @Override
    public List<ViewStats> stats(String start, String end, String[] uris, boolean unique) {
        ArrayList<String> listUris = new ArrayList<>(Arrays.asList(uris));
        LocalDateTime startTime = LocalDateTime.parse(start, formatter);
        LocalDateTime endTime = LocalDateTime.parse(end, formatter);
        if (listUris.isEmpty()) {
            if (unique) {
                return endpointHitRepository.getAllUniqueIp(startTime, endTime);
            } else {
                return endpointHitRepository.getAll(startTime, endTime);
            }
        } else {
            if (unique) {
                return endpointHitRepository.getAllUniqueIp(startTime, endTime, listUris);
            } else {
                return endpointHitRepository.getAll(startTime, endTime, listUris);
            }
        }
    }
}
