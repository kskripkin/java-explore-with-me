package ru.practicum.pub.categories;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.model.categories.Category;
import ru.practicum.validation.ValidateCategories;
import ru.practicum.validation.ValidateEvents;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriesServiceImpl implements CategoriesService {

    private final CategoriesRepository categoriesRepository;

    private final ValidateCategories validateCategories;

    private final ValidateEvents validateEvents;

    @Override
    public List<Category> getCategories(Integer from, Integer size) {
        validateEvents.validateFromAndSize(from, size);
        return categoriesRepository.findAll(PageRequest.of((from / size), size)).getContent();
    }

    @Override
    public Category getCategoriesOne(long catId) {
        validateCategories.validateFoundCategory(catId);
        return categoriesRepository.getById(catId);
    }
}
