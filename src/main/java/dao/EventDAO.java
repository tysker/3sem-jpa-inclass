package dao;

import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;
import model.Event;

public class EventDAO {

    private static EventDAO instance;
    private static EntityManagerFactory emf;

    public static EventDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EventDAO();
        }
        return instance;
    }

    public void persistEvent(Event event) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(event);
            em.getTransaction().commit();
        }
    }

    public Event updateEvent(Event event) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Event merge = em.merge(event);
            em.getTransaction().commit();
            return merge;
        }
    }

    public Event findEvent(int id) {
        try (var em = emf.createEntityManager()) {
            return em.find(Event.class, id);
        }
    }
}
