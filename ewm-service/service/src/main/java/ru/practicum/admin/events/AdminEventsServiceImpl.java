package ru.practicum.admin.events;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.mapper.EventMapper;
import ru.practicum.model.events.EventFullDto;
import ru.practicum.priv.events.EventServiceRepository;
import ru.practicum.model.events.Event;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminEventsServiceImpl implements AdminEventsService {

    private final EventServiceRepository eventRepository;
    private final EventMapper eventMapper;

    @Override
    public List<EventFullDto> getEvents(String[] users, String[] states, String[] categories, String rangeStart, String rangeEnd, Integer from, Integer size) {
        return eventRepository.getEvents(users, states, categories, rangeStart, rangeEnd, PageRequest.of((from / size), size))
                .stream().map(x -> eventMapper.EventToEventFullDto(x)).collect(Collectors.toList());
    }

    @Override
    public EventFullDto editEvent(Integer eventId, Event event) {
        event.setId(eventId);
        return eventMapper.EventToEventFullDto(eventRepository.save(event));
    }
}
