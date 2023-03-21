package ru.practicum.model.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    String annotation;

    long category;

    String description;

    LocalDateTime eventDate;

    Location location;

    long id;

    boolean paid;

    long participantLimit;

    boolean requestModeration;

    String stateAction;

    String title;
}
