package ru.practicum.pub.events;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.model.events.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(value = "select c.id,c.annotation,c.category,c.confirmed_requests,c.created_on,c.description,c.event_date,c.initiator,c.location,c.paid,c.participant_limit,c.published_on,c.request_moderation,c.state_action,c.title,c.views " +
            "from events as c " +
            "join events_to_compilation as etc on etc.id_event = c.id " +
            "join compilations as e on etc.id_compilation = e.id " +
            "where e.id = ?1 ", nativeQuery = true)
    List<Event> getEvents(long compId);

    @Query(value = "select * " +
            "from events " +
            "where category = ?1 ", nativeQuery = true)
    List<Event> getEventsByCategory(long catId);

    @Query(value = "select * " +
            "from events " +
            "where initiator = ?1 ", nativeQuery = true)
    List<Event> getEvents(long userId, Pageable pageable);

    @Query(value = "select * " +
            "from events " +
            "where initiator in (?1) and state_action in (?2) and category in (?3) and event_date >= ?4 and event_date <= ?5 ", nativeQuery = true)
    List<Event> getEvents(Long[] users, String[] states, Long[] categories, LocalDateTime rangeStart, LocalDateTime rangeEnd, Pageable pageable);

    @Query(value = "select * " +
            "from events " +
            "where initiator in (?1) and category in (?2) ", nativeQuery = true)
    List<Event> getEvents(Long[] users, Long[] categories, Pageable pageable);

    @Query(value = "select * " +
            "from events " +
            "where (annotation ilike %?1% or description ilike %?1%) and category in (?2) and paid = ?3 and event_date >= ?4 and event_date <= ?5 " +
            "order by event_date ", nativeQuery = true)
    List<Event> getEventsSortEventDate(String text, Long[] categories, boolean paid, LocalDateTime rangeStart, LocalDateTime rangeEnd, Pageable pageable);

    @Query(value = "select * " +
            "from events " +
            "where (annotation ilike %?1% or description ilike %?1%) and category in (?2) and paid = ?3 and event_date >= ?4 and event_date <= ?5 " +
            "order by views ", nativeQuery = true)
    List<Event> getEventsSortViews(String text, Long[] categories, boolean paid, LocalDateTime rangeStart, LocalDateTime rangeEnd, Pageable pageable);

    @Query(value = "select * " +
            "from events " +
            "where (annotation ilike %?1% or description ilike %?1%) and paid = ?2 and event_date >= ?3 and event_date <= ?4 " +
            "order by event_date ", nativeQuery = true)
    List<Event> getEventsSortEventDate(String text, boolean paid, LocalDateTime rangeStart, LocalDateTime rangeEnd, Pageable pageable);

    @Query(value = "select * " +
            "from events " +
            "where (annotation ilike %?1% or description ilike %?1%) and paid = ?2 and event_date >= ?3 and event_date <= ?4 " +
            "order by views ", nativeQuery = true)
    List<Event> getEventsSortViews(String text, boolean paid, LocalDateTime rangeStart, LocalDateTime rangeEnd, Pageable pageable);


}
