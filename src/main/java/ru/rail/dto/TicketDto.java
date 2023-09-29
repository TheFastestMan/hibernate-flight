package ru.rail.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TicketDto {
    private Long id;
    private String passportNo;
    private String passengerName;
    private Long flightId;
    private String seatNo;
    private int cost;
}
