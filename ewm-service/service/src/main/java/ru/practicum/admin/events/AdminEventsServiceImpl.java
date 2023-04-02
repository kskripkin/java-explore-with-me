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
import java.util.List;
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
        
        if (rangeStart == null && rangeEnd == null && states == null) {
            List<Event> eventList = eventRepository.getEvents(users, categories, PageRequest.of((from / size), size));

            List<Category> categoryList = categoriesRepository.getAll(categories);

            List<User> userList = userRepository.getAll(users);

            List<Long> locationIdList = eventList.stream().map(x -> x.getLocation()).collect(Collectors.toList());
            List<Location> locationList = locationsRepository.getAll(locationIdList);

            return eventList
                    .stream()
                    .map(x -> eventMapper.eventToEventFullDto(
                            x,
                            categoryList.stream().filter(v -> v.getId() == x.getCategory()).findFirst().get(),
                            userList.stream().filter(v -> v.getId() == x.getInitiator()).findFirst().get(),
                            locationList.stream().filter(v -> v.getId() == x.getLocation()).findFirst().get()

                    )).collect(Collectors.toList());
        }
        LocalDateTime localDateTimeStartRange = LocalDateTime.parse(rangeStart, formatter);
        LocalDateTime localDateTimeEndRange = LocalDateTime.parse(rangeEnd, formatter);

        List<Event> eventList = eventRepository.getEvents(users, states, categories, localDateTimeStartRange, localDateTimeEndRange, PageRequest.of((from / size), size));

        List<Category> categoryList = categoriesRepository.getAll(categories);

        List<User> userList = userRepository.getAll(users);

        List<Long> locationIdList = eventList.stream().map(x -> x.getLocation()).collect(Collectors.toList());
        List<Location> locationList = locationsRepository.getAll(locationIdList);

        return eventList
                .stream().map(x -> eventMapper.eventToEventFullDto(
                        x,
                        categoryList.stream().filter(v -> v.getId() == x.getCategory()).findFirst().get(),
                        userList.stream().filter(v -> v.getId() == x.getInitiator()).findFirst().get(),
                        locationList.stream().filter(v -> v.getId() == x.getLocation()).findFirst().get()

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
