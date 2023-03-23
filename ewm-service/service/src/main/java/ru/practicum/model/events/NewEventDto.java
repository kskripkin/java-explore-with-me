package ru.practicum.model.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewEventDto {

    String annotation;

    Long category;

    String description;

    LocalDateTime eventDate;

    Location location;

    boolean paid;

    Long participantLimit;

    boolean requestModeration;

    String title;
}
