package ru.practicum.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EndpointHit {

    int id;

    String app;

    String uri;

    String ip;

    LocalDateTime timestamp;
}
