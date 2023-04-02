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
import java.util.List;
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

        List<Event> eventList = eventRepository.getAll(compilationList.stream().map(x -> x.getId()).collect(Collectors.toList()));

        List<EventsToCompilation> eventsToCompilationList = eventsCompilationRepository.getAll(compilationList.stream().map(x -> x.getId()).collect(Collectors.toList()));

        List<Long> categoryIdList = eventList.stream().map(x -> x.getCategory()).collect(Collectors.toList());
        List<Category> categoryList = categoriesRepository.getAll(categoryIdList);

        List<Long> userIdList = eventList.stream().map(x -> x.getInitiator()).collect(Collectors.toList());
        List<User> userList = userRepository.getAll(userIdList);

        List<Long> locationIdList = eventList.stream().map(x -> x.getLocation()).collect(Collectors.toList());
        List<Location> locationList = locationsRepository.getAll(locationIdList);

        return compilationList
                .stream()
                .map(x -> compilationsMapper.compilationsToCompilationDto(
                                x,
                                eventToCompilationMapEvent(eventList, eventsToCompilationList, x.getId()).stream()
                                        .map(c -> eventMapper.eventToEventShortDto(
                                                c,
                                                categoryList.stream().filter(v -> v.getId() == c.getCategory()).findFirst().get(),
                                                userList.stream().filter(v -> v.getId() == c.getInitiator()).findFirst().get(),
                                                locationList.stream().filter(v -> v.getId() == c.getLocation()).findFirst().get()
                                        )).collect(Collectors.toList())
                        )
                )
                .collect(Collectors.toList());
    }

    private List<Event> eventToCompilationMapEvent(List<Event> eventList, List<EventsToCompilation> eventsToCompilationList, Long compId) {
        List<Event> returnedListEvent = new ArrayList<>();
        for (int i = 0; i < eventList.size(); i++) {
            for (int j = 0; j < eventsToCompilationList.size(); j++) {
                if (eventList.get(i).getId() == eventsToCompilationList.get(j).getIdEvent() && eventsToCompilationList.get(j).getIdCompilation() == compId) {
                    returnedListEvent.add(eventList.get(i));
                }
            }
        }
        return returnedListEvent;
    }

    @Override
    public CompilationDto getCompilation(long compId) {
        List<Event> eventList = eventRepository.getEvents(compId);

        List<Long> categoryIdList = eventList.stream().map(x -> x.getCategory()).collect(Collectors.toList());
        List<Category> categoryList = categoriesRepository.getAll(categoryIdList);

        List<Long> userIdList = eventList.stream().map(x -> x.getInitiator()).collect(Collectors.toList());
        List<User> userList = userRepository.getAll(userIdList);

        List<Long> locationIdList = eventList.stream().map(x -> x.getLocation()).collect(Collectors.toList());
        List<Location> locationList = locationsRepository.getAll(locationIdList);

        List<EventShortDto> eventShortDtoList = eventList.stream()
                .map(x -> eventMapper.eventToEventShortDto(
                        x,
                        categoryList.stream().filter(v -> v.getId() == x.getCategory()).findFirst().get(),
                        userList.stream().filter(v -> v.getId() == x.getInitiator()).findFirst().get(),
                        locationList.stream().filter(v -> v.getId() == x.getLocation()).findFirst().get()
                ))
                .collect(Collectors.toList());
        return compilationsMapper.compilationsToCompilationDto(
                compilationRepository.getCompilations(compId),
                eventShortDtoList
        );
    }
}
