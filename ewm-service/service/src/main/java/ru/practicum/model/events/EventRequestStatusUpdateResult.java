package ru.practicum.model.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRequestStatusUpdateResult {

    ParticipationRequestDto confirmedRequests;

    ParticipationRequestDto rejectedRequests;
}
