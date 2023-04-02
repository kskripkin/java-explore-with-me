package ru.practicum.model.events;


import javax.persistence.*;

@Entity
@Table(name = "events_to_compilation")
public class EventsToCompilation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "id_compilation", nullable = false)
    private long idCompilation;

    @Column(name = "id_event",nullable = false)
    private long idEvent;

    public EventsToCompilation(long idCompilation, long idEvent) {
        this.idCompilation = idCompilation;
        this.idEvent = idEvent;
    }
}
