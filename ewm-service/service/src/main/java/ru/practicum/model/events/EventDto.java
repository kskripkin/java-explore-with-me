package ru.practicum.model.events;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    private String annotation;

    private Category category;

    private int confirmedRequests;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;

    private int id;

    private Initiator initiator;

    private boolean paid;

    private String title;

    private int views;
}
