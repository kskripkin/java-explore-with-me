package ru.practicum.model.compilations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.model.events.EventDto;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompilationDto {

    int id;

    List<EventDto> events;

    boolean pinned;

    String title;
}
