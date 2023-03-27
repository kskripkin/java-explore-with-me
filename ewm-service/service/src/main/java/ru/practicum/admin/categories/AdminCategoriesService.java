package ru.practicum.admin.categories;

import ru.practicum.model.categories.Category;

public interface AdminCategoriesService {

    Category addCategory(Category category);

    void deleteCategory(Long catId);

    Category editCategory(Long catId, Category category);
}
