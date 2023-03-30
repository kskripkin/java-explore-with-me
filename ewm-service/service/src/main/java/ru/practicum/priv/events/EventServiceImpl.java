package ru.practicum.priv.events;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.mapper.CustomerMapperImpl;
import ru.practicum.mapper.EventMapper;
import ru.practicum.mapper.RequestMapper;
import ru.practicum.model.events.*;
import ru.practicum.model.request.ParticipationRequestDto;
import ru.practicum.model.request.RequestRqDto;
import ru.practicum.priv.requests.RequestsRepository;
import ru.practicum.pub.events.EventRepository;
import ru.practicum.validation.ValidateCategories;
import ru.practicum.validation.ValidateEvents;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    private final RequestsRepository requestsRepository;

    private final EventMapper eventMapper;

    private final RequestMapper requestMapper;

    private final CustomerMapperImpl customerMapper;

    private final ValidateEvents validateEvents;

    @Override
    public List<EventShortDto> getEvents(long userId, Integer from, Integer size) {
        return eventRepository.getEvents(userId, PageRequest.of((from / size), size))
                .stream()
                .map(x -> eventMapper.eventToEventShortDto(x)).collect(Collectors.toList());
    }

    @Override
    public EventFullDto addEvent(long userId, NewEventDto newEventDto) {
        validateEvents.validateObject(newEventDto);
        validateEvents.validateDate(newEventDto.getEventDate());
        Event event = eventRepository.save(eventMapper.newEventDtoToEvent(userId, newEventDto));
        return eventMapper.eventToEventFullDto(event);
    }

    @Override
    public EventFullDto getEvent(long userId, long eventId) {
        return eventMapper.eventToEventFullDto(eventRepository.getById(eventId));
    }

    @Override
    public EventFullDto editEvent(long userId, long eventId, UpdateEventUserRequest event) {
        validateEvents.validateDate(event.getEventDate());
        validateEvents.validateIsPublished(eventId);
        Event sourceEvent = eventRepository.getById(eventId);
        Event eventEdit = customerMapper.updateEventFromUpdateEventUserRequest(event, sourceEvent);
        eventEdit.setId(eventId);
        if (eventEdit.getStateAction().equals("CANCEL_REVIEW")) {
            eventEdit.setStateAction("CANCELED");
        }
        if (eventEdit.getStateAction().equals("SEND_TO_REVIEW")) {
            eventEdit.setStateAction("PENDING");
        }
        return eventMapper.eventToEventFullDto(eventRepository.save(eventEdit));
    }

    @Override
    public List<ParticipationRequestDto> getRequests(long userId, long eventId) {
        return requestsRepository.getByIdCurrentUser(eventId)
                .stream()
                .map(x -> requestMapper.RequestToParticipationRequestDto(x)).collect(Collectors.toList());
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
                        .map(x -> requestMapper.RequestToParticipationRequestDto(x)).collect(Collectors.toList())
        );
        eventRequestStatusUpdateResult.setRejectedRequests(
                requestsRepository.getRequestsByEventIdAndStatus("REJECTED", eventId)
                        .stream()
                        .map(x -> requestMapper.RequestToParticipationRequestDto(x)).collect(Collectors.toList())
        );
        return eventRequestStatusUpdateResult;
    }
}
