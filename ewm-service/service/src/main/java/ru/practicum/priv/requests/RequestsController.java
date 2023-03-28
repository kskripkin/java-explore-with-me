package ru.practicum.priv.requests;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.model.request.ParticipationRequestDto;
import ru.practicum.model.request.Request;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class RequestsController {

    private final RequestsService requestsService;

    @GetMapping("/{userId}/requests")
    public List<ParticipationRequestDto> getRequests(@PathVariable Long userId) {
        log.info("GET /users/{}/requests", userId);
        return requestsService.getRequests(userId);
    }

    @PostMapping("/{userId}/requests")
    @ResponseStatus(value = HttpStatus.CREATED)
    public ParticipationRequestDto addRequests(@PathVariable Long userId,
                                 @RequestParam Long eventId) {
        log.info("GET /users/{}/requests?eventId={}", userId, eventId);
        return requestsService.addRequests(userId, eventId);
    }

    @PatchMapping("/{userId}/requests/{requestId}/cancel")
    public ParticipationRequestDto cancelRequests(@PathVariable Long userId,
                                     @PathVariable Long requestId) {
        log.info("GET /users/{}/requests/{}/cancel", userId, requestId);
        return requestsService.cancelRequests(userId, requestId);
    }
}
