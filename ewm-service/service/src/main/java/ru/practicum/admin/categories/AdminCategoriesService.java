package ru.practicum.admin.categories;

import ru.practicum.model.compilations.Category;

public interface AdminCategoriesService {

    Category addCategory(Category category);

    Category deleteCategory(Integer catId);

    Category editCategory(Integer catId);
}
