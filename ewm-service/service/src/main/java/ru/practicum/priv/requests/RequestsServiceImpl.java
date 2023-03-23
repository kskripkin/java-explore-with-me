package ru.practicum.priv.requests;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.model.request.Request;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RequestsServiceImpl implements RequestsService {

    private final RequestsRepository requestsRepository;

    @Override
    public List<Request> getUser(Integer userId) {
        return requestsRepository.getById(userId);
    }

    @Override
    public Request addRequests(Integer userId, Integer eventId) {
        return requestsRepository.addRequests(userId, eventId);
    }

    @Override
    public Request cancelRequests(Integer userId, Integer requestId) {
        return requestsRepository.cancelRequests(userId, requestId);
    }
}
