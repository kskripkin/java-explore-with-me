package ru.practicum.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.model.categories.Category;
import ru.practicum.model.events.*;
import ru.practicum.model.users.User;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class EventMapper {

    public EventShortDto eventToEventShortDto(Event event, Category category, User initiator, Location location) {
        return new EventShortDto(
                event.getId(),
                event.getAnnotation(),
                category,
                event.getConfirmedRequests(),
                event.getEventDate(),
                initiator,
                location,
                event.getPaid(),
                event.getTitle(),
                event.getViews());
    }

    public EventFullDto eventToEventFullDto(Event event, Category category, User initiator, Location location) {
        return new EventFullDto(
                event.getId(),
                event.getAnnotation(),
                category,
                event.getConfirmedRequests(),
                event.getCreatedOn(),
                event.getDescription(),
                event.getEventDate(),
                initiator,
                location,
                event.getPaid(),
                event.getParticipantLimit(),
                event.getPublishedOn(),
                event.getRequestModeration(),
                event.getStateAction(),
                event.getTitle(),
                event.getViews());
    }

    public Event newEventDtoToEvent(long userId, NewEventDto newEventDto, Location location) {
        return new Event(
                0,
                newEventDto.getAnnotation(),
                newEventDto.getCategory(),
                0,
                LocalDateTime.now(),
                newEventDto.getDescription(),
                newEventDto.getEventDate(),
                userId,
                location.getId(),
                newEventDto.isPaid(),
                newEventDto.getParticipantLimit(),
                null,
                newEventDto.isRequestModeration(),
                "PENDING",
                newEventDto.getTitle(),
                0
        );
    }

}
