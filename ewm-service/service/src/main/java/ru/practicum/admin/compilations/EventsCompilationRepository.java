package ru.practicum.admin.compilations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.model.events.EventsToCompilation;

import java.util.List;

public interface EventsCompilationRepository extends JpaRepository<EventsToCompilation, Long> {

    @Query(value = "select * " +
            "from events_to_compilation " +
            "where id_compilation in (?1) ", nativeQuery = true)
    List<EventsToCompilation> getAll(List<Long> compIds);

}
