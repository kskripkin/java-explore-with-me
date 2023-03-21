package ru.practicum.admin.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.model.compilations.Category;

public interface AdminCategoriesRepository extends JpaRepository<Category, Long> {

}
