package ru.practicum.priv.requests;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.model.request.Request;

import java.util.List;

public interface RequestsRepository extends JpaRepository<Request, Long> {

    @Query(value = "select id " +
            "from requests " +
            "where requester = ?1 ", nativeQuery = true)
    List<Long> getRequestsIdByRequesterId(long requester);

}
