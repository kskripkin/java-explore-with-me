package ru.practicum.validation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.exception.model.ConflictException;
import ru.practicum.exception.model.NotFoundException;
import ru.practicum.model.categories.Category;
import ru.practicum.pub.categories.CategoriesRepository;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class ValidateCategories {

    private final CategoriesRepository categoriesRepository;

    public void validateFoundCategory(long catId) {
        if (categoriesRepository.findById(catId).isEmpty()) {
            throw new NotFoundException("Category id not found");
        }
    }

}
