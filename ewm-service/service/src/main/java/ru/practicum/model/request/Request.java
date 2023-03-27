package ru.practicum.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "requests")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
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
