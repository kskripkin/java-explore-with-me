package ru.practicum.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.model.ViewStats;

public interface StatsDaoViewStats extends JpaRepository<ViewStats, Long> {
    @Query(value = "select * " +
            "from items " +
            "where available = true and (name ilike %?1% or description ilike %?1%)", nativeQuery = true)
    ViewStats getStats(String start, String end, String[] uris, boolean unique);
}
