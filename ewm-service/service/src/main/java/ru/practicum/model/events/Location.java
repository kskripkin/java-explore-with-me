package ru.practicum.model.events;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "locations")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String lat;

    @Column(nullable = false)
    private String lon;

    public Location(String lat, String lon) {
        this.lat = lat;
        this.lon = lon;
    }
}
