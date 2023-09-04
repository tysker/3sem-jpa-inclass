package dk.lyngby.dao.impl;

import dk.lyngby.config.HibernateConfig;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ProductDAOTest {

    private static EntityManagerFactory emf;
    private static ProductDAO dao;

    @BeforeAll
    void setUpAll() {
        emf = HibernateConfig.getEntityManagerFactoryConfig();
        dao = ProductDAO.getInstance(emf);
    }

    @BeforeEach
    void setUp() {
        try(var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.getTransaction().commit();
        }
    }

    @AfterAll
    void tearDownAll() {
        emf.close();
    }

    @Test
    void saveProduct() {
        // given

        // when

        // then
    }

    @Test
    void getProduct() {
        // given

        // when

        // then
    }
}