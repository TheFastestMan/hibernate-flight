package ru.rail;


import org.junit.jupiter.api.Test;
import ru.rail.entity.Ticket;

import static org.junit.jupiter.api.Assertions.*;

class HibernateRunnerTest {

    @Test
    public void testSaveTicket() {
        // Note: This is a very basic example and won't be an exhaustive test.
        Ticket ticket = Ticket.builder()
                .id(66L)
                .passportNo("LS9868FT")
                .passengerName("AxelLL Miller")
                .flightId(2L)
                .seatNo("C2")
                .cost(100)
                .build();

        assertNotNull(ticket, "Ticket should not be null after creation");
    }
}