package ru.practicum.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestRsDto {

    List<ConfirmedRequests> confirmedRequests;

    List<RejectedRequests> rejectedRequests;

}