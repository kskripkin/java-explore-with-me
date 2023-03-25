package ru.practicum.pub.compilations;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.model.compilations.Compilation;

public interface CompilationRepository extends JpaRepository<Compilation, Long> {
}
