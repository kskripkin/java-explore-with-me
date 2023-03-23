package ru.practicum.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.model.events.EventRequestStatusUpdateRequest;
import ru.practicum.model.request.ParticipationRequestDto;
import ru.practicum.model.request.Request;
import ru.practicum.priv.requests.RequestsRepository;

@Component
@RequiredArgsConstructor
public class RequestMapper {

    private final RequestsRepository requestsRepository;

    public ParticipationRequestDto RequestToParticipationRequestDto(Request request) {
        return new ParticipationRequestDto(
                request.getId(),
                request.getCreated(),
                request.getEvent(),
                request.getRequester(),
                request.getStatus()
        );
    }

    public EventRequestStatusUpdateRequest RequestToEventRequestStatusUpdateRequest(Request request) {
        return new EventRequestStatusUpdateRequest(
                requestsRepository.getRequestsIdByRequesterId(request.getRequester()),
                request.getStatus()
        );
    }
}
