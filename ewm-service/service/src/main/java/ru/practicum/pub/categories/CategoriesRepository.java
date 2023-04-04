package ru.practicum.pub.categories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.model.categories.Category;

import java.util.List;

public interface CategoriesRepository extends JpaRepository<Category, Long> {

    @Query(value = "select * " +
            "from categories " +
            "where name = ?1 ", nativeQuery = true)
    List<Category> getCategoryByName(String name);

    @Query(value = "select * " +
            "from categories " +
            "where id in (?1) ", nativeQuery = true)
    List<Category> getAll(List<Long> categories);

}
