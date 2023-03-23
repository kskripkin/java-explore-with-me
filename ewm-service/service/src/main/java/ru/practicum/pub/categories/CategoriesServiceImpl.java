package ru.practicum.pub.categories;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.model.compilations.Compilation;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoriesServiceImpl implements CategoriesService {

    private final CategoriesRepository categoriesRepository;

    @Override
    public List<Compilation> getCategories(Integer from, Integer size) {
        return categoriesRepository.getCategories(PageRequest.of((from / size), size));
    }

    @Override
    public List<Compilation> getCategoriesOne(Integer catId) {
        return categoriesRepository.getCategoriesOne(catId);;
    }
}
