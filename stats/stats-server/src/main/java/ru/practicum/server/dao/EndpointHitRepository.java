package ru.practicum.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.model.ViewStats;
import ru.practicum.server.model.EndpointHit;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface EndpointHitRepository extends JpaRepository<EndpointHit, Long> {


//    @Query(value = "select * from stats s where timestamp > ?1 and timestamp <= ?2 and s.uri in (?3) ", nativeQuery = true)
//    Collection<EndpointHit> getAll(LocalDateTime start, LocalDateTime end, ArrayList<String> uris);

    @Query(value = "select app, uri, count(uri) as hits from stats s where timestamp > ?1 and timestamp <= ?2 and s.uri in (?3) group by uri, app", nativeQuery = true)
    Collection<ViewStats> getAll(LocalDateTime start, LocalDateTime end, ArrayList<String> uris);
    @Query(value = "select * from stats s where timestamp > ?1 and timestamp <= ?2", nativeQuery = true)
    Collection<EndpointHit> getAll(LocalDateTime start, LocalDateTime end);
//    //Доработать на уникальность
//    @Query(value = "select app, uri, count(uri) " +
//            "from stats " +
//            "where timestamp > ?1 and timestamp <= ?2 " +
//            "group by uri, app ", nativeQuery = true)
//    Collection<ViewStats> getAllUnique(LocalDateTime start, LocalDateTime end);
//
//    //Должно работать
//    @Query(value = "select app, uri, count(uri) " +
//            "from stats " +
//            "where timestamp > ?1 and timestamp <= ?2 " +
//            "group by uri, app ", nativeQuery = true)
//    Collection<ViewStats> getAllNonUnique(LocalDateTime start, LocalDateTime end);
//
//    //Доработать на уникальность, чтобы ip не было в выводе
//    @Query(value = "select distinct on(ip) uri, app, count(uri) " +
//            "from stats " +
//            "where timestamp > ?1 and timestamp <= ?2 and uri = ?3 " +
//            "group by uri, app, ip ", nativeQuery = true)
//    ViewStats getOneUnique(LocalDateTime start, LocalDateTime end, String uri);
//
//    //Должно работать
//    @Query(value = "select app, uri, count(uri) " +
//            "from stats " +
//            "where timestamp > ?1 and timestamp <= ?2 and uri = ?3" +
//            "group by uri, app ", nativeQuery = true)
//    ViewStats getOneNonUnique(LocalDateTime start, LocalDateTime end, String uri);


}