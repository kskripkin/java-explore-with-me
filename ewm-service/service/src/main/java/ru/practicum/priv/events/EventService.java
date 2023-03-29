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

    EventFullDto addEvent(long userId,
                         NewEventDto newEventDto
    );

    EventFullDto getEvent(long userId,
                          long eventId
    );

    EventFullDto editEvent(long userId,
                           long eventId,
                           UpdateEventUserRequest event
    );

    List<ParticipationRequestDto> getRequests(long userId,
                                        long eventId
    );

    EventRequestStatusUpdateResult editRequests(long userId,
                              long eventId,
                              EventRequestStatusUpdateRequest requestRqDto
    );
}
