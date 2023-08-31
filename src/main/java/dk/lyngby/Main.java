package dk.lyngby;

import dk.lyngby.config.HibernateConfig;
import dk.lyngby.model.Company;
import dk.lyngby.model.ContactPerson;
import jakarta.persistence.EntityManagerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        LOGGER.info("Hello World");


        try (var em = emf.createEntityManager())
        {
            Company company = new Company("Lyngby", "Lyngby Hovedgade 23", "12345678");
            ContactPerson contactPerson = new ContactPerson("John", "Doe", "87654321");
            company.setContactPerson(contactPerson);
            em.getTransaction().begin();
            em.persist(company);
            em.getTransaction().commit();
        }

    }
}
