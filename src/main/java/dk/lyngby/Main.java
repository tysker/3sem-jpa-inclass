package dk.lyngby;

import dk.lyngby.config.HibernateConfig;
import jakarta.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        try (var em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            em.getTransaction().commit();
        }

    }
}
