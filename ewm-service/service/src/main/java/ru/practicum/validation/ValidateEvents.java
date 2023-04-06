package ru.practicum.validation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.exception.model.ConflictException;
import ru.practicum.exception.model.NotFoundException;
import ru.practicum.exception.model.ValidationException;
import ru.practicum.model.events.Event;
import ru.practicum.model.events.NewEventDto;
import ru.practicum.pub.events.EventRepository;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Component
public class ValidateEvents {

    private final EventRepository eventRepository;

    public void findEvent(Long eventId) {
        if (eventId == null || eventId == 0) {
            throw new ValidationException("eventId not found");
        }
    }

    public void validateFindEvent(Long eventId) {
        if (eventRepository.findById(eventId).isEmpty()) {
            throw new NotFoundException("Event id not found");
        }
    }

    public void validateObject(NewEventDto newEventDto) {
        if (newEventDto.getAnnotation() == null || newEventDto.getDescription() == null) {
            throw new ValidationException("eventId not valid");
        }
    }

    public void validateIsPublished(long eventId) {
        if ("PUBLISHED".equals(eventRepository.getById(eventId).getStateAction())) {
            throw new ConflictException("Event is PUBLISHED");
        }
    }

    public void limitPeople(long eventId) {
        Event event = eventRepository.getById(eventId);
        if (event.getParticipantLimit() == event.getConfirmedRequests()) {
            throw new ConflictException("Max peoples for Event");
        }
    }

    public void validateDate(LocalDateTime dateTime) {
        if (dateTime == null) {
            return;
        }
        if (dateTime.isBefore(LocalDateTime.now())) {
            throw new ConflictException("Event date before now");
        }
    }

    public void validatePublish(long eventId, String status) {
        String statusSource = eventRepository.getById(eventId).getStateAction();
        if ("PENDING".equals(statusSource)) {
            return;
        }
        switch (status) {
            case "PUBLISH_EVENT":
                if ("PUBLISHED".equals(statusSource)) {
                    throw new ConflictException("Event state already PUBLISHED");
                } else {
                    throw new ConflictException("Event state already CANCELED");
                }
            case "REJECT_EVENT":
                if ("CANCELED".equals(statusSource)) {
                    throw new ConflictException("Event state already CANCELED");
                } else {
                    throw new ConflictException("Event state already PUBLISHED");
                }
            default:
                throw new ValidationException("Status not found");
        }
    }

    public void validateFromAndSize(Integer from, Integer size) {
        if (from < 0) {
            throw new ValidationException("\"From\" can not be equal or less than 0");
        }
        if (size < 1) {
            throw new ValidationException("\"Size\" can not be equal or less than 1");
        }
    }
}
