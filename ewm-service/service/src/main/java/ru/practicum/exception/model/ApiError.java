package ru.practicum.exception.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class ApiError {

    List<String> errors;

    String message;

    String reason;

    String status;

    LocalDateTime timestamp;
}
