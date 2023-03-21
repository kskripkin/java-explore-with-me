package ru.practicum.requests;

import ru.practicum.user.model.Request;

import java.util.List;

public interface RequestsService {

    List<Request> getUser(Integer userId);

    Request addRequests(Integer userId,
                        Integer eventId);

    Request cancelRequests(Integer userId,
                           Integer requestId);
}
