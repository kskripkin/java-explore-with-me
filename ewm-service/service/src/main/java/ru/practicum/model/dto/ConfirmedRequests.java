package ru.practicum.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfirmedRequests {

    private LocalDateTime created;

    private long event;

    private long id;

    private long requester;

    private String status;
}
