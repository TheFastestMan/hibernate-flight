package ru.rail;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.rail.entity.Ticket;

import javax.persistence.Table;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class HibernateRunnerTest {
    private Connection connection;
    private final String DB_URL = "jdbc:postgresql://localhost:5432/flight_repository";
    private final String USER = "postgres";
    private final String PASS = "postgres";

    @BeforeEach
    public void setUp() throws Exception {
        connection = DriverManager.getConnection(DB_URL, USER, PASS);
    }

    @Test
    public void testSaveTicket() {
        Ticket ticket = createTestTicket();

        String sql = generateInsertSQL(ticket);

        try (Statement stmt = connection.createStatement()) {
            int affectedRows = stmt.executeUpdate(sql);
            assertEquals(1, affectedRows, "Should insert one row");
        } catch (Exception e) {
            fail("Error executing SQL: " + e.getMessage());
        }
    }

    @Test
    public void testUpdateTicket() {
        Ticket ticket = createTestTicket();
        ticket.setPassengerName("Updated Name");

        String sql = generateUpdateSQL(ticket);

        try (Statement stmt = connection.createStatement()) {
            int affectedRows = stmt.executeUpdate(sql);
            assertEquals(1, affectedRows, "Should update one row");
        } catch (Exception e) {
            fail("Error executing SQL: " + e.getMessage());
        }
    }

    @Test
    public void testDeleteTicket() {
        Ticket ticket = createTestTicket();

        String sql = generateDeleteSQL(ticket);

        try (Statement stmt = connection.createStatement()) {
            int affectedRows = stmt.executeUpdate(sql);
            assertEquals(1, affectedRows, "Should delete one row");
        } catch (Exception e) {
            fail("Error executing SQL: " + e.getMessage());
        }
    }

    private Ticket createTestTicket() {
        return Ticket.builder()
                .id(66L)
                .passportNo("LS9868FT")
                .passengerName("AxelLL Miller")
                .flightId(1L)
                .seatNo("A3")
                .cost(100)
                .build();
    }

    private String generateInsertSQL(Ticket ticket) {
        return String.format("""
                        INSERT INTO 
                        %s
                        (id, passport_no, passenger_name, flight_id, seat_no, cost)
                        VALUES
                        (%d, '%s', '%s', %d, '%s', %d)
                        """,
                getTableName(ticket),
                ticket.getId(),
                ticket.getPassportNo(),
                ticket.getPassengerName(),
                ticket.getFlightId(),
                ticket.getSeatNo(),
                ticket.getCost());
    }

    private String generateUpdateSQL(Ticket ticket) {
        return String.format("""
                        UPDATE
                        %s
                        SET passport_no = '%s', passenger_name = '%s', flight_id = %d, seat_no = '%s', cost = %d
                        WHERE id = %d
                        """,
                getTableName(ticket),
                ticket.getPassportNo(),
                ticket.getPassengerName(),
                ticket.getFlightId(),
                ticket.getSeatNo(),
                ticket.getCost(),
                ticket.getId());
    }

    private String generateDeleteSQL(Ticket ticket) {
        return String.format("""
                        DELETE FROM 
                        %s
                        WHERE id = %d
                        """,
                getTableName(ticket),
                ticket.getId());
    }

    private String getTableName(Ticket ticket) {
        Table tableAnnotation = ticket.getClass().getAnnotation(Table.class);
        return Optional.ofNullable(tableAnnotation)
                .map(Table::name)
                .orElseThrow(() -> new RuntimeException("No @Table annotation found on Ticket class"));
    }
}