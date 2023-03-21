package ru.practicum.admin.compilations;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.model.compilations.Compilation;

public interface CompilationAdminRepository extends JpaRepository<Compilation, Long> {
}
