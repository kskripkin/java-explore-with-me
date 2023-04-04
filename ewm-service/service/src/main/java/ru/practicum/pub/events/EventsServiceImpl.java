package ru.practicum.pub.events;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.admin.events.LocationsRepository;
import ru.practicum.admin.users.UserRepository;
import ru.practicum.exception.model.ValidationException;
import ru.practicum.mapper.EventMapper;
import ru.practicum.model.categories.Category;
import ru.practicum.model.events.Event;
import ru.practicum.model.events.EventFullDto;
import ru.practicum.model.events.EventShortDto;
import ru.practicum.model.events.Location;
import ru.practicum.model.users.User;
import ru.practicum.pub.categories.CategoriesRepository;
import ru.practicum.validation.ValidateEvents;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

        Map<Long, Event> eventMap = new HashMap<>();

        if (categories != null) {
            switch (sort) {
                case "EVENT_DATE":
                    if (onlyAvailable) {

                        eventRepository.getEventsSortEventDate(text, categories, paid, localDateTimeStartRange, localDateTimeEndRange, PageRequest.of((from / size), size))
                                .stream().forEach(event -> eventMap.put(event.getId(), event));

                        List<Long> categoryIdList = eventMap.entrySet().stream().map(event -> event.getValue().getCategory()).collect(Collectors.toList());
                        Map<Long, Category> categoryMap = new HashMap<>();
                        categoriesRepository.getAll(categoryIdList).stream().forEach(category -> categoryMap.put(category.getId(), category));

                        List<Long> userIdList = eventMap.entrySet().stream().map(event -> event.getValue().getInitiator()).collect(Collectors.toList());
                        Map<Long, User> userMap = new HashMap<>();
                        userRepository.getAll(userIdList).stream().forEach(user -> userMap.put(user.getId(), user));

                        List<Long> locationIdList = eventMap.entrySet().stream().map(event -> event.getValue().getLocation()).collect(Collectors.toList());
                        Map<Long, Location> locationMap = new HashMap<>();
                        locationsRepository.getAll(locationIdList).stream().forEach(location -> locationMap.put(location.getId(), location));

                        return eventMap.entrySet()
                                .stream()
                                .filter(event -> event.getValue().getParticipantLimit() > event.getValue().getConfirmedRequests())
                                .map(entrySetEvent -> eventMapper.eventToEventShortDto(
                                        entrySetEvent.getValue(),
                                        categoryMap.get(entrySetEvent.getValue().getCategory()),
                                        userMap.get(entrySetEvent.getValue().getInitiator()),
                                        locationMap.get(entrySetEvent.getValue().getLocation())
                                ))
                                .collect(Collectors.toList());
                    } else {

                        eventRepository.getEventsSortEventDate(text, categories, paid, localDateTimeStartRange, localDateTimeEndRange, PageRequest.of((from / size), size))
                                .stream().forEach(event -> eventMap.put(event.getId(), event));

                        List<Long> categoryIdList = eventMap.entrySet().stream().map(event -> event.getValue().getCategory()).collect(Collectors.toList());
                        Map<Long, Category> categoryMap = new HashMap<>();
                        categoriesRepository.getAll(categoryIdList).stream().forEach(category -> categoryMap.put(category.getId(), category));

                        List<Long> userIdList = eventMap.entrySet().stream().map(event -> event.getValue().getInitiator()).collect(Collectors.toList());
                        Map<Long, User> userMap = new HashMap<>();
                        userRepository.getAll(userIdList).stream().forEach(user -> userMap.put(user.getId(), user));

                        List<Long> locationIdList = eventMap.entrySet().stream().map(event -> event.getValue().getLocation()).collect(Collectors.toList());
                        Map<Long, Location> locationMap = new HashMap<>();
                        locationsRepository.getAll(locationIdList).stream().forEach(location -> locationMap.put(location.getId(), location));

                        return eventMap.entrySet()
                                .stream()
                                .map(entrySetEvent -> eventMapper.eventToEventShortDto(
                                        entrySetEvent.getValue(),
                                        categoryMap.get(entrySetEvent.getValue().getCategory()),
                                        userMap.get(entrySetEvent.getValue().getInitiator()),
                                        locationMap.get(entrySetEvent.getValue().getLocation())
                                ))
                                .collect(Collectors.toList());
                    }
                case "VIEWS":
                    if (onlyAvailable) {

                        eventRepository.getEventsSortViews(text, categories, paid, localDateTimeStartRange, localDateTimeEndRange, PageRequest.of((from / size), size))
                                .stream().forEach(event -> eventMap.put(event.getId(), event));

                        List<Long> categoryIdList = eventMap.entrySet().stream().map(event -> event.getValue().getCategory()).collect(Collectors.toList());
                        Map<Long, Category> categoryMap = new HashMap<>();
                        categoriesRepository.getAll(categoryIdList).stream().forEach(category -> categoryMap.put(category.getId(), category));

                        List<Long> userIdList = eventMap.entrySet().stream().map(event -> event.getValue().getInitiator()).collect(Collectors.toList());
                        Map<Long, User> userMap = new HashMap<>();
                        userRepository.getAll(userIdList).stream().forEach(user -> userMap.put(user.getId(), user));

                        List<Long> locationIdList = eventMap.entrySet().stream().map(event -> event.getValue().getLocation()).collect(Collectors.toList());
                        Map<Long, Location> locationMap = new HashMap<>();
                        locationsRepository.getAll(locationIdList).stream().forEach(location -> locationMap.put(location.getId(), location));

                        return eventMap.entrySet()
                                .stream()
                                .filter(entrySetEvent -> entrySetEvent.getValue().getParticipantLimit() > entrySetEvent.getValue().getConfirmedRequests())
                                .map(entrySetEvent -> eventMapper.eventToEventShortDto(
                                        entrySetEvent.getValue(),
                                        categoryMap.get(entrySetEvent.getValue().getCategory()),
                                        userMap.get(entrySetEvent.getValue().getInitiator()),
                                        locationMap.get(entrySetEvent.getValue().getLocation())
                                ))
                                .collect(Collectors.toList());
                    } else {

                        eventRepository.getEventsSortViews(text, categories, paid, localDateTimeStartRange, localDateTimeEndRange, PageRequest.of((from / size), size))
                                .stream().forEach(event -> eventMap.put(event.getId(), event));

                        List<Long> categoryIdList = eventMap.entrySet().stream().map(event -> event.getValue().getCategory()).collect(Collectors.toList());
                        Map<Long, Category> categoryMap = new HashMap<>();
                        categoriesRepository.getAll(categoryIdList).stream().forEach(category -> categoryMap.put(category.getId(), category));

                        List<Long> userIdList = eventMap.entrySet().stream().map(event -> event.getValue().getInitiator()).collect(Collectors.toList());
                        Map<Long, User> userMap = new HashMap<>();
                        userRepository.getAll(userIdList).stream().forEach(user -> userMap.put(user.getId(), user));

                        List<Long> locationIdList = eventMap.entrySet().stream().map(event -> event.getValue().getLocation()).collect(Collectors.toList());
                        Map<Long, Location> locationMap = new HashMap<>();
                        locationsRepository.getAll(locationIdList).stream().forEach(location -> locationMap.put(location.getId(), location));

                        return eventMap.entrySet()
                                .stream()
                                .map(entrySetEvent -> eventMapper.eventToEventShortDto(
                                        entrySetEvent.getValue(),
                                        categoryMap.get(entrySetEvent.getValue().getCategory()),
                                        userMap.get(entrySetEvent.getValue().getInitiator()),
                                        locationMap.get(entrySetEvent.getValue().getLocation())
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

                        eventRepository.getEventsSortEventDate(text, paid, localDateTimeStartRange, localDateTimeEndRange, PageRequest.of((from / size), size))
                                .stream().forEach(event -> eventMap.put(event.getId(), event));

                        List<Long> categoryIdList = eventMap.entrySet().stream().map(event -> event.getValue().getCategory()).collect(Collectors.toList());
                        Map<Long, Category> categoryMap = new HashMap<>();
                        categoriesRepository.getAll(categoryIdList).stream().forEach(category -> categoryMap.put(category.getId(), category));

                        List<Long> userIdList = eventMap.entrySet().stream().map(event -> event.getValue().getInitiator()).collect(Collectors.toList());
                        Map<Long, User> userMap = new HashMap<>();
                        userRepository.getAll(userIdList).stream().forEach(user -> userMap.put(user.getId(), user));

                        List<Long> locationIdList = eventMap.entrySet().stream().map(event -> event.getValue().getLocation()).collect(Collectors.toList());
                        Map<Long, Location> locationMap = new HashMap<>();
                        locationsRepository.getAll(locationIdList).stream().forEach(location -> locationMap.put(location.getId(), location));

                        return eventMap.entrySet()
                                .stream()
                                .filter(entrySetEvent -> entrySetEvent.getValue().getParticipantLimit() > entrySetEvent.getValue().getConfirmedRequests())
                                .map(entrySetEvent -> eventMapper.eventToEventShortDto(
                                        entrySetEvent.getValue(),
                                        categoryMap.get(entrySetEvent.getValue().getCategory()),
                                        userMap.get(entrySetEvent.getValue().getInitiator()),
                                        locationMap.get(entrySetEvent.getValue().getLocation())
                                ))
                                .collect(Collectors.toList());
                    } else {

                        eventRepository.getEventsSortEventDate(text, paid, localDateTimeStartRange, localDateTimeEndRange, PageRequest.of((from / size), size))
                                .stream().forEach(event -> eventMap.put(event.getId(), event));

                        List<Long> categoryIdList = eventMap.entrySet().stream().map(event -> event.getValue().getCategory()).collect(Collectors.toList());
                        Map<Long, Category> categoryMap = new HashMap<>();
                        categoriesRepository.getAll(categoryIdList).stream().forEach(category -> categoryMap.put(category.getId(), category));

                        List<Long> userIdList = eventMap.entrySet().stream().map(event -> event.getValue().getInitiator()).collect(Collectors.toList());
                        Map<Long, User> userMap = new HashMap<>();
                        userRepository.getAll(userIdList).stream().forEach(user -> userMap.put(user.getId(), user));

                        List<Long> locationIdList = eventMap.entrySet().stream().map(event -> event.getValue().getLocation()).collect(Collectors.toList());
                        Map<Long, Location> locationMap = new HashMap<>();
                        locationsRepository.getAll(locationIdList).stream().forEach(location -> locationMap.put(location.getId(), location));

                        return eventMap.entrySet()
                                .stream()
                                .map(entrySetEvent -> eventMapper.eventToEventShortDto(
                                        entrySetEvent.getValue(),
                                        categoryMap.get(entrySetEvent.getValue().getCategory()),
                                        userMap.get(entrySetEvent.getValue().getInitiator()),
                                        locationMap.get(entrySetEvent.getValue().getLocation())
                                ))
                                .collect(Collectors.toList());
                    }
                case "VIEWS":
                    if (onlyAvailable) {

                        eventRepository.getEventsSortViews(text, paid, localDateTimeStartRange, localDateTimeEndRange, PageRequest.of((from / size), size))
                                .stream().forEach(event -> eventMap.put(event.getId(), event));

                        List<Long> categoryIdList = eventMap.entrySet().stream().map(event -> event.getValue().getCategory()).collect(Collectors.toList());
                        Map<Long, Category> categoryMap = new HashMap<>();
                        categoriesRepository.getAll(categoryIdList).stream().forEach(category -> categoryMap.put(category.getId(), category));

                        List<Long> userIdList = eventMap.entrySet().stream().map(event -> event.getValue().getInitiator()).collect(Collectors.toList());
                        Map<Long, User> userMap = new HashMap<>();
                        userRepository.getAll(userIdList).stream().forEach(user -> userMap.put(user.getId(), user));

                        List<Long> locationIdList = eventMap.entrySet().stream().map(event -> event.getValue().getLocation()).collect(Collectors.toList());
                        Map<Long, Location> locationMap = new HashMap<>();
                        locationsRepository.getAll(locationIdList).stream().forEach(location -> locationMap.put(location.getId(), location));

                        return eventMap.entrySet()
                                .stream()
                                .filter(entrySetEvent -> entrySetEvent.getValue().getParticipantLimit() > entrySetEvent.getValue().getConfirmedRequests())
                                .map(entrySetEvent -> eventMapper.eventToEventShortDto(
                                        entrySetEvent.getValue(),
                                        categoryMap.get(entrySetEvent.getValue().getCategory()),
                                        userMap.get(entrySetEvent.getValue().getInitiator()),
                                        locationMap.get(entrySetEvent.getValue().getLocation())
                                ))
                                .collect(Collectors.toList());
                    } else {

                        eventRepository.getEventsSortViews(text, paid, localDateTimeStartRange, localDateTimeEndRange, PageRequest.of((from / size), size))
                                .stream().forEach(event -> eventMap.put(event.getId(), event));

                        List<Long> categoryIdList = eventMap.entrySet().stream().map(event -> event.getValue().getCategory()).collect(Collectors.toList());
                        Map<Long, Category> categoryMap = new HashMap<>();
                        categoriesRepository.getAll(categoryIdList).stream().forEach(category -> categoryMap.put(category.getId(), category));

                        List<Long> userIdList = eventMap.entrySet().stream().map(event -> event.getValue().getInitiator()).collect(Collectors.toList());
                        Map<Long, User> userMap = new HashMap<>();
                        userRepository.getAll(userIdList).stream().forEach(user -> userMap.put(user.getId(), user));

                        List<Long> locationIdList = eventMap.entrySet().stream().map(event -> event.getValue().getLocation()).collect(Collectors.toList());
                        Map<Long, Location> locationMap = new HashMap<>();
                        locationsRepository.getAll(locationIdList).stream().forEach(location -> locationMap.put(location.getId(), location));

                        return eventMap.entrySet()
                                .stream()
                                .map(entrySetEvent -> eventMapper.eventToEventShortDto(
                                        entrySetEvent.getValue(),
                                        categoryMap.get(entrySetEvent.getValue().getCategory()),
                                        userMap.get(entrySetEvent.getValue().getInitiator()),
                                        locationMap.get(entrySetEvent.getValue().getLocation())
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
