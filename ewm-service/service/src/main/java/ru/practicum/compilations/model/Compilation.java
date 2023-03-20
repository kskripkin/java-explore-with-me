package ru.practicum.compilations.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Compilation {

    EventDto events;

    int id;

    boolean pinned;

    String title;

}
