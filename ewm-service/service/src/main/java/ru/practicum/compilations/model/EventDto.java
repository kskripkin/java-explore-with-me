package ru.practicum.compilations.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
