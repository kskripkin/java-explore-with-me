package ru.practicum.requests;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.practicum.user.model.Request;

public interface RequestsRepository extends JpaRepository<Request, Long> {
}
