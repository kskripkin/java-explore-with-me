package ru.practicum.priv.events;

import ru.practicum.model.events.*;
import ru.practicum.model.request.ParticipationRequestDto;
import ru.practicum.model.request.RequestRqDto;

import java.util.List;

public interface EventService {

    List<EventShortDto> getEvents(long userId,
                                  Integer from,
                                  Integer size
    );

    NewEventDto addEvent(long userId,
                         Event event
    );

    EventFullDto getEvent(long userId,
                          long eventId
    );

    UpdateEventUserRequest editEvent(long userId,
                       long eventId,
                       Event event
    );

    ParticipationRequestDto getRequests(long userId,
                                        long eventId
    );

    EventRequestStatusUpdateResult editRequests(long userId,
                              long eventId,
                              RequestRqDto requestRqDto
    );
}
