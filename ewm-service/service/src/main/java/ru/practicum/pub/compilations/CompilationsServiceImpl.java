package ru.practicum.pub.compilations;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.admin.events.LocationsRepository;
import ru.practicum.admin.users.UserRepository;
import ru.practicum.mapper.CompilationsMapper;
import ru.practicum.mapper.EventMapper;
import ru.practicum.model.compilations.Compilation;
import ru.practicum.model.compilations.CompilationDto;
import ru.practicum.model.events.Event;
import ru.practicum.model.events.EventShortDto;
import ru.practicum.pub.categories.CategoriesRepository;
import ru.practicum.pub.events.EventRepository;
import ru.practicum.validation.ValidateEvents;

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
    @Override
    public List<CompilationDto> getCompilations(boolean pinned, Integer from, Integer size) {
        validateEvents.validateFromAndSize(from, size);
        List<Compilation> compilationList = compilationRepository.getCompilations(pinned, PageRequest.of((from / size), size));
        return compilationList
                .stream()
                .map(x -> compilationsMapper.compilationsToCompilationDto(
                        x,
                        eventRepository.getEvents(x.getId())
                                .stream().map(c -> eventMapper.eventToEventShortDto(
                                        c,
                                        categoriesRepository.getById(c.getCategory()),
                                        userRepository.getById(c.getInitiator()),
                                        locationsRepository.getById(c.getLocation())
                                )).collect(Collectors.toList())
                        )
                )
                .collect(Collectors.toList());
    }

    @Override
    public CompilationDto getCompilation(long compId) {
        List<Event> eventList = eventRepository.getEvents(compId);
        List<EventShortDto> eventShortDtoList = eventList.stream()
                .map(x -> eventMapper.eventToEventShortDto(
                        x,
                        categoriesRepository.getById(x.getCategory()),
                        userRepository.getById(x.getInitiator()),
                        locationsRepository.getById(x.getLocation())
                ))
                .collect(Collectors.toList());
        return compilationsMapper.compilationsToCompilationDto(
                compilationRepository.getCompilations(compId),
                eventShortDtoList
        );
    }
}
