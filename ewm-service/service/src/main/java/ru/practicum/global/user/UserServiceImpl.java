package ru.practicum.global.user;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.dto.RequestRsDto;
import ru.practicum.model.compilations.EventDto;
import ru.practicum.model.model.Event;
import ru.practicum.model.model.RequestRqDto;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserServiceRepository userServiceRepository;

    @Override
    public List<EventDto> getEvents(long userId, Integer from, Integer size) {
        return userServiceRepository.getEvents(userId, PageRequest.of((from / size), size));
    }

    @Override
    public EventDto addEvent(long userId, Event event) {
        return userServiceRepository.addEvent(event);
    }

    @Override
    public EventDto getEvent(long userId, long eventId) {
        return userServiceRepository.getEvent(eventId);
    }

    @Override
    public EventDto editEvent(long userId, long eventId, Event event) {
        event.setId(eventId);
        return userServiceRepository.save(event);
    }

    @Override
    public EventDto getRequests(long userId, long eventId) {
        return userServiceRepository.getRequests(eventId);
    }

    @Override
    public RequestRsDto editRequests(long userId, long eventId, RequestRqDto requestRqDto) {
        return userServiceRepository.save(requestRqDto);
    }
}
