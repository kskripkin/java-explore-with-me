package ru.practicum.model.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.model.categories.Category;
import ru.practicum.model.users.User;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventShortDto {

    long id;

    String annotation;

    Category category;

    long confirmedRequests;

    LocalDateTime eventDate;

    User initiator;

    Location location;

    boolean paid;

    String title;

    long views;
}
