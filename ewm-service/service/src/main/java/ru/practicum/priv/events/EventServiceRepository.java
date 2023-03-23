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



}
