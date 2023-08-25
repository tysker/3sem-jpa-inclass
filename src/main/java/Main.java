import jakarta.persistence.EntityManagerFactory;

public class Main {
    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

        Phone mobil = new Phone("123456789", Phone.PhoneType.MOBILE);
        Phone home = new Phone("987654321", Phone.PhoneType.HOME);
        Phone work = new Phone("123123123", Phone.PhoneType.WORK);

        try(var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(mobil);
            em.persist(home);
            em.persist(work);
            em.getTransaction().commit();
        }

    }
}
