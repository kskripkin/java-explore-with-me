package ru.practicum.events;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.user.model.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}
