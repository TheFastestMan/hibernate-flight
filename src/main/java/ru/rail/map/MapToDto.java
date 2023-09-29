package ru.rail.map;

import ru.rail.dto.TicketDto;
import ru.rail.entity.Ticket;

public class MapToDto {
    private static final MapToDto INSTANCE = new MapToDto();

    public static MapToDto getInstance() {
        return INSTANCE;
    }

    public TicketDto convertToDto(Ticket ticket) {
        if (ticket == null) {
            return null;
        }

        return TicketDto.builder()
                .id(ticket.getId())
                .passportNo(ticket.getPassportNo())
                .passengerName(ticket.getPassengerName())
                .flightId(ticket.getFlightId())
                .seatNo(ticket.getSeatNo())
                .cost(ticket.getCost())
                .build();
    }

}
