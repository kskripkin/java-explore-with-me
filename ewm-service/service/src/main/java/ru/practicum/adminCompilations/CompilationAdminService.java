package ru.practicum.adminCompilations;

import ru.practicum.adminUsers.model.User;
import ru.practicum.compilations.model.Compilation;

public interface CompilationAdminService {

    User addCompilation(Compilation compilation);

    void deleteCompilation(Integer compId);

    User editCompilation(Compilation compilation);
}
