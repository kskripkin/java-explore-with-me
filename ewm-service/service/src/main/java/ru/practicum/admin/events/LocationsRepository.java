package ru.practicum.admin.events;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.model.events.Location;

public interface LocationsRepository extends JpaRepository<Location, Long> {
}