package ru.practicum.admin.events;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.admin.users.UserRepository;
import ru.practicum.exception.model.ValidationException;
import ru.practicum.mapper.CustomerMapper;
import ru.practicum.mapper.EventMapper;
import ru.practicum.model.categories.Category;
import ru.practicum.model.events.EventFullDto;
import ru.practicum.model.events.UpdateEventAdminRequest;
import ru.practicum.model.events.Event;
import ru.practicum.model.users.User;
import ru.practicum.pub.categories.CategoriesRepository;
import ru.practicum.pub.events.EventRepository;
import ru.practicum.validation.ValidateEvents;
import ru.practicum.model.events.Location;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminEventsServiceImpl implements AdminEventsService {

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final EventRepository eventRepository;

    private final LocationsRepository locationsRepository;

    private final UserRepository userRepository;

    private final CategoriesRepository categoriesRepository;

    private final EventMapper eventMapper;

    private final CustomerMapper customerMapper;

    private final ValidateEvents validateEvents;

    @Override
    public List<EventFullDto> getEvents(List<Long> users, List<String> states, List<Long> categories, String rangeStart, String rangeEnd, Integer from, Integer size) {
        validateEvents.validateFromAndSize(from, size);

        Map<Long, Category> categoryMap = new HashMap<>();
        categoriesRepository.getAll(categories).stream().forEach(category -> categoryMap.put(category.getId(), category));

        Map<Long, User> userMap = new HashMap<>();
        userRepository.getAll(users).stream().forEach(user -> userMap.put(user.getId(), user));

        if (rangeStart == null && rangeEnd == null && states == null) {

            Map<Long, Event> eventMap = new HashMap<>();
            eventRepository.getEvents(users, categories, PageRequest.of((from / size), size)).stream().forEach(event -> eventMap.put(event.getId(), event));

            List<Long> locationIdList = eventMap.entrySet().stream().map(event -> event.getValue().getLocation()).collect(Collectors.toList());
            Map<Long, Location> locationMap = new HashMap<>();
            locationsRepository.getAll(locationIdList).stream().forEach(location -> locationMap.put(location.getId(), location));

            return eventMap.entrySet()
                    .stream()
                    .map(entrySetEvent -> eventMapper.eventToEventFullDto(
                            entrySetEvent.getValue(),
                            categoryMap.get(entrySetEvent.getValue().getCategory()),
                            userMap.get(entrySetEvent.getValue().getInitiator()),
                            locationMap.get(entrySetEvent.getValue().getLocation())

                    )).collect(Collectors.toList());
        }

        LocalDateTime localDateTimeStartRange = LocalDateTime.parse(rangeStart, formatter);
        LocalDateTime localDateTimeEndRange = LocalDateTime.parse(rangeEnd, formatter);

        Map<Long, Event> eventMap = new HashMap<>();
        eventRepository.getEvents(users, states, categories, localDateTimeStartRange, localDateTimeEndRange, PageRequest.of((from / size), size)).stream().forEach(event -> eventMap.put(event.getId(), event));

        List<Long> locationIdList = eventMap.entrySet().stream().map(event -> event.getValue().getLocation()).collect(Collectors.toList());
        Map<Long, Location> locationMap = new HashMap<>();
        locationsRepository.getAll(locationIdList).stream().forEach(location -> locationMap.put(location.getId(), location));

        return eventMap.entrySet()
                .stream().map(entrySetEvent -> eventMapper.eventToEventFullDto(
                        entrySetEvent.getValue(),
                        categoryMap.get(entrySetEvent.getValue().getCategory()),
                        userMap.get(entrySetEvent.getValue().getInitiator()),
                        locationMap.get(entrySetEvent.getValue().getLocation())
                )).collect(Collectors.toList());
    }

    @Override
    public EventFullDto editEvent(Long eventId, UpdateEventAdminRequest updateEventAdminRequest) {
        validateEvents.findEvent(eventId);
        validateEvents.validateDate(updateEventAdminRequest.getEventDate());
        Event event = eventRepository.getById(eventId);
        long locationId = 0;
        if (updateEventAdminRequest.getLocation() != null) {
            locationId = locationsRepository.save(new Location(
                    updateEventAdminRequest.getLocation().getLat(),
                    updateEventAdminRequest.getLocation().getLon()
            )).getId();
        }
        Event eventEdit = customerMapper.updateEventFromUpdateEventAdminRequest(updateEventAdminRequest, event, locationId);
        eventEdit.setId(eventId);
        switch (updateEventAdminRequest.getStateAction()) {
            case "PUBLISH_EVENT":
                validateEvents.validatePublish(eventId, "PUBLISH_EVENT");
                eventEdit.setStateAction("PUBLISHED");
                break;
            case "REJECT_EVENT":
                validateEvents.validatePublish(eventId, "REJECT_EVENT");
                eventEdit.setStateAction("CANCELED");
                break;
            default:
                throw new ValidationException("State action not found");
        }
        Event eventSave = eventRepository.save(eventEdit);
        return eventMapper.eventToEventFullDto(
                eventSave,
                categoriesRepository.getById(eventSave.getCategory()),
                userRepository.getById(eventSave.getInitiator()),
                locationsRepository.getById(eventSave.getLocation()));
    }
}
