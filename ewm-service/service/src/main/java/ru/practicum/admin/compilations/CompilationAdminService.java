package ru.practicum.admin.compilations;

import ru.practicum.model.users.User;
import ru.practicum.model.compilations.Compilation;

public interface CompilationAdminService {

    User addCompilation(Compilation compilation);

    void deleteCompilation(Integer compId);

    User editCompilation(Compilation compilation);
}
