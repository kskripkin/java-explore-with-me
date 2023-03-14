package ru.practicum.server.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.model.EndpointHit;

public interface StatsDaoEndpointHit extends JpaRepository<EndpointHit, Long> {
}
