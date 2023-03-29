package ru.practicum.pub.compilations;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.model.compilations.Compilation;

import java.util.List;

public interface CompilationRepository extends JpaRepository<Compilation, Long> {

    @Query(value = "select * " +
            "from compilations " +
            "where pinned = ?1 ", nativeQuery = true)
    List<Compilation> getCompilations(boolean pinned, Pageable pageable);

    @Query(value = "select * " +
            "from compilations " +
            "where id = ?1 ", nativeQuery = true)
    Compilation getCompilations(long compId);

    @Modifying
    @Query(value = "insert into events_to_compilation (id_compilation, id_event) " +
            "values (?1, ?2) ", nativeQuery = true)
    void saveEventsToCompilation(long idCompilation, long idEvent);

    @Modifying
    @Query(value = "delete from events_to_compilation " +
            "where id_compilation = ?1 ", nativeQuery = true)
    void deleteIdEventsToCompilation(Long compId);

    @Modifying
    @Query(value = "delete from compilations " +
            "where id = ?1 ", nativeQuery = true)
    void deleteCompilation(Long compId);
}
