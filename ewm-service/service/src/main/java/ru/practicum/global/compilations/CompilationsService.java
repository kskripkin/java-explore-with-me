package ru.practicum.global.compilations;

import ru.practicum.model.compilations.Compilation;

import java.util.List;

public interface CompilationsService {

    List<Compilation> getCompilations(boolean pinned,
                                      Integer from,
                                      Integer size
    );

    Compilation getCompilation(Integer compId);

}
