package ru.practicum.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.admin.events.LocationsRepository;
import ru.practicum.admin.users.UserRepository;
import ru.practicum.model.categories.Category;
import ru.practicum.model.events.*;
import ru.practicum.model.users.User;
import ru.practicum.pub.categories.CategoriesRepository;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class EventMapper {

    private final CategoriesRepository categoriesRepository;
    private final UserRepository userRepository;
    private final LocationsRepository locationsRepository;

    public EventShortDto eventToEventShortDto(Event event, Category category, User initiator, Location location) {
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
