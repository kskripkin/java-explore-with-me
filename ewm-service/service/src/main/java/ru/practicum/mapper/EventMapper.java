package ru.practicum.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.admin.events.LocationsRepository;
import ru.practicum.admin.users.UserRepository;
import ru.practicum.model.events.*;
import ru.practicum.pub.categories.CategoriesRepository;

@Component
@RequiredArgsConstructor
public class EventMapper {

    private final CategoriesRepository categoriesRepository;
    private final UserRepository userRepository;
    private final LocationsRepository locationsRepository;

    public EventShortDto EventToEventShortDto(Event event) {
        return new EventShortDto(
                event.getId(),
                event.getAnnotation(),
                categoriesRepository.getById(event.getCategory()),
                event.getConfirmedRequests(),
                event.getEventDate(),
                userRepository.getById(event.getInitiator()),
                locationsRepository.getById(event.getLocation()),
                event.isPaid(),
                event.getTitle(),
                event.getViews());
    }

    public NewEventDto EventToNewEventDto(Event event) {
        return new NewEventDto(
                event.getAnnotation(),
                event.getCategory(),
                event.getDescription(),
                event.getEventDate(),
                locationsRepository.getById(event.getLocation()),
                event.isPaid(),
                event.getParticipantLimit(),
                event.isRequestModeration(),
                event.getTitle());
    }

    public EventFullDto EventToEventFullDto(Event event) {
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
                event.isPaid(),
                event.getParticipantLimit(),
                event.getPublishedOn(),
                event.isRequestModeration(),
                event.getStateAction(),
                event.getTitle(),
                event.getViews());
    }

    public UpdateEventUserRequest EventToUpdateEventUserRequest(Event event) {
        return new UpdateEventUserRequest(
                event.getAnnotation(),
                event.getCategory(),
                event.getDescription(),
                event.getEventDate(),
                locationsRepository.getById(event.getLocation()),
                event.isPaid(),
                event.getParticipantLimit(),
                event.isRequestModeration(),
                event.getStateAction(),
                event.getTitle());
    }


}
