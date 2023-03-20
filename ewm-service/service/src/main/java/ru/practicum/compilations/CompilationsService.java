package ru.practicum.compilations;

import ru.practicum.compilations.model.Compilation;

import java.util.List;

public interface CompilationsService {

    List<Compilation> getCompilations(boolean pinned,
                                      Integer from,
                                      Integer size
    );

    Compilation getCompilation(Integer compId);

}
