package ru.practicum.pub.events;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.model.events.EventFullDto;
import ru.practicum.model.events.EventShortDto;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/events")
public class EventsController {

    private final EventsService eventsService;

    @GetMapping
    public List<EventShortDto> getEvents(@RequestParam(value= "text", required = false, defaultValue = "") String text,
                                         @RequestParam(value= "categories", required = false) String[] categories,
                                         @RequestParam(value= "paid", required = false, defaultValue = "false") boolean paid,
                                         @RequestParam(value= "rangeStart", required = false, defaultValue = "1900-01-01 00:00:00") String rangeStart,
                                         @RequestParam(value= "rangeEnd", required = false, defaultValue = "2900-01-01 00:00:00") String rangeEnd,
                                         @RequestParam(value= "onlyAvailable", required = false, defaultValue = "false") boolean onlyAvailable,
                                         @RequestParam(value= "sort", required = false, defaultValue = "EVENT_DATE") String sort,
                                         @RequestParam(name = "from", required = false, defaultValue = "0") Integer from,
                                         @RequestParam(value= "size", required = false, defaultValue = "10") Integer size) {
        log.info("GET /events?text={}&categories={}&paid={}&rangeStart={}&rangeEnd={}&onlyAvailable={}&sort={}&from={}&size={}",
                text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size);
        return eventsService.getEvents(text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size);
    }

    @GetMapping("/{eventId}")
    public EventFullDto editEvent(@PathVariable Long eventId) {
        log.info("PATCH /events/{}", eventId);
        return eventsService.editEvent(eventId);
    }
}
