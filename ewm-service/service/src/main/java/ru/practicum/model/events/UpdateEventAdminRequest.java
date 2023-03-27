package ru.practicum.model.events;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime eventDate;

    Location location;

    boolean paid;

    long participantLimit;

    boolean requestModeration;

    String stateAction;

    String title;
}
