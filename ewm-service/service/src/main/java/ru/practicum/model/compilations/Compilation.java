package ru.practicum.model.compilations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.practicum.model.events.EventDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Compilation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    EventDto events;

    boolean pinned;

    String title;

}
