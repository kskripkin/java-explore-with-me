package ru.practicum.validation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.practicum.exception.model.ValidationException;

@Slf4j
@RequiredArgsConstructor
@Component
public class ValidateEvents {

    public void getEvents(String text, String[] categories, boolean paid, String rangeStart, String rangeEnd, boolean onlyAvailable, String sort, Integer from, Integer size) {
//        if (text == null) {
//            throw new ValidationException("Text is empty");
//        }
//        if (rangeStart == null) {
//            throw new ValidationException("rangeStart is empty");
//        }
//        if (rangeEnd == null) {
//            throw new ValidationException("rangeEnd is empty");
//        }
//        if (sort == null) {
//            throw new ValidationException("sort is empty");
//        }
    }

    public void findEvent(Long eventId) {
        if (eventId == null || eventId == 0) {
            throw new ValidationException("eventId not found");
        }
    }
}
