package ru.practicum.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.compilations.model.Category;
import ru.practicum.compilations.model.Initiator;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Event {

    String annotation;

    long category;

    String description;

    LocalDateTime eventDate;

    long location;

    int id;

    boolean paid;

    long participantLimit;

    boolean requestModeration;

    String title;
}
