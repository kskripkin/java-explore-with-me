package ru.practicum.adminEvents;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.compilations.model.EventDto;
import ru.practicum.user.model.Event;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminEventsServiceImpl implements AdminEventsService {

    private final AdminEventsRepository adminEventsRepository;

    @Override
    public List<EventDto> getEvents(String[] users, String[] states, String[] categories, String rangeStart, String rangeEnd, Integer from, Integer size) {
        return adminEventsRepository.getEvents(users, states, categories, rangeStart, rangeEnd, PageRequest.of((from / size), size));
    }

    @Override
    public EventDto editEvent(Integer eventId, Event event) {
        event.setId(eventId);
        return adminEventsRepository.save(event);
    }
}
