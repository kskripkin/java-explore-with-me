package ru.practicum.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.compilations.model.Category;

public interface AdminRepository extends JpaRepository<Category, Long> {

}
