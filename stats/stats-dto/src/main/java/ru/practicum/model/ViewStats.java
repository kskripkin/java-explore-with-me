package ru.practicum.model;

import lombok.*;

@Data
@AllArgsConstructor
public class ViewStats {

    String app;

    String uri;

    long hits;
}