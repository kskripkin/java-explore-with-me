package ru.practicum.admin.events;

import ru.practicum.model.events.EventDto;
import ru.practicum.model.events.Event;
import ru.practicum.model.events.EventFullDto;
import ru.practicum.model.events.UpdateEventAdminRequest;

import java.util.List;

public interface AdminEventsService {

    List<EventFullDto> getEvents(String[] users,
                                 String[] states,
                                 String[] categories,
                                 String rangeStart,
                                 String rangeEnd,
                                 Integer from,
                                 Integer size
    );

    EventFullDto editEvent(Integer eventId,
                           UpdateEventAdminRequest event);
}
