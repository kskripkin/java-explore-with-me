package ru.practicum.server.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewStats {

    String app;

    String uri;

    long hits;
}
