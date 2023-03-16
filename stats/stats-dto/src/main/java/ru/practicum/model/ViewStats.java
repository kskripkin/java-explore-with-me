package ru.practicum.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewStats {

    String app;

    String uri;

    int hits;

    public ViewStats(String app, String uri) {
        this.app = app;
        this.uri = uri;
    }
}
