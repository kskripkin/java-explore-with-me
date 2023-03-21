package ru.practicum.categories;

import ru.practicum.compilations.model.Compilation;

import java.util.List;

public interface CategoriesService {

    List<Compilation> getCategories(Integer from,
                                    Integer size
    );

    List<Compilation> getCategoriesOne(Integer catId);
}
