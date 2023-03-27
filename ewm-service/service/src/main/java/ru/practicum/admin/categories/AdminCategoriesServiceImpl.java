package ru.practicum.admin.categories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.pub.categories.CategoriesRepository;
import ru.practicum.model.categories.Category;
import ru.practicum.validation.ValidateCategories;

@Service
@RequiredArgsConstructor
public class AdminCategoriesServiceImpl implements AdminCategoriesService {

    private final CategoriesRepository categoriesRepository;

    private final ValidateCategories validateCategories;

    @Override
    public Category addCategory(Category category) {
        return categoriesRepository.save(category);
    }

    @Override
    public void deleteCategory(Long catId) {
        validateCategories.validateFoundCategory(catId);
        categoriesRepository.deleteById(catId);
    }

    @Override
    public Category editCategory(Long catId, Category category) {
        validateCategories.validateFoundCategory(catId);
        category.setId(catId);
        return categoriesRepository.save(category);
    }
}
