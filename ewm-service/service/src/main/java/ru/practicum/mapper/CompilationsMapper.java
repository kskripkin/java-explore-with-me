package ru.practicum.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.model.compilations.Compilation;
import ru.practicum.model.compilations.CompilationDto;
import ru.practicum.model.compilations.NewCompilationDto;
import ru.practicum.model.events.EventShortDto;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CompilationsMapper {

    public CompilationDto compilationsToCompilationDto(Compilation compilation, List<EventShortDto> eventShortDtoList) {
        return new CompilationDto(
                compilation.getId(),
                eventShortDtoList,
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
}
