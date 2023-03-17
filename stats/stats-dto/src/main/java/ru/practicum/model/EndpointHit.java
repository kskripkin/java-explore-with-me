package ru.practicum.model;

import lombok.Data;

@Data
public class EndpointHit {

    int id;

    String app;

    String uri;

    String ip;

    String timestamp;
}
