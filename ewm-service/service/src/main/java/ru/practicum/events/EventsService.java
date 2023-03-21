package ru.practicum.events;

import ru.practicum.compilations.model.EventDto;
import ru.practicum.user.model.Event;

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
