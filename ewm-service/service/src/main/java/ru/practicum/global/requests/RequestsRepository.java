package ru.practicum.global.requests;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.model.model.Request;

public interface RequestsRepository extends JpaRepository<Request, Long> {
}