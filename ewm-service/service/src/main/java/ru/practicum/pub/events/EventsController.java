package ru.practicum.pub.events;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.model.events.EventDto;
import ru.practicum.model.events.Event;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/events")
public class EventsController {

    private final EventsService eventsService;

    @GetMapping
    public List<EventDto> getEvents(@RequestParam(value= "text", required = false) String text,
                                    @RequestParam(value= "categories", required = false) String[] categories,
                                    @RequestParam(value= "paid", required = false) boolean paid,
                                    @RequestParam(value= "rangeStart", required = false) String rangeStart,
                                    @RequestParam(value= "rangeEnd", required = false) String rangeEnd,
                                    @RequestParam(value= "onlyAvailable", required = false) boolean onlyAvailable,
                                    @RequestParam(value= "sort", required = false) String sort,
                                    @RequestParam(name = "from", required = false, defaultValue = "0") Integer from,
                                    @RequestParam(value= "size", required = false, defaultValue = "10") Integer size) {
        log.info("GET /events?text={}&categories={}&paid={}&rangeStart={}&rangeEnd={}&onlyAvailable={}&sort={}&from={}&size={}",
                text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size);
        return eventsService.getEvents(text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size);
    }

    @PatchMapping("/{eventId}")
    public EventDto editEvent(@PathVariable Integer eventId,
                              @RequestBody Event event) {
        log.info("PATCH /events/{}", eventId);
        return eventsService.editEvent(eventId, event);
    }
}
