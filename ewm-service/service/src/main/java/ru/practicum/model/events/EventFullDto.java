package ru.practicum.model.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.model.categories.Category;
import ru.practicum.model.users.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventFullDto {

    long id;

    String annotation;

    Category category;

    @Column(name = "confirmed_requests")
    long confirmedRequests;

    @Column(name = "created_on")
    LocalDateTime createdOn;

    String description;

    @Column(name = "event_date")
    LocalDateTime eventDate;

    User initiator;

    Location location;

    boolean paid;

    @Column(name = "participant_limit")
    long participantLimit;

    @Column(name = "published_on")
    LocalDateTime publishedOn;

    @Column(name = "request_moderation")
    boolean requestModeration;

    String stateAction;

    String title;

    long views;
}
