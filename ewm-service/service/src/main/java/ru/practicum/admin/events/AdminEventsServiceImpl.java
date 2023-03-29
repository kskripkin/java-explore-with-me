package ru.practicum.admin.events;

import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import ru.practicum.mapper.CustomerMapper;
import ru.practicum.mapper.CustomerMapperImpl;
import ru.practicum.mapper.EventMapper;
import ru.practicum.model.events.EventFullDto;
import ru.practicum.model.events.UpdateEventAdminRequest;
import ru.practicum.priv.events.EventServiceRepository;
import ru.practicum.model.events.Event;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminEventsServiceImpl implements AdminEventsService {

    private final EventServiceRepository eventRepository;
    private final EventMapper eventMapper;

    private final CustomerMapperImpl customerMapper;
//    private CustomerMapper mapper
//            = Mappers.getMapper(CustomerMapperImpl.class);

    @Override
    public List<EventFullDto> getEvents(String[] users, String[] states, String[] categories, String rangeStart, String rangeEnd, Integer from, Integer size) {
        System.out.println(eventRepository.findAll());
        return eventRepository.getEvents(users, states, categories, rangeStart, rangeEnd, PageRequest.of((from / size), size))
                .stream().map(x -> eventMapper.eventToEventFullDto(x)).collect(Collectors.toList());
    }

    @Override
    public EventFullDto editEvent(Long eventId, UpdateEventAdminRequest updateEventAdminRequest) {
        Event event = eventRepository.getById(eventId);
        System.out.println(event);
        //Event event2 = mapper.updateEventFromUpdateEventAdminRequest(updateEventAdminRequest);
        Event event1 = customerMapper.updateEventFromUpdateEventAdminRequest(updateEventAdminRequest, event);
//        System.out.println(event);
//        System.out.println("2222222cccccc         "+event2);
        event1.setId(eventId);
        if (event1.getStateAction().equals("PUBLISH_EVENT")) {
            event1.setStateAction("PUBLISHED");
        }
        System.out.println(event1);
        return eventMapper.eventToEventFullDto(eventRepository.save(event1));
    }
}
