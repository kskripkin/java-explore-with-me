package ru.practicum.adminCategories;

import ru.practicum.compilations.model.Category;

public interface AdminCategoriesService {

    Category addCategory(Category category);

    Category deleteCategory(Integer catId);

    Category editCategory(Integer catId);
}
