package ru.practicum.mapper;

import org.mapstruct.*;
import ru.practicum.model.events.Event;
import ru.practicum.model.events.UpdateEventAdminRequest;

@Mapper
public interface CustomerMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
            nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
    void updateEventFromUpdateEventAdminRequest(UpdateEventAdminRequest updateEventAdminRequest,
                                                                @MappingTarget Event event);
}
