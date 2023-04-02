package ru.practicum.admin.events;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.model.events.Location;

import java.util.List;

public interface LocationsRepository extends JpaRepository<Location, Long> {

    @Query(value = "select * " +
            "from locations " +
            "where id in (?1) ", nativeQuery = true)
    List<Location> getAll(List<Long> locations);
}