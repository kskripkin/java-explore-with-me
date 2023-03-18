package ru.practicum.model;

import lombok.*;

@Data
@NoArgsConstructor
public class ViewStats {

    String app;

    String uri;

    long hits;

    public ViewStats(String app, String uri, long hits) {
        this.app = app;
        this.uri = uri;
        this.hits = hits;
    }
}