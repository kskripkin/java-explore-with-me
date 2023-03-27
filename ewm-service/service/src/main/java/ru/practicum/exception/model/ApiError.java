package ru.practicum.exception.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

    List<String> errors;

    String message;

    String reason;

    String status;

    LocalDateTime timestamp;
}
