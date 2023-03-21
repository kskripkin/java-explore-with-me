package ru.practicum.adminCompilations;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.compilations.model.Compilation;

public interface CompilationAdminRepository extends JpaRepository<Compilation, Long> {
}
