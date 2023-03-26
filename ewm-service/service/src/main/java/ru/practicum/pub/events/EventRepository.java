package ru.practicum.pub.events;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.model.compilations.Compilation;
import ru.practicum.model.events.Event;

import java.util.List;

public interface EventRepository extends JpaRepository<Event, Long> {

    @Query(value = "select c.id,c.annotation,c.category,c.confirmed_requests,c.created_on,c.description,c.event_date,c.initiator,c.location,c.paid,c.participant_limit,c.published_on,c.request_moderation,c.state_action,c.title,c.views " +
            "from compilations as c " +
            "join events_to_compilation as etc on etc.id_compilation = c.id " +
            "join events as e on etc.id_event = e.id " +
            "where c.id = ?1 ", nativeQuery = true)
    List<Event> getEvents(long compId);

}
