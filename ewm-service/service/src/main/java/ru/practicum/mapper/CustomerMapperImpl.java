package ru.practicum.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.practicum.admin.events.LocationsRepository;
import ru.practicum.model.events.Event;
import ru.practicum.model.events.Location;
import ru.practicum.model.events.UpdateEventAdminRequest;
import ru.practicum.model.events.UpdateEventUserRequest;

@Service
@RequiredArgsConstructor
public class CustomerMapperImpl {

    private final LocationsRepository locationsRepository;


    public Event updateEventFromUpdateEventAdminRequest(UpdateEventAdminRequest updateEventAdminRequest, Event event) {
        if (updateEventAdminRequest == null) {
            return event;
        }

        if (updateEventAdminRequest.getLocation() != null) {
            locationsRepository.save(new Location(
                    event.getLocation(),
                    updateEventAdminRequest.getLocation().getLat(),
                    updateEventAdminRequest.getLocation().getLon()
            ));
        }
        if ( updateEventAdminRequest.getId() != 0 ) {
            event.setId( updateEventAdminRequest.getId() );
        }
        if ( updateEventAdminRequest.getAnnotation() != null ) {
            event.setAnnotation( updateEventAdminRequest.getAnnotation() );
        }
        if ( updateEventAdminRequest.getCategory() != 0 ) {
            event.setCategory( updateEventAdminRequest.getCategory() );
        }
        if ( updateEventAdminRequest.getDescription() != null ) {
            event.setDescription( updateEventAdminRequest.getDescription() );
        }
        if ( updateEventAdminRequest.getEventDate() != null ) {
            event.setEventDate( updateEventAdminRequest.getEventDate() );
        }
        if ( updateEventAdminRequest.getPaid() != null ) {
            event.setPaid( updateEventAdminRequest.getPaid() );
        }
        if ( updateEventAdminRequest.getParticipantLimit() != 0 ) {
            event.setParticipantLimit( updateEventAdminRequest.getParticipantLimit() );
        }
        if ( updateEventAdminRequest.getPaid() != null ) {
            event.setRequestModeration( updateEventAdminRequest.getRequestModeration() );
        }
        if ( updateEventAdminRequest.getStateAction() != null ) {
            event.setStateAction( updateEventAdminRequest.getStateAction() );
        }
        if ( updateEventAdminRequest.getTitle() != null ) {
            event.setTitle( updateEventAdminRequest.getTitle() );
        }

        return event;
    }

    public Event updateEventFromUpdateEventUserRequest(UpdateEventUserRequest updateEventAdminRequest, Event event) {
        if (updateEventAdminRequest == null) {
            return event;
        }

        if (updateEventAdminRequest.getLocation() != null) {
            locationsRepository.save(new Location(
                    event.getLocation(),
                    updateEventAdminRequest.getLocation().getLat(),
                    updateEventAdminRequest.getLocation().getLon()
            ));
        }
        if ( updateEventAdminRequest.getAnnotation() != null ) {
            event.setAnnotation( updateEventAdminRequest.getAnnotation() );
        }
        if ( updateEventAdminRequest.getCategory() != 0 ) {
            event.setCategory( updateEventAdminRequest.getCategory() );
        }
        if ( updateEventAdminRequest.getDescription() != null ) {
            event.setDescription( updateEventAdminRequest.getDescription() );
        }
        if ( updateEventAdminRequest.getEventDate() != null ) {
            event.setEventDate( updateEventAdminRequest.getEventDate() );
        }
        if ( updateEventAdminRequest.getPaid() != null ) {
            event.setPaid( updateEventAdminRequest.getPaid() );
        }
        if ( updateEventAdminRequest.getParticipantLimit() != null ) {
            event.setParticipantLimit( updateEventAdminRequest.getParticipantLimit() );
        }
        if ( updateEventAdminRequest.getPaid() != null ) {
            event.setRequestModeration( updateEventAdminRequest.getRequestModeration() );
        }
        if ( updateEventAdminRequest.getStateAction() != null ) {
            event.setStateAction( updateEventAdminRequest.getStateAction() );
        }
        if ( updateEventAdminRequest.getTitle() != null ) {
            event.setTitle( updateEventAdminRequest.getTitle() );
        }

        return event;
    }
}
