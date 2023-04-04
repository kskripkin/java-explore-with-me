package ru.practicum.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.practicum.exception.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiError handleValidationException(final ValidationException e) {
        return new ApiError(
                new ArrayList<>(List.of(e.toString().split("\n"))),
                e.getMessage(),
                "Incorrectly made request.",
                HttpStatus.BAD_REQUEST.toString(),
                LocalDateTime.now()
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError handleUserNotFoundException(final NotFoundException e) {
        return new ApiError(
                new ArrayList<>(List.of(e.toString().split("\n"))),
                e.getMessage(),
                "For the requested operation the conditions are not met.",
                HttpStatus.NOT_FOUND.toString(),
                LocalDateTime.now()
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public ApiError handleConflictException(final ConflictException e) {
        return new ApiError(
                new ArrayList<>(List.of(e.toString().split("\n"))),
                e.getMessage(),
                "For the requested operation the conditions are not met.",
                HttpStatus.CONFLICT.toString(),
                LocalDateTime.now()
        );
    }

    @org.springframework.web.bind.annotation.ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiError handleThrowable(final Throwable e) {
        log.error("Unexpected error: " + e.getMessage());
        return new ApiError(
                new ArrayList<>(List.of(e.toString().split("\n"))),
                e.getMessage(),
                "For the requested operation the conditions are not met.",
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                LocalDateTime.now()
        );
    }
}
