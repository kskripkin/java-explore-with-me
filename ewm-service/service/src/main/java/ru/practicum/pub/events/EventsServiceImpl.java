package ru.practicum.pub.events;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.model.events.EventDto;
import ru.practicum.model.events.Event;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventsServiceImpl implements EventsService {

    private final EventRepository eventRepository;

    @Override
    public List<EventDto> getEvents(String text, String[] categories, boolean paid, String rangeStart, String rangeEnd, boolean onlyAvailable, String sort, Integer from, Integer size) {
        return eventRepository.getEvents(text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, PageRequest.of((from / size), size));
    }

    @Override
    public EventDto editEvent(Integer eventId, Event event) {
        event.setId(eventId);
        return eventRepository.save(event);
    }
}
