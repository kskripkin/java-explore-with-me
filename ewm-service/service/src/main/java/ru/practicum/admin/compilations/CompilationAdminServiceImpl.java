package ru.practicum.admin.compilations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.admin.events.LocationsRepository;
import ru.practicum.admin.users.UserRepository;
import ru.practicum.mapper.CompilationsMapper;
import ru.practicum.mapper.CustomerMapper;
import ru.practicum.mapper.EventMapper;
import ru.practicum.model.categories.Category;
import ru.practicum.model.compilations.Compilation;
import ru.practicum.model.compilations.CompilationDto;
import ru.practicum.model.compilations.NewCompilationDto;
import ru.practicum.model.compilations.UpdateCompilationRequest;
import ru.practicum.model.events.Event;
import ru.practicum.model.events.EventShortDto;
import ru.practicum.model.events.EventsToCompilation;
import ru.practicum.model.events.Location;
import ru.practicum.model.users.User;
import ru.practicum.pub.categories.CategoriesRepository;
import ru.practicum.pub.compilations.CompilationRepository;
import ru.practicum.pub.events.EventRepository;
import ru.practicum.validation.ValidateCompilations;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CompilationAdminServiceImpl implements CompilationAdminService {

    private final CompilationRepository compilationRepository;

    private final EventRepository eventRepository;

    private final UserRepository userRepository;

    private final LocationsRepository locationsRepository;

    private final CategoriesRepository categoriesRepository;

    private final CompilationsMapper compilationsMapper;

    private final CustomerMapper customerMapper;

    private final ValidateCompilations validateCompilations;

    private final EventMapper eventMapper;

    private final EventsCompilationRepository eventsCompilationRepository;

    @Transactional
    @Override
    public CompilationDto addCompilation(NewCompilationDto newCompilationDto) {
        validateCompilations.validateObject(newCompilationDto);
        Compilation compilation = compilationRepository.save(compilationsMapper.newCompilationDtoToCompilation(newCompilationDto));
        List<EventsToCompilation> eventsToCompilationList = new ArrayList<>();
        for (Long eventId : newCompilationDto.getEvents()) {
            eventsToCompilationList.add(new EventsToCompilation(compilation.getId(), eventId));
        }
        eventsCompilationRepository.saveAll(eventsToCompilationList);
        List<Event> eventList = eventRepository.getEvents(compilation.getId());

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
                compilation,
                eventShortDtoList
        );
    }

    @Transactional
    @Override
    public void deleteCompilation(Long compId) {
        validateCompilations.findCompilation(compId);
        compilationRepository.deleteIdEventsToCompilation(compId);
        compilationRepository.deleteCompilation(compId);
    }

    @Transactional
    @Override
    public CompilationDto editCompilation(Long compId, UpdateCompilationRequest updateCompilationRequest) {
        Compilation compilationSource = compilationRepository.getById(compId);
        Compilation compilationUpdate = customerMapper.updateEventFromUpdateCompilationRequest(updateCompilationRequest, compilationSource);
        compilationRepository.deleteIdEventsToCompilation(compId);
        List<EventsToCompilation> eventsToCompilationList = new ArrayList<>();
        for (Long eventId : updateCompilationRequest.getEvents()) {
            eventsToCompilationList.add(new EventsToCompilation(compId, eventId));
        }
        eventsCompilationRepository.saveAll(eventsToCompilationList);
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
                compilationRepository.save(compilationUpdate),
                eventShortDtoList
        );
    }
}
