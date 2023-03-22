package ru.practicum.global.requests;

import ru.practicum.model.request.Request;

import java.util.List;

public interface RequestsService {

    List<Request> getUser(Integer userId);

    Request addRequests(Integer userId,
                        Integer eventId);

    Request cancelRequests(Integer userId,
                           Integer requestId);
}
