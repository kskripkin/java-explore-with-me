package ru.practicum.priv.events;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.practicum.model.events.*;
import ru.practicum.model.request.ParticipationRequestDto;
import ru.practicum.model.request.RequestRqDto;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users")
public class EventController {

    private final EventService eventService;

    @GetMapping("/{userId}/events")
    public List<EventShortDto> getEvents(@PathVariable long userId,
                                         @RequestParam(name = "from", required = false, defaultValue = "0") Integer from,
                                         @RequestParam(value= "size", required = false, defaultValue = "10") Integer size
    ) {
        log.info("GET /users/{}/event?from={}&size={}", userId, from, size);
        return eventService.getEvents(userId, from, size);
    }

    @PostMapping("/{userId}/events")
    @ResponseStatus(value = HttpStatus.CREATED)
    public EventFullDto addEvent(@PathVariable long userId,
                                @RequestBody NewEventDto newEventDto) {
        log.info("POST /users/{}/event {}", userId, newEventDto);
        return eventService.addEvent(userId, newEventDto);
    }

    @GetMapping("/{userId}/events/{eventId}")
    public EventFullDto getEvent(@PathVariable long userId,
                                 @PathVariable long eventId
    ) {
        log.info("GET /users/{}/event/{}", userId, eventId);
        return eventService.getEvent(userId, eventId);
    }

    @PatchMapping("/{userId}/events/{eventId}")
    public EventFullDto editEvent(@PathVariable long userId,
                              @PathVariable long eventId,
                              @RequestBody UpdateEventUserRequest event
    ) {
        log.info("PATCH /users/{}/event/{} {}", userId, eventId, event);
        return eventService.editEvent(userId, eventId, event);
    }

    @GetMapping("/{userId}/events/{eventId}/requests")
    public List<ParticipationRequestDto> getRequests(@PathVariable long userId,
                                               @PathVariable long eventId
    ) {
        log.info("GET /users/{}/event/{}/requests", userId, eventId);
        return eventService.getRequests(userId, eventId);
    }

    @PatchMapping("/{userId}/events/{eventId}/requests")
    public EventRequestStatusUpdateResult editRequests(@PathVariable long userId,
                                     @PathVariable long eventId,
                                     @RequestBody EventRequestStatusUpdateRequest requestRqDto
    ) {
        log.info("PATCH /users/{}/event/{}/requests {}", userId, eventId, requestRqDto);
        return eventService.editRequests(userId, eventId, requestRqDto);
    }
}
