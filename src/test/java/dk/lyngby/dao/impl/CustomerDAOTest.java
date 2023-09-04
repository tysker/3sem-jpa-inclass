package dk.lyngby.dao.impl;

import dk.lyngby.config.HibernateConfig;
import dk.lyngby.model.Customer;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDAOTest {

    private static EntityManagerFactory emf;
    private static CustomerDAO dao;

    @BeforeAll
    static void setUpAll() {
        emf = HibernateConfig.getEntityManagerFactoryConfig();
        dao = CustomerDAO.getInstance(emf);
    }

    @BeforeEach
    void setUp() {

        Customer customer1 = new Customer("John", "john@mail.com");
        Customer customer2 = new Customer("Jane", "jane@mail.com");

        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.createNativeQuery("ALTER SEQUENCE customers_id_seq RESTART WITH 1").executeUpdate();
            em.createNamedQuery("Customer.deleteAllRows").executeUpdate();
            em.persist(customer1);
            em.persist(customer2);
            em.getTransaction().commit();
        }
    }

    @AfterAll
    static void tearDownAll() {

    }

    @Test
    void saveCustomer() {

        // given
        Customer steve = new Customer("Steve", "steve@mail.com");

        // when
        int expected = dao.saveCustomer(steve);

        // then
        assertEquals(3, expected);
    }

    @Test
    void getCustomer() {
        // given
        String expected = "John";
        // when
        Customer customer = dao.getCustomer(1);

        // then
        assertEquals(expected, customer.getName());
    }

    @Test
    void getAllCustomers() {
        // given
        int expected = 2;

        // when
        List<Customer> customerList = dao.getAllCustomers();

        // then
        assertEquals(expected, customerList.size());
    }
}