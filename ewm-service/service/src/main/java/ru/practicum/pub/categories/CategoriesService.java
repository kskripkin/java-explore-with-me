package ru.practicum.pub.categories;

import ru.practicum.model.categories.Category;
import ru.practicum.model.compilations.Compilation;

import java.util.List;

public interface CategoriesService {

    List<Category> getCategories(Integer from,
                                 Integer size
    );

    Category getCategoriesOne(long catId);
}
