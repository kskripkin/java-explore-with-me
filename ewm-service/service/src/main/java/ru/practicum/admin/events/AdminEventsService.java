package ru.practicum.admin.events;

import ru.practicum.model.events.EventFullDto;
import ru.practicum.model.events.UpdateEventAdminRequest;

import java.util.List;

public interface AdminEventsService {

    List<EventFullDto> getEvents(List<Long> users,
                                 List<String> states,
                                 List<Long> categories,
                                 String rangeStart,
                                 String rangeEnd,
                                 Integer from,
                                 Integer size
    );

    EventFullDto editEvent(Long eventId,
                           UpdateEventAdminRequest event);
}
