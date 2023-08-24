import jakarta.persistence.EntityManagerFactory;

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

    public void updateEvent(Event event) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.merge(event);
            em.getTransaction().commit();
        }
    }
}
