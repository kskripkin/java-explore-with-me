package ru.practicum.server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
public class ViewStats {

    String app;

    String uri;

    Integer hits;

    public ViewStats(String app, String uri/*, Integer hits*/) {
        this.app = app;
        this.uri = uri;
        //this.hits = hits;
    }
}

