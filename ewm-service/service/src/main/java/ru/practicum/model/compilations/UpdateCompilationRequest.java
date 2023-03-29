package ru.practicum.model.compilations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCompilationRequest {

    List<Long> events;

    Boolean pinned;

    String title;
}
