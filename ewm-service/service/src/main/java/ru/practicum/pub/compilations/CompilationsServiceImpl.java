package ru.practicum.pub.compilations;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.admin.compilations.EventsCompilationRepository;
import ru.practicum.admin.events.LocationsRepository;
import ru.practicum.admin.users.UserRepository;
import ru.practicum.mapper.CompilationsMapper;
import ru.practicum.mapper.EventMapper;
import ru.practicum.model.categories.Category;
import ru.practicum.model.compilations.Compilation;
import ru.practicum.model.compilations.CompilationDto;
import ru.practicum.model.events.Event;
import ru.practicum.model.events.EventShortDto;
import ru.practicum.model.events.EventsToCompilation;
import ru.practicum.model.events.Location;
import ru.practicum.model.users.User;
import ru.practicum.pub.categories.CategoriesRepository;
import ru.practicum.pub.events.EventRepository;
import ru.practicum.validation.ValidateEvents;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CompilationsServiceImpl implements  CompilationsService {

    private final CompilationRepository compilationRepository;

    private final EventRepository eventRepository;

    private final CompilationsMapper compilationsMapper;

    private final ValidateEvents validateEvents;

    private final EventMapper eventMapper;

    private final UserRepository userRepository;

    private final CategoriesRepository categoriesRepository;

    private final LocationsRepository locationsRepository;

    private final EventsCompilationRepository eventsCompilationRepository;

    @Override
    public List<CompilationDto> getCompilations(boolean pinned, Integer from, Integer size) {
        validateEvents.validateFromAndSize(from, size);

        List<Compilation> compilationList = compilationRepository.getCompilations(pinned, PageRequest.of((from / size), size));

        Map<Long, Event> eventMap = new HashMap<>();
        eventRepository.getAll(compilationList.stream().map(compilation -> compilation.getId()).collect(Collectors.toList()))
                .stream().forEach(event -> eventMap.put(event.getId(), event));

        List<EventsToCompilation> eventsToCompilationList = eventsCompilationRepository.getAll(compilationList.stream().map(compilation -> compilation.getId()).collect(Collectors.toList()));

        List<Long> categoryIdList = eventMap.entrySet().stream().map(event -> event.getValue().getCategory()).collect(Collectors.toList());
        Map<Long, Category> categoryMap = new HashMap<>();
        categoriesRepository.getAll(categoryIdList).stream().forEach(category -> categoryMap.put(category.getId(), category));

        List<Long> userIdList = eventMap.entrySet().stream().map(event -> event.getValue().getInitiator()).collect(Collectors.toList());
        Map<Long, User> userMap = new HashMap<>();
        userRepository.getAll(userIdList).stream().forEach(user -> userMap.put(user.getId(), user));

        List<Long> locationIdList = eventMap.entrySet().stream().map(event -> event.getValue().getLocation()).collect(Collectors.toList());
        Map<Long, Location> locationMap = new HashMap<>();
        locationsRepository.getAll(locationIdList).stream().forEach(location -> locationMap.put(location.getId(), location));

        return compilationList
                .stream()
                .map(compilation -> compilationsMapper.compilationsToCompilationDto(
                                compilation,
                                eventToCompilationMapEvent(eventMap, eventsToCompilationList, compilation.getId()).entrySet().stream()
                                        .map(entrySetEvent -> eventMapper.eventToEventShortDto(
                                                entrySetEvent.getValue(),
                                                categoryMap.get(entrySetEvent.getValue().getCategory()),
                                                userMap.get(entrySetEvent.getValue().getInitiator()),
                                                locationMap.get(entrySetEvent.getValue().getLocation())
                                        )).collect(Collectors.toList())
                        )
                )
                .collect(Collectors.toList());
    }

    private Map<Long, Event> eventToCompilationMapEvent(Map<Long, Event> eventMap, List<EventsToCompilation> eventsToCompilationList, Long compId) {
        Map<Long, Event> returnedMapEvent = new HashMap<>();
        for (Map.Entry<Long, Event> entry : eventMap.entrySet()) {
            for (int j = 0; j < eventsToCompilationList.size(); j++) {
                if (entry.getValue().getId() == eventsToCompilationList.get(j).getIdEvent() && eventsToCompilationList.get(j).getIdCompilation() == compId) {
                    returnedMapEvent.put(entry.getValue().getId(), entry.getValue());
                }
            }
        }
        return returnedMapEvent;
    }

    @Override
    public CompilationDto getCompilation(long compId) {
        Map<Long, Event> eventMap = new HashMap<>();
        eventRepository.getEvents(compId).stream().forEach(event -> eventMap.put(event.getId(), event));

        List<Long> categoryIdList = eventMap.entrySet().stream().map(event -> event.getValue().getCategory()).collect(Collectors.toList());
        Map<Long, Category> categoryMap = new HashMap<>();
        categoriesRepository.getAll(categoryIdList).stream().forEach(category -> categoryMap.put(category.getId(), category));

        List<Long> userIdList = eventMap.entrySet().stream().map(event -> event.getValue().getInitiator()).collect(Collectors.toList());
        Map<Long, User> userMap = new HashMap<>();
        userRepository.getAll(userIdList).stream().forEach(user -> userMap.put(user.getId(), user));

        List<Long> locationIdList = eventMap.entrySet().stream().map(event -> event.getValue().getLocation()).collect(Collectors.toList());
        Map<Long, Location> locationMap = new HashMap<>();
        locationsRepository.getAll(locationIdList).stream().forEach(location -> locationMap.put(location.getId(), location));

        List<EventShortDto> eventShortDtoList = eventMap.entrySet().stream()
                .map(entrySetEvent -> eventMapper.eventToEventShortDto(
                        entrySetEvent.getValue(),
                        categoryMap.get(entrySetEvent.getValue().getCategory()),
                        userMap.get(entrySetEvent.getValue().getInitiator()),
                        locationMap.get(entrySetEvent.getValue().getLocation())
                ))
                .collect(Collectors.toList());

        return compilationsMapper.compilationsToCompilationDto(
                compilationRepository.getCompilations(compId),
                eventShortDtoList
        );
    }
}
