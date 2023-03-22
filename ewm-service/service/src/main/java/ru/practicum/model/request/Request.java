package ru.practicum.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(nullable = false)
    LocalDateTime created;

    @Column(nullable = false)
    long event;

    @Column(nullable = false)
    long requester;

    @Column(nullable = false)
    String status;

}
