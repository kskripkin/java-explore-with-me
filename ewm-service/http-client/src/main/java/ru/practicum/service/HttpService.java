package ru.practicum.service;

import org.springframework.http.ResponseEntity;
import ru.practicum.model.EndpointHit;

public interface HttpService {

    ResponseEntity<Object> hit(EndpointHit endpointHit);

    ResponseEntity<Object> stats(String start,
                                 String end,
                                 String[] uris,
                                 boolean unique
    );

}
