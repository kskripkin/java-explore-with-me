package ru.practicum.adminEvents;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.compilations.model.Category;
import ru.practicum.compilations.model.Compilation;
import ru.practicum.compilations.model.EventDto;
import ru.practicum.user.model.Event;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/admin/events")
public class AdminEventsController {

    private final AdminEventsService adminEventsService;

    @GetMapping
    public List<EventDto> getEvents(@RequestParam(value= "users", required = false) String[] users,
                                         @RequestParam(value= "states", required = false) String[] states,
                                         @RequestParam(value= "categories", required = false) String[] categories,
                                         @RequestParam(value= "rangeStart", required = false) String rangeStart,
                                         @RequestParam(value= "rangeEnd", required = false) String rangeEnd,
                                         @RequestParam(name = "from", required = false, defaultValue = "0") Integer from,
                                         @RequestParam(value= "size", required = false, defaultValue = "10") Integer size) {
        log.info("GET /admin/events?users={}&states={}&categories={}&rangeStart={}&rangeEnd={}&from={}&size={}",
                users, states, categories, rangeStart, rangeEnd, from, size);
        return adminEventsService.getEvents(users, states, categories, rangeStart, rangeEnd, from, size);
    }

    @PatchMapping("/{eventId}")
    public EventDto editEvent(@PathVariable Integer eventId,
                              @RequestBody Event event) {
        log.info("PATCH /admin/events/{}", eventId);
        return adminEventsService.editCategory(eventId, event);
    }
}
