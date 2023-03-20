package ru.practicum.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.model.EndpointHit;
import ru.practicum.service.HttpService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/")
public class HttpController {

    private final HttpService httpService;

    @PostMapping("/hit")
    public ResponseEntity<Object> hit(@RequestBody EndpointHit endpointHit) {
        log.info("POST /hit {}", endpointHit);
        return httpService.hit(endpointHit);
    }

    @GetMapping("/stats")
    public ResponseEntity<Object> stats(@RequestParam(name = "start") String start,
                                 @RequestParam(name = "end") String end,
                                 @RequestParam(value="uris", required = false) String[] uris,
                                 @RequestParam(name = "unique", required = false, defaultValue = "false") boolean unique
    ) {
        log.info("GET /stats?start={}&end={}&uris={}&unique={}", start, end, uris, unique);
        return httpService.stats(start, end, uris, unique);
    }
}
