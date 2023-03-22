package ru.practicum.admin.events;

import ru.practicum.model.events.EventDto;
import ru.practicum.model.events.Event;

import java.util.List;

public interface AdminEventsService {

    List<EventDto> getEvents(String[] users,
                                  String[] states,
                                  String[] categories,
                                  String rangeStart,
                                  String rangeEnd,
                                  Integer from,
                                  Integer size
    );

    EventDto editEvent(Integer eventId,
                       Event event);
}
