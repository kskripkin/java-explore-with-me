package ru.practicum.priv.requests;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.model.request.Request;

import java.util.List;

public interface RequestsRepository extends JpaRepository<Request, Long> {

    @Query(value = "select id " +
            "from requests " +
            "where requester = ?1 ", nativeQuery = true)
    List<Long> getRequestsIdByRequesterId(long requester);

    @Query(value = "select * " +
            "from requests " +
            "where event = ?1 ", nativeQuery = true)
    List<Request> getByIdCurrentUser(long eventId);

    @Modifying
    @Query(value = "update requests set status = ?3 " +
            "where id in (?2) and event = ?1 ", nativeQuery = true)
    void saveAllStatus(long eventId, List<Long> requestsId, String status);

    @Query(value = "select * " +
            "from requests " +
            "where status = ?1 and event = ?2 ", nativeQuery = true)
    List<Request> getRequestsByEventIdAndStatus(String status, long eventId);

    @Query(value = "select r.id, r.created, r.event, r.requester, r.status " +
            "from requests as r " +
            "join events as e on r.event = e.id " +
            "where r.requester = ?1 and e.initiator != ?1 ", nativeQuery = true)
    List<Request> getByRequesterId(long userId);

    @Query(value = "select * " +
            "from requests " +
            "where requester = ?1 and event = ?2 ", nativeQuery = true)
    List<Request> getRequestFromUserByEvent(long userId, long eventId);

}
