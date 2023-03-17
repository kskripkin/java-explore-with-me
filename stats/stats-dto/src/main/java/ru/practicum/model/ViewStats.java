package ru.practicum.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ViewStats {

    String app;

    String uri;

    int hits;

//    public ViewStats(String app, String uri, int hits) {
//        this.app = app;
//        this.uri = uri;
//        this.hits = hits;
//    }
}
