package ru.practicum.global.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.model.categories.Category;

public interface CategoriesRepository extends JpaRepository<Category, Long> {
}
