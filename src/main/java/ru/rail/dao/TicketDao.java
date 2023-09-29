package ru.rail.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import ru.rail.entity.Ticket;

public class TicketDao {
    private final SessionFactory sessionFactory;

    public TicketDao() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(Ticket.class);
        this.sessionFactory = configuration.buildSessionFactory();
    }

    public void save(Ticket ticket) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(ticket);
            transaction.commit();
        }
    }

    public void update(Ticket ticket) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(ticket);
            transaction.commit();
        }
    }

    public Ticket get(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Ticket.class, id);
        }
    }

    public void delete(Long id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            Ticket ticket = session.get(Ticket.class, id);
            if (ticket != null) {
                session.delete(ticket);
            }
            transaction.commit();
        }
    }
}
