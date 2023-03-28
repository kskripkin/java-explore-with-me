package ru.practicum.mapper;

import org.mapstruct.*;
import ru.practicum.model.events.Event;
import ru.practicum.model.events.UpdateEventAdminRequest;

//@Mapper
public interface CustomerMapper {


//    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
//            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
//    @Mapping(target = "location", source = "updateEventAdminRequest.location.id")
//    Event updateEventFromUpdateEventAdminRequest(UpdateEventAdminRequest updateEventAdminRequest);
    Event updateEventFromUpdateEventAdminRequest(UpdateEventAdminRequest updateEventAdminRequest,
                                                /*@MappingTarget*/ Event event);

//    @org.mapstruct.Condition
//    default boolean isNotNull(Integer value) {
//        return !(value == 0);
//    }
}
