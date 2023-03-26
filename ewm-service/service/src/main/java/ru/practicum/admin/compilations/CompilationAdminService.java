package ru.practicum.admin.compilations;

import ru.practicum.model.compilations.CompilationDto;
import ru.practicum.model.compilations.NewCompilationDto;
import ru.practicum.model.compilations.UpdateCompilationRequest;

public interface CompilationAdminService {

    CompilationDto addCompilation(NewCompilationDto newCompilationDto);

    void deleteCompilation(Long compId);

    CompilationDto editCompilation(Long compId, UpdateCompilationRequest updateCompilationRequest);
}
