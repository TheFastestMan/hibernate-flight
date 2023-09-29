package ru.rail.service;

import ru.rail.dao.TicketDao;
import ru.rail.dto.TicketDto;
import ru.rail.entity.Ticket;
import ru.rail.map.MapToDto;
import ru.rail.map.MapToEntity;

public class TicketService {
    private final TicketDao ticketDao;
    private final MapToEntity mapToEntity = MapToEntity.getInstance();
    private final MapToDto mapToDto = MapToDto.getInstance();

    public TicketService() {
        this.ticketDao = new TicketDao();
    }

    public void saveTicket(TicketDto ticketDto) {
        Ticket ticket = mapToEntity.convertToEntity(ticketDto);
        ticketDao.save(ticket);
    }

    public TicketDto getTicket(Long id) {
        Ticket ticket = ticketDao.get(id);

        if (ticket == null) {
            return null;
        }

        return mapToDto.convertToDto(ticket);
    }


    public void updateTicket(TicketDto ticketDto) {
        Ticket ticket = mapToEntity.convertToEntity(ticketDto);
        ticketDao.update(ticket);
    }

    public void deleteTicket(Long id) {
        ticketDao.delete(id);
    }


}

