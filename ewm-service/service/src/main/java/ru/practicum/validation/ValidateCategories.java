package ru.practicum.validation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.exception.model.ConflictException;
import ru.practicum.exception.model.NotFoundException;
import ru.practicum.exception.model.ValidationException;
import ru.practicum.model.categories.Category;
import ru.practicum.pub.categories.CategoriesRepository;
import ru.practicum.pub.events.EventRepository;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class ValidateCategories {

    private final CategoriesRepository categoriesRepository;

    private final EventRepository eventRepository;

    public void validateFoundCategory(long catId) {
        if (categoriesRepository.findById(catId).isEmpty()) {
            throw new NotFoundException("Category id not found");
        }
    }

    public void validateObject(Category category) {
        if (category.getId() == 0 && category.getName() == null) {
            throw new ValidationException("Category not valid");
        }
    }

    public void validateUniqueCategoryName(Category category) {
        if (!categoriesRepository.getCategoryByName(category.getName()).isEmpty()) {
            throw new ConflictException("Category already used");
        }
    }

    public void validateLinkEvents(long catId) {
        if (!eventRepository.getEventsByCategory(catId).isEmpty()) {
            throw new ConflictException("Category already used");
        }
    }

}
