package ru.practicum.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewStats {

    String app;

    String uri;

    long hits;
}