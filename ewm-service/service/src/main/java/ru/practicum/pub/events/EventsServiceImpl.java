package ru.practicum.pub.events;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.exception.model.ValidationException;
import ru.practicum.mapper.EventMapper;
import ru.practicum.model.events.EventFullDto;
import ru.practicum.model.events.EventShortDto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventsServiceImpl implements EventsService {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final EventRepository eventRepository;

    private final EventMapper eventMapper;

    @Override
    public List<EventShortDto> getEvents(String text, Long[] categories, boolean paid, String rangeStart, String rangeEnd, boolean onlyAvailable, String sort, Integer from, Integer size) {
        LocalDateTime localDateTimeStartRange = LocalDateTime.parse(rangeStart, formatter);
        LocalDateTime localDateTimeEndRange = LocalDateTime.parse(rangeEnd, formatter);
        if (categories != null) {
            switch (sort) {
                case "EVENT_DATE":
                    if (onlyAvailable) {
                        return eventRepository.getEventsSortEventDate(text, categories, paid, localDateTimeStartRange, localDateTimeEndRange, PageRequest.of((from / size), size))
                                .stream()
                                .filter(x -> x.getParticipantLimit() > x.getConfirmedRequests())
                                .map(x -> eventMapper.eventToEventShortDto(x))
                                .collect(Collectors.toList());
                    } else {
                        return eventRepository.getEventsSortEventDate(text, categories, paid, localDateTimeStartRange, localDateTimeEndRange, PageRequest.of((from / size), size))
                                .stream()
                                .map(x -> eventMapper.eventToEventShortDto(x))
                                .collect(Collectors.toList());
                    }
                case "VIEWS":
                    if (onlyAvailable) {
                        return eventRepository.getEventsSortViews(text, categories, paid, localDateTimeStartRange, localDateTimeEndRange, PageRequest.of((from / size), size))
                                .stream()
                                .filter(x -> x.getParticipantLimit() > x.getConfirmedRequests())
                                .map(x -> eventMapper.eventToEventShortDto(x))
                                .collect(Collectors.toList());
                    } else {
                        return eventRepository.getEventsSortViews(text, categories, paid, localDateTimeStartRange, localDateTimeEndRange, PageRequest.of((from / size), size))
                                .stream()
                                .map(x -> eventMapper.eventToEventShortDto(x))
                                .collect(Collectors.toList());
                    }
                default:
                    throw new ValidationException("sort not found");
            }
        } else {
            switch (sort) {
                case "EVENT_DATE":
                    if (onlyAvailable) {
                        return eventRepository.getEventsSortEventDate(text, paid, localDateTimeStartRange, localDateTimeEndRange, PageRequest.of((from / size), size))
                                .stream()
                                .filter(x -> x.getParticipantLimit() > x.getConfirmedRequests())
                                .map(x -> eventMapper.eventToEventShortDto(x))
                                .collect(Collectors.toList());
                    } else {
                        return eventRepository.getEventsSortEventDate(text, paid, localDateTimeStartRange, localDateTimeEndRange, PageRequest.of((from / size), size))
                                .stream()
                                .map(x -> eventMapper.eventToEventShortDto(x))
                                .collect(Collectors.toList());
                    }
                case "VIEWS":
                    if (onlyAvailable) {
                        return eventRepository.getEventsSortViews(text, paid, localDateTimeStartRange, localDateTimeEndRange, PageRequest.of((from / size), size))
                                .stream()
                                .filter(x -> x.getParticipantLimit() > x.getConfirmedRequests())
                                .map(x -> eventMapper.eventToEventShortDto(x))
                                .collect(Collectors.toList());
                    } else {
                        return eventRepository.getEventsSortViews(text, paid, localDateTimeStartRange, localDateTimeEndRange, PageRequest.of((from / size), size))
                                .stream()
                                .map(x -> eventMapper.eventToEventShortDto(x))
                                .collect(Collectors.toList());
                    }
                default:
                    throw new ValidationException("sort not found");
            }
        }
    }

    @Override
    public EventFullDto editEvent(Long eventId) {
        return eventMapper.eventToEventFullDto(eventRepository.getById(eventId));
    }
}
