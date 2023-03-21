package ru.practicum.adminEvents;

import ru.practicum.compilations.model.EventDto;
import ru.practicum.user.model.Event;

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
