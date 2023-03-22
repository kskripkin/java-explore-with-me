package ru.practicum.model.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.model.categories.Category;
import ru.practicum.model.compilations.Initiator;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {

    String annotation;

    Category category;

    int confirmedRequests;

    LocalDateTime eventDate;

    int id;

    Initiator initiator;

    boolean paid;

    String title;

    int views;
}
