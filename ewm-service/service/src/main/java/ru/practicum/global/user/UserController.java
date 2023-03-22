package ru.practicum.global.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.model.events.EventDto;
import ru.practicum.dto.RequestRsDto;
import ru.practicum.model.events.Event;
import ru.practicum.model.request.RequestRqDto;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}/events")
    public List<EventDto> getEvents(@PathVariable long userId,
                                    @RequestParam(name = "from", required = false, defaultValue = "0") Integer from,
                                    @RequestParam(value= "size", required = false, defaultValue = "10") Integer size
    ) {
        log.info("GET /users/{}/event?from={}&size={}", userId, from, size);
        return userService.getEvents(userId, from, size);
    }

    @PostMapping("/{userId}/events")
    public EventDto addEvent(@PathVariable long userId,
                             @RequestBody Event event) {
        log.info("POST /users/{}/event {}", userId, event);
        return userService.addEvent(userId, event);
    }

    @GetMapping("/{userId}/events/{eventId}")
    public EventDto getEvent(@PathVariable long userId,
                             @PathVariable long eventId
    ) {
        log.info("GET /users/{}/event/{}", userId, eventId);
        return userService.getEvent(userId, eventId);
    }

    @PatchMapping("/{userId}/events/{eventId}")
    public EventDto editEvent(@PathVariable long userId,
                              @PathVariable long eventId,
                              @RequestBody Event event
    ) {
        log.info("PATCH /users/{}/event/{} {}", userId, eventId, event);
        return userService.editEvent(userId, eventId, event);
    }

    @GetMapping("/{userId}/events/{eventId}/requests")
    public EventDto getRequests(@PathVariable long userId,
                                @PathVariable long eventId
    ) {
        log.info("GET /users/{}/event/{}/requests", userId, eventId);
        return userService.getRequests(userId, eventId);
    }

    @PatchMapping("/{userId}/events/{eventId}/requests")
    public RequestRsDto editRequests(@PathVariable long userId,
                                     @PathVariable long eventId,
                                     @RequestBody RequestRqDto requestRqDto
    ) {
        log.info("PATCH /users/{}/event/{}/requests {}", userId, eventId, requestRqDto);
        return userService.editRequests(userId, eventId, requestRqDto);
    }
}
