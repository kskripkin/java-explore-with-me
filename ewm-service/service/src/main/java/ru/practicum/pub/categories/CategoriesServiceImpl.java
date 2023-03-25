package ru.practicum.pub.categories;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.model.categories.Category;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriesServiceImpl implements CategoriesService {

    private final CategoriesRepository categoriesRepository;

    @Override
    public List<Category> getCategories(Integer from, Integer size) {
        return categoriesRepository.findAll(PageRequest.of((from / size), size)).getContent();
    }

    @Override
    public Category getCategoriesOne(long catId) {
        return categoriesRepository.getById(catId);
    }
}
