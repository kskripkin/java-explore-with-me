package ru.practicum.admin;

import ru.practicum.compilations.model.Category;

public interface AdminService {

    Category addCategory(Category category);

    Category deleteCategory(Integer catId);

    Category editCategory(Integer catId);
}
