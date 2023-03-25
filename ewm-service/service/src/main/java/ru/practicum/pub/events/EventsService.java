package ru.practicum.pub.events;

import ru.practicum.model.events.Event;
import ru.practicum.model.events.EventFullDto;
import ru.practicum.model.events.EventShortDto;

import java.util.List;

public interface EventsService {

    List<EventShortDto> getEvents(String text,
                                  String[] categories,
                                  boolean paid,
                                  String rangeStart,
                                  String rangeEnd,
                                  boolean onlyAvailable,
                                  String sort,
                                  Integer from,
                                  Integer size);

    EventFullDto editEvent(Integer eventId,
                               Event event);
}
