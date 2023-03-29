package ru.practicum.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.admin.events.LocationsRepository;
import ru.practicum.admin.users.UserRepository;
import ru.practicum.model.events.*;
import ru.practicum.pub.categories.CategoriesRepository;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class EventMapper {

    private final CategoriesRepository categoriesRepository;
    private final UserRepository userRepository;
    private final LocationsRepository locationsRepository;

    public EventShortDto eventToEventShortDto(Event event) {
        return new EventShortDto(
                event.getId(),
                event.getAnnotation(),
                categoriesRepository.getById(event.getCategory()),
                event.getConfirmedRequests(),
                event.getEventDate(),
                userRepository.getById(event.getInitiator()),
                locationsRepository.getById(event.getLocation()),
                event.getPaid(),
                event.getTitle(),
                event.getViews());
    }

    public NewEventDto eventToNewEventDto(Event event) {
        return new NewEventDto(
                event.getAnnotation(),
                event.getCategory(),
                event.getDescription(),
                event.getEventDate(),
                locationsRepository.getById(event.getLocation()),
                event.getPaid(),
                event.getParticipantLimit(),
                event.getRequestModeration(),
                event.getTitle());
    }

    public EventFullDto eventToEventFullDto(Event event) {
        return new EventFullDto(
                event.getId(),
                event.getAnnotation(),
                categoriesRepository.getById(event.getCategory()),
                event.getConfirmedRequests(),
                event.getCreatedOn(),
                event.getDescription(),
                event.getEventDate(),
                userRepository.getById(event.getInitiator()),
                locationsRepository.getById(event.getLocation()),
                event.getPaid(),
                event.getParticipantLimit(),
                event.getPublishedOn(),
                event.getRequestModeration(),
                event.getStateAction(),
                event.getTitle(),
                event.getViews());
    }

    public UpdateEventUserRequest eventToUpdateEventUserRequest(Event event) {
        return new UpdateEventUserRequest(
                event.getAnnotation(),
                event.getCategory(),
                event.getDescription(),
                event.getEventDate(),
                locationsRepository.getById(event.getLocation()),
                event.getPaid(),
                event.getParticipantLimit(),
                event.getRequestModeration(),
                event.getStateAction(),
                event.getTitle());
    }

    public Event newEventDtoToEvent(long userId, NewEventDto newEventDto) {
        return new Event(
                0,
                newEventDto.getAnnotation(),
                newEventDto.getCategory(),
                0,
                LocalDateTime.now(),
                newEventDto.getDescription(),
                newEventDto.getEventDate(),
                userId,
                locationsRepository.save(newEventDto.getLocation()).getId(),
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
