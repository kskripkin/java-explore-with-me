package ru.practicum.admin.categories;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.model.compilations.Category;

@Service
@RequiredArgsConstructor
public class AdminCategoriesServiceImpl implements AdminCategoriesService {

    private final AdminCategoriesRepository adminCategoriesRepository;
    @Override
    public Category addCategory(Category category) {
        return adminCategoriesRepository.addCategory(category);
    }

    @Override
    public Category deleteCategory(Integer catId) {
        return adminCategoriesRepository.deleteCategory(catId);
    }

    @Override
    public Category editCategory(Integer catId) {
        return adminCategoriesRepository.editCategory(catId);
    }
}
