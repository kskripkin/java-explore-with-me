package ru.practicum.admin.events;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.model.events.EventFullDto;
import ru.practicum.model.events.UpdateEventAdminRequest;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/admin/events")
public class AdminEventsController {

    private final AdminEventsService adminEventsService;

    @GetMapping
    public List<EventFullDto> getEvents(@RequestParam(value= "users", required = false) Long[] users,
                                        @RequestParam(value= "states", required = false) String[] states,
                                        @RequestParam(value= "categories", required = false) Long[] categories,
                                        @RequestParam(value= "rangeStart", required = false) String rangeStart,
                                        @RequestParam(value= "rangeEnd", required = false) String rangeEnd,
                                        @RequestParam(name = "from", required = false, defaultValue = "0") Integer from,
                                        @RequestParam(value= "size", required = false, defaultValue = "10") Integer size) {
        log.info("GET /admin/events?users={}&states={}&categories={}&rangeStart={}&rangeEnd={}&from={}&size={}",
                users, states, categories, rangeStart, rangeEnd, from, size);
        return adminEventsService.getEvents(users, states, categories, rangeStart, rangeEnd, from, size);
    }

    @PatchMapping("/{eventId}")
    public EventFullDto editEvent(@PathVariable Long eventId,
                              @RequestBody UpdateEventAdminRequest event) {
        log.info("PATCH /admin/events/{} {}", eventId, event);
        return adminEventsService.editEvent(eventId, event);
    }
}
