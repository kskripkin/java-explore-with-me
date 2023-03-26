package ru.practicum.pub.compilations;

import ru.practicum.model.compilations.Compilation;
import ru.practicum.model.compilations.CompilationDto;

import java.util.List;

public interface CompilationsService {

    List<CompilationDto> getCompilations(boolean pinned,
                                      Integer from,
                                      Integer size
    );

    CompilationDto getCompilation(Integer compId);

}
