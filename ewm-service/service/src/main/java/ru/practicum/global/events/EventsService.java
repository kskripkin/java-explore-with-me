package ru.practicum.global.events;

import ru.practicum.model.compilations.EventDto;
import ru.practicum.model.model.Event;

import java.util.List;

public interface EventsService {

    List<EventDto> getEvents(String text,
                             String[] categories,
                             boolean paid,
                             String rangeStart,
                             String rangeEnd,
                             boolean onlyAvailable,
                             String sort,
                             Integer from,
                             Integer size);

    EventDto editEvent(Integer eventId,
                       Event event);
}
