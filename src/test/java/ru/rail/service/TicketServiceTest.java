package ru.rail.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.rail.dto.TicketDto;
import ru.rail.service.TicketService;

import static org.junit.jupiter.api.Assertions.*;

class TicketServiceTest {

    private TicketService ticketService;

    @BeforeEach
    public void setUp() {
        ticketService = new TicketService();
    }

    @Test
    public void testSaveAndGetTicket() {
        TicketDto ticketDto = createTestTicketDto();
        ticketService.saveTicket(ticketDto);

        TicketDto retrievedTicketDto = ticketService.getTicket(ticketDto.getId());
        assertNotNull(retrievedTicketDto);
        assertEquals(ticketDto.getPassengerName(), retrievedTicketDto.getPassengerName());
    }

    @Test
    public void testUpdateTicket() {
        TicketDto ticketDto = createTestTicketDto();

        ticketDto.setPassengerName(createTestTicketDto().getPassengerName());
        ticketService.updateTicket(ticketDto);

        TicketDto updatedTicketDto = ticketService.getTicket(ticketDto.getId());
        assertEquals(createTestTicketDto().getPassengerName(), updatedTicketDto.getPassengerName());
    }

    @Test
    public void testDeleteTicket() {
        TicketDto ticketDto = createTestTicketDto();

        ticketService.deleteTicket(ticketDto.getId());
        assertNull(ticketService.getTicket(ticketDto.getId()));
    }

    private TicketDto createTestTicketDto() {
        return TicketDto.builder()
                .id(71L)
                .passportNo("NO232323")
                .passengerName("Dwight Schrute")
                .flightId(2L)
                .seatNo("Q2")
                .cost(999)
                .build();

    }
}
