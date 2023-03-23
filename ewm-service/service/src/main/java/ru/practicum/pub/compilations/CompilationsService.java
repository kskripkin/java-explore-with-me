package ru.practicum.pub.compilations;

import ru.practicum.model.compilations.Compilation;

import java.util.List;

public interface CompilationsService {

    List<Compilation> getCompilations(boolean pinned,
                                      Integer from,
                                      Integer size
    );

    Compilation getCompilation(Integer compId);

}
