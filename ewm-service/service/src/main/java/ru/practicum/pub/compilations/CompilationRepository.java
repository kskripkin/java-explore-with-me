package ru.practicum.pub.compilations;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.model.compilations.Compilation;

import java.awt.print.Pageable;
import java.util.List;

public interface CompilationRepository extends JpaRepository<Compilation, Long> {

    @Query(value = "select id " +
            "from requests " +
            "where requester = ?1 ", nativeQuery = true)
    public List<Compilation> getCompilations(boolean pinned, Pageable pageable);
}
