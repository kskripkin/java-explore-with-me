package ru.practicum.admin.events;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.pub.events.EventRepository;
import ru.practicum.model.events.EventDto;
import ru.practicum.model.events.Event;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminEventsServiceImpl implements AdminEventsService {

    private final EventRepository eventRepository;

    @Override
    public List<EventDto> getEvents(String[] users, String[] states, String[] categories, String rangeStart, String rangeEnd, Integer from, Integer size) {
        return eventRepository.getEvents(users, states, categories, rangeStart, rangeEnd, PageRequest.of((from / size), size));
    }

    @Override
    public EventDto editEvent(Integer eventId, Event event) {
        event.setId(eventId);
        return eventRepository.save(event);
    }
}
