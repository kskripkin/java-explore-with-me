package ru.practicum.validation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.exception.model.NotFoundException;
import ru.practicum.exception.model.ValidationException;
import ru.practicum.model.categories.Category;
import ru.practicum.model.compilations.NewCompilationDto;
import ru.practicum.pub.categories.CategoriesRepository;
import ru.practicum.pub.compilations.CompilationRepository;

@Slf4j
@RequiredArgsConstructor
@Component
public class ValidateCompilations {

    private final CompilationRepository compilationRepository;

    public void findCompilation(long compId) {
        if (compilationRepository.findById(compId).isEmpty()) {
            throw new NotFoundException("Category id not found");
        }
    }

    public void validateObject(NewCompilationDto newCompilationDto) {
        if (newCompilationDto.getEvents() == null || newCompilationDto.getTitle() == null) {
            throw new ValidationException("NewCompilationDto not valid");
        }
    }

}
