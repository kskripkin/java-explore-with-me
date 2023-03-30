package ru.practicum.validation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.exception.model.ConflictException;
import ru.practicum.model.events.Event;
import ru.practicum.priv.requests.RequestsRepository;
import ru.practicum.pub.events.EventRepository;

@Slf4j
@RequiredArgsConstructor
@Component
public class ValidateRequests {

    private final RequestsRepository requestsRepository;

    private final EventRepository eventRepository;

    public void validateRequestFromUser(long userId, long eventId) {
        if (!requestsRepository.getRequestFromUserByEvent(userId, eventId).isEmpty()) {
            throw new ConflictException("User already set request on event");
        }
    }

    public void validateUserOwnerEvent(long userId, long eventId) {
        if (eventRepository.getById(eventId).getInitiator() == userId) {
            throw new ConflictException("User owner this event");
        }
    }

    public void validatePublishEvent(long eventId) {
        if (!eventRepository.getById(eventId).getStateAction().equals("PUBLISHED")) {
            throw new ConflictException("Event not published");
        }
    }

    public void validateLimitPeoples(long eventId) {
        Event event = eventRepository.getById(eventId);
        if (event.getParticipantLimit() == event.getConfirmedRequests()) {
            throw new ConflictException("There are no places for the event");
        }
    }

}
