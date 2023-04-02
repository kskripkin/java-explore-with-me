package ru.practicum.pub.events;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.admin.events.LocationsRepository;
import ru.practicum.admin.users.UserRepository;
import ru.practicum.exception.model.ValidationException;
import ru.practicum.mapper.EventMapper;
import ru.practicum.model.events.Event;
import ru.practicum.model.events.EventFullDto;
import ru.practicum.model.events.EventShortDto;
import ru.practicum.pub.categories.CategoriesRepository;
import ru.practicum.validation.ValidateEvents;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EventsServiceImpl implements EventsService {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final EventRepository eventRepository;

    private final CategoriesRepository categoriesRepository;

    private final UserRepository userRepository;

    private final LocationsRepository locationsRepository;

    private final EventMapper eventMapper;

    private final ValidateEvents validateEvents;

    @Override
    public List<EventShortDto> getEvents(String text, Long[] categories, boolean paid, String rangeStart, String rangeEnd, boolean onlyAvailable, String sort, Integer from, Integer size) {
        validateEvents.validateFromAndSize(from, size);
        LocalDateTime localDateTimeStartRange = LocalDateTime.parse(rangeStart, formatter);
        LocalDateTime localDateTimeEndRange = LocalDateTime.parse(rangeEnd, formatter);
        if (categories != null) {
            switch (sort) {
                case "EVENT_DATE":
                    if (onlyAvailable) {
                        return eventRepository.getEventsSortEventDate(text, categories, paid, localDateTimeStartRange, localDateTimeEndRange, PageRequest.of((from / size), size))
                                .stream()
                                .filter(x -> x.getParticipantLimit() > x.getConfirmedRequests())
                                .map(x -> eventMapper.eventToEventShortDto(
                                        x,
                                        categoriesRepository.getById(x.getCategory()),
                                        userRepository.getById(x.getInitiator()),
                                        locationsRepository.getById(x.getLocation())
                                ))
                                .collect(Collectors.toList());
                    } else {
                        return eventRepository.getEventsSortEventDate(text, categories, paid, localDateTimeStartRange, localDateTimeEndRange, PageRequest.of((from / size), size))
                                .stream()
                                .map(x -> eventMapper.eventToEventShortDto(
                                        x,
                                        categoriesRepository.getById(x.getCategory()),
                                        userRepository.getById(x.getInitiator()),
                                        locationsRepository.getById(x.getLocation())
                                ))
                                .collect(Collectors.toList());
                    }
                case "VIEWS":
                    if (onlyAvailable) {
                        return eventRepository.getEventsSortViews(text, categories, paid, localDateTimeStartRange, localDateTimeEndRange, PageRequest.of((from / size), size))
                                .stream()
                                .filter(x -> x.getParticipantLimit() > x.getConfirmedRequests())
                                .map(x -> eventMapper.eventToEventShortDto(
                                        x,
                                        categoriesRepository.getById(x.getCategory()),
                                        userRepository.getById(x.getInitiator()),
                                        locationsRepository.getById(x.getLocation())
                                ))
                                .collect(Collectors.toList());
                    } else {
                        return eventRepository.getEventsSortViews(text, categories, paid, localDateTimeStartRange, localDateTimeEndRange, PageRequest.of((from / size), size))
                                .stream()
                                .map(x -> eventMapper.eventToEventShortDto(
                                        x,
                                        categoriesRepository.getById(x.getCategory()),
                                        userRepository.getById(x.getInitiator()),
                                        locationsRepository.getById(x.getLocation())
                                ))
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
                                .map(x -> eventMapper.eventToEventShortDto(
                                        x,
                                        categoriesRepository.getById(x.getCategory()),
                                        userRepository.getById(x.getInitiator()),
                                        locationsRepository.getById(x.getLocation())
                                ))
                                .collect(Collectors.toList());
                    } else {
                        return eventRepository.getEventsSortEventDate(text, paid, localDateTimeStartRange, localDateTimeEndRange, PageRequest.of((from / size), size))
                                .stream()
                                .map(x -> eventMapper.eventToEventShortDto(
                                        x,
                                        categoriesRepository.getById(x.getCategory()),
                                        userRepository.getById(x.getInitiator()),
                                        locationsRepository.getById(x.getLocation())
                                ))
                                .collect(Collectors.toList());
                    }
                case "VIEWS":
                    if (onlyAvailable) {
                        return eventRepository.getEventsSortViews(text, paid, localDateTimeStartRange, localDateTimeEndRange, PageRequest.of((from / size), size))
                                .stream()
                                .filter(x -> x.getParticipantLimit() > x.getConfirmedRequests())
                                .map(x -> eventMapper.eventToEventShortDto(
                                        x,
                                        categoriesRepository.getById(x.getCategory()),
                                        userRepository.getById(x.getInitiator()),
                                        locationsRepository.getById(x.getLocation())))
                                .collect(Collectors.toList());
                    } else {
                        return eventRepository.getEventsSortViews(text, paid, localDateTimeStartRange, localDateTimeEndRange, PageRequest.of((from / size), size))
                                .stream()
                                .map(x -> eventMapper.eventToEventShortDto(
                                        x,
                                        categoriesRepository.getById(x.getCategory()),
                                        userRepository.getById(x.getInitiator()),
                                        locationsRepository.getById(x.getLocation())
                                ))
                                .collect(Collectors.toList());
                    }
                default:
                    throw new ValidationException("sort not found");
            }
        }
    }

    @Override
    public EventFullDto editEvent(Long eventId) {
        Event event = eventRepository.getById(eventId);
        return eventMapper.eventToEventFullDto(
                event,
                categoriesRepository.getById(event.getCategory()),
                userRepository.getById(event.getInitiator()),
                locationsRepository.getById(event.getLocation())
        );
    }
}
