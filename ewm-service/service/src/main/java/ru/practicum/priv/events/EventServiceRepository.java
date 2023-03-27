package ru.practicum.priv.events;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.model.events.Event;

import java.util.List;

public interface EventServiceRepository extends JpaRepository<Event, Long> {

    @Query(value = "select * " +
            "from events " +
            "where initiator = ?1 ", nativeQuery = true)
    List<Event> getEvents(long userId, Pageable pageable);

    @Query(value = "select * " +
            "from events " +
            "where initiator in (?1) and state_action in (?2) and category in (?3) and event_date >= ?4 and event_date >= ?5 ", nativeQuery = true)
    List<Event> getEvents(String[] users, String[] states, String[] categories, String rangeStart, String rangeEnd, Pageable pageable);

    @Query(value = "select * " +
            "from events " +
            "where (annotation ilike %?1% or description ilike %?1%) and category in (?2) and paid = ?3 and event_date >= ?4 and event_date >= ?5 " +
            "order by event_date ", nativeQuery = true)
    List<Event> getEventsSortEventDate(String text, String[] categories, boolean paid, String rangeStart, String rangeEnd, Pageable pageable);

    @Query(value = "select * " +
            "from events " +
            "where (annotation ilike %?1% or description ilike %?1%) and category in (?2) and paid = ?3 and event_date >= ?4 and event_date >= ?5 " +
            "order by views ", nativeQuery = true)
    List<Event> getEventsSortViews(String text, String[] categories, boolean paid, String rangeStart, String rangeEnd, Pageable pageable);

}
