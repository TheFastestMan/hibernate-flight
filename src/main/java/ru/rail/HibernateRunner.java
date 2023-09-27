package ru.rail;

import org.hibernate.cfg.Configuration;
import ru.rail.entity.Ticket;

public class HibernateRunner {
    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(Ticket.class);

        try (var sessionFactory = configuration.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.save(Ticket.builder()
                    .id(65L)
                    .passportNo("LS9868F")
                    .passengerName("Axel Miller")
                    .flightId(1L)
                    .seatNo("C1")
                    .cost(100)
                    .build());

            session.getTransaction().commit();

        }

        try (var sessionFactory = configuration.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

            Ticket ticket = session.get(Ticket.class, 65L);
            session.delete(ticket);

            session.getTransaction().commit();

        }

        try (var sessionFactory = configuration.buildSessionFactory();
             var session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.update(Ticket.builder()
                    .id(65L)
                    .passportNo("LS9868XX")
                    .passengerName("Alex Miller")
                    .flightId(1L)
                    .seatNo("C1")
                    .cost(100)
                    .build());

            session.getTransaction().commit();

        }

    }
}
