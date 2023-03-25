package ru.practicum.priv.requests;

import ru.practicum.model.request.ParticipationRequestDto;
import ru.practicum.model.request.Request;

import java.util.List;

public interface RequestsService {

    List<ParticipationRequestDto> getRequests(Long userId);

    ParticipationRequestDto addRequests(Long userId,
                                        Long eventId);

    ParticipationRequestDto cancelRequests(Long userId,
                                           Long requestId);
}
