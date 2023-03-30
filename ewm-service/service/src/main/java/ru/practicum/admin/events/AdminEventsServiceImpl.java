package ru.practicum.admin.events;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.mapper.CustomerMapperImpl;
import ru.practicum.mapper.EventMapper;
import ru.practicum.model.events.EventFullDto;
import ru.practicum.model.events.UpdateEventAdminRequest;
import ru.practicum.model.events.Event;
import ru.practicum.pub.events.EventRepository;
import ru.practicum.validation.ValidateEvents;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminEventsServiceImpl implements AdminEventsService {

    private final EventRepository eventRepository;

    private final EventMapper eventMapper;

    private final CustomerMapperImpl customerMapper;

    private final ValidateEvents validateEvents;

    @Override
    public List<EventFullDto> getEvents(String[] users, String[] states, String[] categories, String rangeStart, String rangeEnd, Integer from, Integer size) {
        return eventRepository.getEvents(users, states, categories, rangeStart, rangeEnd, PageRequest.of((from / size), size))
                .stream().map(x -> eventMapper.eventToEventFullDto(x)).collect(Collectors.toList());
    }

    @Override
    public EventFullDto editEvent(Long eventId, UpdateEventAdminRequest updateEventAdminRequest) {
        System.out.println("1          " + eventRepository.findAll());
        validateEvents.findEvent(eventId);
        validateEvents.validateDate(updateEventAdminRequest.getEventDate());
        Event event = eventRepository.getById(eventId);
        Event eventEdit = customerMapper.updateEventFromUpdateEventAdminRequest(updateEventAdminRequest, event);
        eventEdit.setId(eventId);
        switch (updateEventAdminRequest.getStateAction()) {
            case "PUBLISH_EVENT":
                validateEvents.validatePublish(eventId, "PUBLISH_EVENT");
                eventEdit.setStateAction("PUBLISHED");
                break;
            case "REJECT_EVENT":
                validateEvents.validatePublish(eventId, "REJECT_EVENT");
                eventEdit.setStateAction("CANCELED");
                break;
        }
//        if (updateEventAdminRequest.getStateAction().equals("PUBLISH_EVENT")) {
//            validateEvents.validatePublish(eventId);
//            eventEdit.setStateAction("PUBLISHED");
//        }
//        if (updateEventAdminRequest.getStateAction().equals("REJECT_EVENT")) {
//            eventEdit.setStateAction("CANCELED");
//        }
        System.out.println("3      " + eventEdit);
        return eventMapper.eventToEventFullDto(eventRepository.save(eventEdit));
    }
}
