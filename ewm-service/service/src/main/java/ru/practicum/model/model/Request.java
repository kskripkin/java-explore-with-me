package ru.practicum.model.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request {

    LocalDateTime created;

    long event;

    long id;

    long requester;

    String status;

}
