package ru.practicum.server.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.model.ViewStats;
import ru.practicum.server.model.EndpointHit;
import ru.practicum.server.service.StatsService;

import javax.validation.Valid;
import java.util.Collection;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/")
public class StatsController {

    private final StatsService statsService;

    @PostMapping("/hit")
    public EndpointHit hit(@RequestBody @Valid EndpointHit endpointHit) {
        log.info("POST /hit", endpointHit);
        return statsService.hit(endpointHit);
    }

    //{{baseUrl}}/stats?start=culpa elit anim&end=culpa elit anim&uris=ut aliquip&uris=Lorem in do&unique=false
    @GetMapping("/stats")
    public Collection<ViewStats> stats(@RequestParam(name = "start") String start,
                                       @RequestParam(name = "end") String end,
                                       @RequestParam(value="uris[]") String[] uris,
                                       @RequestParam(name = "unique") boolean unique
                           ) {
        log.info("GET /stats?start={}&end={}&uris={}&unique={}", start, end, uris, unique);
        return statsService.stats(start, end, uris, unique);
    }
}