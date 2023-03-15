package ru.practicum.server.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.model.ViewStats;
import ru.practicum.server.dao.EndpointHitRepository;
import ru.practicum.server.model.EndpointHit;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        List<ViewStats> list = new ArrayList<>();
        if (uris.length == 0) {
            if (unique) {
                return endpointHitRepository.getAllUnique(LocalDateTime.parse(start), LocalDateTime.parse(end));
            } else {
                return endpointHitRepository.getAllNonUnique(LocalDateTime.parse(start), LocalDateTime.parse(end));
            }
        }
        for (int i = 0; i < uris.length; i++) {
            if (unique) {
                list.add(endpointHitRepository.getOneUnique(LocalDateTime.parse(start), LocalDateTime.parse(end), uris[i]));
            } else {
                list.add(endpointHitRepository.getOneNonUnique(LocalDateTime.parse(start), LocalDateTime.parse(end), uris[i]));
            }
        }
        return list;
    }
}
