package ru.practicum.model.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    String annotation;

    long category;

    String description;

    @Column(name = "event_date")
    LocalDateTime eventDate;

    Location location;

    boolean paid;

    @Column(name = "participant_limit")
    long participantLimit;

    @Column(name = "request_moderation")
    boolean requestModeration;

    String stateAction;//не понятно должно ли это поле быть тут или в ДТО

    String title;
}
