package ru.practicum.global.user;

import ru.practicum.dto.RequestRsDto;
import ru.practicum.model.events.EventDto;
import ru.practicum.model.events.Event;
import ru.practicum.model.request.RequestRqDto;

import java.util.List;

public interface UserService {

    List<EventDto> getEvents(long userId,
                             Integer from,
                             Integer size
    );

    EventDto addEvent(long userId,
                      Event event
    );

    EventDto getEvent(long userId,
                      long eventId
    );

    EventDto editEvent(long userId,
                       long eventId,
                       Event event
    );

    EventDto getRequests(long userId,
                         long eventId
    );

    RequestRsDto editRequests(long userId,
                              long eventId,
                              RequestRqDto requestRqDto
    );
}
