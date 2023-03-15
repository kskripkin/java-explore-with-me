package ru.practicum.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.model.ViewStats;
import ru.practicum.server.model.EndpointHit;

import java.time.LocalDateTime;
import java.util.Collection;

public interface EndpointHitRepository extends JpaRepository<EndpointHit, Long> {

    //TODO запросы требуется поправить на уникальность как минимум
    @Query(value = "select app, uri, count(uri) " +
            "from stats " +
            "where timestamp > ?1 and timestamp <= ?2 " +
            "group by uri ", nativeQuery = true)
    Collection<ViewStats> getAllUnique(LocalDateTime start, LocalDateTime end);

    @Query(value = "select app, uri, count(uri) " +
            "from stats " +
            "where timestamp > ?1 and timestamp <= ?2 ", nativeQuery = true)
    Collection<ViewStats> getAllNonUnique(LocalDateTime start, LocalDateTime end);

    @Query(value = "select app, uri, count(uri) " +
            "from stats " +
            "where timestamp > ?1 and timestamp <= ?2 and uri = ?3 ", nativeQuery = true)
    ViewStats getOneUnique(LocalDateTime start, LocalDateTime end, String uri);

    @Query(value = "select app, uri, count(uri) " +
            "from stats " +
            "where timestamp > ?1 and timestamp <= ?2 and uri = ?3", nativeQuery = true)
    ViewStats getOneNonUnique(LocalDateTime start, LocalDateTime end, String uri);


}