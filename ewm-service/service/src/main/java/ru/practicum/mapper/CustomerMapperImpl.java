package ru.practicum.mapper;

import ru.practicum.model.events.Event;
import ru.practicum.model.events.Location;
import ru.practicum.model.events.UpdateEventAdminRequest;

public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Event updateEventFromUpdateEventAdminRequest(UpdateEventAdminRequest updateEventAdminRequest, Event event) {
        if (updateEventAdminRequest == null) {
            return event;
        }

        if (updateEventAdminRequest.getLocation() != null) {
            event.setLocation(updateEventAdminRequest.getLocation().getId());
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

//    private long updateEventAdminRequestLocationId(UpdateEventAdminRequest updateEventAdminRequest) {
//        if ( updateEventAdminRequest == null ) {
//            return 0L;
//        }
//        Location location = updateEventAdminRequest.getLocation();
//        if ( location == null ) {
//            return 0L;
//        }
//        long id = location.getId();
//        return id;
//    }
}
