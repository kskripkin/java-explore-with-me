package ru.practicum.priv.requests;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.mapper.RequestMapper;
import ru.practicum.model.events.Event;
import ru.practicum.model.request.ParticipationRequestDto;
import ru.practicum.model.request.Request;
import ru.practicum.pub.events.EventRepository;
import ru.practicum.validation.ValidateEvents;
import ru.practicum.validation.ValidateRequests;
import ru.practicum.validation.ValidateUsers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RequestsServiceImpl implements RequestsService {

    private final RequestsRepository requestsRepository;

    private final EventRepository eventRepository;

    private final RequestMapper requestMapper;

    private final ValidateUsers validateUsers;

    private final ValidateEvents validateEvents;

    private final ValidateRequests validateRequests;

    @Override
    public List<ParticipationRequestDto> getRequests(Long userId) {
        validateUsers.findUser(userId);
        return requestsRepository.getByRequesterId(userId)
                .stream()
                .map(x -> requestMapper.RequestToParticipationRequestDto(x))
                .collect(Collectors.toList());
    }

    @Override
    public ParticipationRequestDto addRequests(Long userId, Long eventId) {
        validateUsers.findUser(userId);
        validateEvents.findEvent(eventId);
        validateRequests.validateRequestFromUser(userId, eventId);
        validateRequests.validateUserOwnerEvent(userId, eventId);
        validateRequests.validatePublishEvent(eventId);
        validateRequests.validateLimitPeoples(eventId);
        Event event = eventRepository.getReferenceById(eventId);
        if (event.getRequestModeration()) {
            return requestMapper.RequestToParticipationRequestDto(
                    requestsRepository.save(new Request(
                            0,
                            LocalDateTime.now(),
                            eventId,
                            userId,
                            "PENDING"
                    ))
            );
        } else {
            Request request = requestsRepository.save(new Request(
                            0,
                            LocalDateTime.now(),
                            eventId,
                            userId,
                            "APPROVED"
            ));
            event.setConfirmedRequests(event.getConfirmedRequests() + 1);
            eventRepository.save(event);
            return requestMapper.RequestToParticipationRequestDto(request);
        }
    }

    @Override
    public ParticipationRequestDto cancelRequests(Long userId, Long requestId) {
        validateUsers.findUser(userId);
        Request request = requestsRepository.getById(requestId);
        request.setStatus("CANCELED");
        requestsRepository.deleteById(requestId);
        return requestMapper.RequestToParticipationRequestDto(request);
    }
}
