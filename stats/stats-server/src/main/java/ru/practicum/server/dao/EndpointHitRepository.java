package ru.practicum.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.practicum.model.ViewStats;
import ru.practicum.server.model.EndpointHit;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EndpointHitRepository extends JpaRepository<EndpointHit, Long> {

    @Query("SELECT new ru.practicum.model.ViewStats (s.app, s.uri, count(s.ip) as cnt) FROM EndpointHit as s where s.timestamp > ?1 and s.timestamp <= ?2 and s.uri in (?3) group by s.uri, s.app order by cnt desc")
    List<ViewStats> getAll(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query("SELECT new ru.practicum.model.ViewStats (s.app, s.uri, count(s.ip) as cnt) FROM EndpointHit as s where s.timestamp > ?1 and s.timestamp <= ?2 group by s.uri, s.app order by cnt desc")
    List<ViewStats> getAll(LocalDateTime start, LocalDateTime end);

    @Query("SELECT new ru.practicum.model.ViewStats (s.app, s.uri, count(distinct s.ip) as cnt) FROM EndpointHit as s where s.timestamp > ?1 and s.timestamp <= ?2 and s.uri in (?3) group by s.uri, s.app order by cnt desc")
    List<ViewStats> getAllUniqueIp(LocalDateTime start, LocalDateTime end, List<String> uris);

    @Query("SELECT new ru.practicum.model.ViewStats (s.app, s.uri, count(distinct s.ip) as cnt) FROM EndpointHit as s where s.timestamp > ?1 and s.timestamp <= ?2 group by s.uri, s.app order by cnt desc")
    List<ViewStats> getAllUniqueIp(LocalDateTime start, LocalDateTime end);
}