package ru.practicum.admin.categories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.global.categories.CategoriesRepository;
import ru.practicum.model.categories.Category;

@Service
@RequiredArgsConstructor
public class AdminCategoriesServiceImpl implements AdminCategoriesService {

    private final CategoriesRepository categoriesRepository;
    @Override
    public Category addCategory(Category category) {
        return categoriesRepository.save(category);
    }

    @Override
    public void deleteCategory(Long catId) {
        categoriesRepository.deleteById(catId);
    }

    @Override
    public Category editCategory(Integer catId, Category category) {
        category.setId(catId);
        return categoriesRepository.save(category);
    }
}
