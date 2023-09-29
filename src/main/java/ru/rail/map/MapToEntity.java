package ru.rail.map;

import ru.rail.dto.TicketDto;
import ru.rail.entity.Ticket;

public class MapToEntity {
    private static final MapToEntity INSTANCE = new MapToEntity();

    public static MapToEntity getInstance() {
        return INSTANCE;
    }

    public Ticket convertToEntity(TicketDto ticketDto) {
        return Ticket.builder()
                .id(ticketDto.getId())
                .passportNo(ticketDto.getPassportNo())
                .passengerName(ticketDto.getPassengerName())
                .flightId(ticketDto.getFlightId())
                .seatNo(ticketDto.getSeatNo())
                .cost(ticketDto.getCost())
                .build();
    }

}
