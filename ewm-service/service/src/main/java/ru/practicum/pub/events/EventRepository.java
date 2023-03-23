package ru.practicum.pub.events;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.model.events.Event;

public interface EventRepository extends JpaRepository<Event, Long> {
}