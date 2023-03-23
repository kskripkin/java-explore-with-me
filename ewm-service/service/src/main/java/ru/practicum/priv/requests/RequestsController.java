package ru.practicum.priv.requests;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.model.request.Request;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class RequestsController {

    private final RequestsService requestsService;

    @GetMapping("/{userId}/requests")
    public List<Request> getUser(@PathVariable Integer userId) {
        log.info("GET /users/{}/requests", userId);
        return requestsService.getUser(userId);
    }

    @PostMapping("/{userId}/requests")
    public Request addRequests(@PathVariable Integer userId,
                                 @RequestParam Integer eventId) {
        log.info("GET /users/{}/requests?eventId={}", userId, eventId);
        return requestsService.addRequests(userId, eventId);
    }

    @PatchMapping("/{userId}/requests/{requestId}/cancel")
    public Request cancelRequests(@PathVariable Integer userId,
                                     @PathVariable Integer requestId) {
        log.info("GET /users/{}/requests/{}/cancel", userId, requestId);
        return requestsService.cancelRequests(userId, requestId);
    }
}
