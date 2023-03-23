package ru.practicum.model.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateEventAdminRequest {

    long id;

    String annotation;

    long category;

    String description;

    LocalDateTime eventDate;

    Location location;

    boolean paid;

    long participantLimit;

    boolean requestModeration;

    String stateAction;

    String title;
}
