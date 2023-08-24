import jakarta.persistence.EntityManagerFactory;

public class FestivalDAO {

    private static FestivalDAO instance;
    private static EntityManagerFactory emf;

    public static FestivalDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new FestivalDAO();
        }
        return instance;
    }

    public void persistFestival(Festival festival) {
        try(var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(festival);
            em.getTransaction().commit();
        }
    }
}
