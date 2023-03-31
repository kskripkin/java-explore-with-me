package ru.practicum.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.model.compilations.Compilation;
import ru.practicum.model.compilations.CompilationDto;
import ru.practicum.model.compilations.NewCompilationDto;
import ru.practicum.model.compilations.UpdateCompilationRequest;
import ru.practicum.pub.events.EventRepository;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CompilationsMapper {

    private final EventRepository eventRepository;
    private final EventMapper eventMapper;

    public CompilationDto compilationsToCompilationDto(Compilation compilation) {
        return new CompilationDto(
                compilation.getId(),
                eventRepository.getEvents(compilation.getId())
                        .stream()
                        .map(x -> eventMapper.eventToEventShortDto(x))
                        .collect(Collectors.toList()),
                compilation.isPinned(),
                compilation.getTitle()
        );
    }

    public Compilation newCompilationDtoToCompilation(NewCompilationDto newCompilationDto) {
        return new Compilation(
                0,
                newCompilationDto.isPinned(),
                newCompilationDto.getTitle());
    }

    public NewCompilationDto updateCompilationRequestToNewCompilationDto(UpdateCompilationRequest updateCompilationRequest) {
        return new NewCompilationDto(
              updateCompilationRequest.getEvents(),
              updateCompilationRequest.getPinned(),
              updateCompilationRequest.getTitle()
        );
    }



}
