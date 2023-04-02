package ru.practicum.admin.compilations;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.model.events.EventsToCompilation;

public interface EventsCompilationRepository extends JpaRepository<EventsToCompilation, Long> {
}
