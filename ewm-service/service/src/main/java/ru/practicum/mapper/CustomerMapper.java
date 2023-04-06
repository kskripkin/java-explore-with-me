package ru.practicum.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.model.comment.Comment;
import ru.practicum.model.compilations.Compilation;
import ru.practicum.model.compilations.UpdateCompilationRequest;
import ru.practicum.model.events.Event;
import ru.practicum.model.events.UpdateEventAdminRequest;
import ru.practicum.model.events.UpdateEventUserRequest;

@Service
@RequiredArgsConstructor
public class CustomerMapper {

    public Event updateEventFromUpdateEventAdminRequest(UpdateEventAdminRequest updateEventAdminRequest, Event event, long locationId) {
        if (updateEventAdminRequest == null) {
            return event;
        }

        if (updateEventAdminRequest.getLocation() != null) {
            event.setLocation(locationId);
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
        if ( updateEventAdminRequest.getTitle() != null ) {
            event.setTitle( updateEventAdminRequest.getTitle() );
        }

        return event;
    }

    public Event updateEventFromUpdateEventUserRequest(UpdateEventUserRequest updateEventAdminRequest, Event event, long locationId) {
        if (updateEventAdminRequest == null) {
            return event;
        }

        if (updateEventAdminRequest.getLocation() != null) {
            event.setLocation(locationId);
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

    public Compilation updateEventFromUpdateCompilationRequest(UpdateCompilationRequest updateCompilationRequest, Compilation compilation) {
        if (updateCompilationRequest == null) {
            return compilation;
        }

        if (updateCompilationRequest.getPinned() != null) {
            compilation.setPinned(updateCompilationRequest.getPinned());
        }
        if (updateCompilationRequest.getTitle() != null) {
            compilation.setTitle(updateCompilationRequest.getTitle());
        }
        return compilation;
    }

    public Comment updateComment(Comment sourceComment, Comment comment) {
        if (comment == null) {
            return sourceComment;
        }

        if (comment.getText() != null) {
            sourceComment.setText(comment.getText());
        }
        if (comment.getCreated() != null) {
            sourceComment.setCreated(comment.getCreated());
        }
        if (comment.getAuthorId() != null) {
            sourceComment.setAuthorId(comment.getAuthorId());
        }
        if (comment.getEventId() != null) {
            sourceComment.setEventId(comment.getEventId());
        }
        return sourceComment;
    }
}
