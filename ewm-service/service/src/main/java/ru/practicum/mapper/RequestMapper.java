package ru.practicum.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.practicum.model.request.ParticipationRequestDto;
import ru.practicum.model.request.Request;

@Component
@RequiredArgsConstructor
public class RequestMapper {

    public ParticipationRequestDto requestToParticipationRequestDto(Request request) {
        return new ParticipationRequestDto(
                request.getId(),
                request.getCreated(),
                request.getEvent(),
                request.getRequester(),
                request.getStatus()
        );
    }
}
