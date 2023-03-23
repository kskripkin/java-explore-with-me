package ru.practicum.priv.events;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.mapper.EventMapper;
import ru.practicum.mapper.RequestMapper;
import ru.practicum.model.dto.RequestRsDto;
import ru.practicum.model.events.*;
import ru.practicum.model.request.ParticipationRequestDto;
import ru.practicum.model.request.RequestRqDto;
import ru.practicum.priv.requests.RequestsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventServiceRepository eventServiceRepository;
    private final RequestsRepository requestsRepository;
    private final EventMapper eventMapper;
    private final RequestMapper requestMapper;

    @Override
    public List<EventShortDto> getEvents(long userId, Integer from, Integer size) {
        //МАППИНГ
        return eventServiceRepository.getEvents(userId, PageRequest.of((from / size), size))
                .stream()
                .map(x -> eventMapper.EventToEventShortDto(x)).collect(Collectors.toList());
    }

    @Override
    public NewEventDto addEvent(long userId, Event event) {
        //МАППИНГ
        return eventMapper.EventToNewEventDto(eventServiceRepository.save(event));
    }

    @Override
    public EventFullDto getEvent(long userId, long eventId) {
        //МАППИНГ
        return eventMapper.EventToEventFullDto(eventServiceRepository.getById(eventId));
    }

    @Override
    public UpdateEventUserRequest editEvent(long userId, long eventId, Event event) {
        event.setId(eventId);
        //МАППИНГ
        return eventMapper.EventToUpdateEventUserRequest(eventServiceRepository.save(event));
    }

    @Override
    public ParticipationRequestDto getRequests(long userId, long eventId) {
        //МАППИНГ
        return requestMapper.RequestToParticipationRequestDto(requestsRepository.getById(eventId));
    }

    @Override
    public EventRequestStatusUpdateRequest editRequests(long userId, long eventId, RequestRqDto requestRqDto) {
        //МАППИНГ
        return requestMapper.RequestToEventRequestStatusUpdateRequest(requestsRepository.save(requestRqDto));//надо сделать чтобы в несколько requests вставлялся передаваемый статус
    }
}
