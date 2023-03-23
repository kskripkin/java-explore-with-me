package ru.practicum.pub.categories;

import ru.practicum.model.compilations.Compilation;

import java.util.List;

public interface CategoriesService {

    List<Compilation> getCategories(Integer from,
                                    Integer size
    );

    List<Compilation> getCategoriesOne(Integer catId);
}
