package ru.practicum.adminCategories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.compilations.model.Category;

public interface AdminCategoriesRepository extends JpaRepository<Category, Long> {

}
