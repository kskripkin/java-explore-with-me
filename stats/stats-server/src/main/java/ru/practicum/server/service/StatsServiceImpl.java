package ru.practicum.server.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.practicum.server.dao.EndpointHitRepository;
import ru.practicum.server.model.EndpointHit;
import ru.practicum.server.model.ViewStats;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public Collection<ViewStats> stats(LocalDateTime start, LocalDateTime end, String[] uris, boolean unique) {
        ArrayList<String> listUris = new ArrayList<>(Arrays.asList(uris));
        if (listUris.isEmpty()) {
            return null;
//            endpointHitRepository.getAll(start, end).stream()
//                    .map(this::mapperEndpointHitToViewStat)
//                    .collect(Collectors.toList());
        } else {
//            return endpointHitRepository.getAll(start, end, listUris).stream()
//                    .map(this::mapperEndpointHitToViewStat)
//                    .collect(Collectors.toList());
            return endpointHitRepository.getAll(start, end, listUris);
        }
//        if (uris.length == 0) {
//            if (unique) {
//                return endpointHitRepository.getAllUnique(LocalDateTime.parse(start), LocalDateTime.parse(end));
//            } else {
//                return endpointHitRepository.getAllNonUnique(LocalDateTime.parse(start), LocalDateTime.parse(end));
//            }
//        }
//        for (int i = 0; i < uris.length; i++) {
//            if (unique) {
//                list.add(endpointHitRepository.getOneUnique(LocalDateTime.parse(start), LocalDateTime.parse(end), uris[i]));
//            } else {
//                list.add(endpointHitRepository.getOneNonUnique(LocalDateTime.parse(start), LocalDateTime.parse(end), uris[i]));
//            }
//        }
    }

//    private ViewStats mapperEndpointHitToViewStat(EndpointHit endpointHit) {
//        return new ViewStats(endpointHit.getApp(),
//                                endpointHit.getUri()
//                            );
//    }
}
