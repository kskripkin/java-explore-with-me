package ru.practicum.priv.events;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.admin.events.LocationsRepository;
import ru.practicum.admin.users.UserRepository;
import ru.practicum.mapper.CustomerMapper;
import ru.practicum.mapper.EventMapper;
import ru.practicum.mapper.RequestMapper;
import ru.practicum.model.events.*;
import ru.practicum.model.request.ParticipationRequestDto;
import ru.practicum.priv.requests.RequestsRepository;
import ru.practicum.pub.categories.CategoriesRepository;
import ru.practicum.pub.events.EventRepository;
import ru.practicum.validation.ValidateEvents;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    private final RequestsRepository requestsRepository;

    private final LocationsRepository locationsRepository;

    private final CategoriesRepository categoriesRepository;

    private final UserRepository userRepository;

    private final EventMapper eventMapper;

    private final RequestMapper requestMapper;

    private final CustomerMapper customerMapper;

    private final ValidateEvents validateEvents;

    @Override
    public List<EventShortDto> getEvents(long userId, Integer from, Integer size) {
        validateEvents.validateFromAndSize(from, size);
        return eventRepository.getEvents(userId, PageRequest.of((from / size), size))
                .stream()
                .map(x -> eventMapper.eventToEventShortDto(
                        x,
                        categoriesRepository.getById(x.getCategory()),
                        userRepository.getById(x.getInitiator()),
                        locationsRepository.getById(x.getLocation())
                )).collect(Collectors.toList());
    }

    @Override
    public EventFullDto addEvent(long userId, NewEventDto newEventDto) {
        validateEvents.validateObject(newEventDto);
        validateEvents.validateDate(newEventDto.getEventDate());
        Event event = eventRepository.save(eventMapper.newEventDtoToEvent(userId, newEventDto, locationsRepository.save(newEventDto.getLocation())));
        return eventMapper.eventToEventFullDto(
                event,
                categoriesRepository.getById(event.getCategory()),
                userRepository.getById(event.getInitiator()),
                locationsRepository.getById(event.getLocation())
        );
    }

    @Override
    public EventFullDto getEvent(long userId, long eventId) {
        Event event = eventRepository.getById(eventId);
        return eventMapper.eventToEventFullDto(
                event,
                categoriesRepository.getById(event.getCategory()),
                userRepository.getById(event.getInitiator()),
                locationsRepository.getById(event.getLocation())
        );
    }

    @Override
    public EventFullDto editEvent(long userId, long eventId, UpdateEventUserRequest event) {
        validateEvents.validateDate(event.getEventDate());
        validateEvents.validateIsPublished(eventId);
        Event sourceEvent = eventRepository.getById(eventId);
        long locationId = 0;
        if (event.getLocation() != null) {
            locationId = locationsRepository.save(new Location(
                    event.getLocation().getLat(),
                    event.getLocation().getLon()
            )).getId();
        }
        Event eventEdit = customerMapper.updateEventFromUpdateEventUserRequest(event, sourceEvent, locationId);
        eventEdit.setId(eventId);
        if ("CANCEL_REVIEW".equals(eventEdit.getStateAction())) {
            eventEdit.setStateAction("CANCELED");
        }
        if ("SEND_TO_REVIEW".equals(eventEdit.getStateAction())) {
            eventEdit.setStateAction("PENDING");
        }
        Event eventSave = eventRepository.save(eventEdit);
        return eventMapper.eventToEventFullDto(
                eventSave,
                categoriesRepository.getById(eventSave.getCategory()),
                userRepository.getById(eventSave.getInitiator()),
                locationsRepository.getById(eventSave.getLocation())
        );
    }

    @Override
    public List<ParticipationRequestDto> getRequests(long userId, long eventId) {
        return requestsRepository.getByIdCurrentUser(eventId)
                .stream()
                .map(x -> requestMapper.requestToParticipationRequestDto(x)).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public EventRequestStatusUpdateResult editRequests(long userId, long eventId, EventRequestStatusUpdateRequest requestRqDto) {
        validateEvents.limitPeople(eventId);
        Event event = eventRepository.getById(eventId);
        event.setConfirmedRequests(event.getConfirmedRequests() + requestRqDto.getRequestIds().size());
        eventRepository.save(event);
        requestsRepository.saveAllStatus(eventId, requestRqDto.getRequestIds(), requestRqDto.getStatus());

        EventRequestStatusUpdateResult eventRequestStatusUpdateResult = new EventRequestStatusUpdateResult();
        eventRequestStatusUpdateResult.setConfirmedRequests(
                requestsRepository.getRequestsByEventIdAndStatus("CONFIRMED", eventId)
                        .stream()
                        .map(x -> requestMapper.requestToParticipationRequestDto(x)).collect(Collectors.toList())
        );
        eventRequestStatusUpdateResult.setRejectedRequests(
                requestsRepository.getRequestsByEventIdAndStatus("REJECTED", eventId)
                        .stream()
                        .map(x -> requestMapper.requestToParticipationRequestDto(x)).collect(Collectors.toList())
        );
        return eventRequestStatusUpdateResult;
    }
}
