package ru.practicum.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestRsDto {

    private List<ConfirmedRequests> confirmedRequests;

    private List<RejectedRequests> rejectedRequests;

}
