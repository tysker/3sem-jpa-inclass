package org.lyngby.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.lyngby.config.HibernateTestConfig;
import org.lyngby.model.Driver;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DriverDAOImplTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static DriverDAOImpl dao;
    private static List<String> driverids;

    @BeforeAll
    static void setUpAll() {
        emf = HibernateTestConfig.getEntityManagerFactoryConfig();
        em = emf.createEntityManager();
        dao = DriverDAOImpl.getInstance(emf);
        driverids = new ArrayList<>();
        deleteAllDrivers();
        createDrivers();
    }

    @AfterEach
    void tearDownAll() {
        em.close();
    }


    private static void deleteAllDrivers() {
        em.getTransaction().begin();
        em.createNamedQuery("Driver.deleteAllDrivers").executeUpdate();
        em.getTransaction().commit();
    }

    private static void createDrivers() {
        em.getTransaction().begin();
        Driver michelle = new Driver("Michelle", "Larsen", new BigDecimal(12000));
        Driver patrick = new Driver("Patrick", "Hansen", new BigDecimal(20000));
        Driver steve = new Driver("Steve", "Schmidt", new BigDecimal(35000));
        em.persist(michelle);
        driverids.add(michelle.getDriverId());
        em.persist(patrick);
        driverids.add(patrick.getDriverId());
        em.persist(steve);
        driverids.add(steve.getDriverId());
        em.getTransaction().commit();
    }

    @Test
    void saveDriver() {
        // given
        String id = dao.saveDriver("John", "Doe", new BigDecimal(9500));
        // then
        assertNotNull(id);
    }

    @Test
    void getDriverById() {

        // given
        String expected = "Michelle";

        // when
        Driver actually = dao.getDriverById(driverids.get(0));

        // then
        assertEquals(expected, actually.getName());
    }

    @Test
    void updateDriver() {

        // given
        Driver expected = dao.getDriverById(driverids.get(1));
        expected.setSurname("Surname");

        // when
        Driver actually = dao.updateDriver(expected);

        // then
        assertEquals(expected.getSurname(), actually.getSurname());
    }

    @Test
    void deleteDriver() {

        // given
        dao.deleteDriver(driverids.get(2));

        // when
        Driver actually = dao.getDriverById(driverids.get(2));

        // then
        assertNull(actually);
    }

    @Test
    void findAllDriversEmployedAtTheSameYear() {

        // given
        String expected = LocalDate.now().getYear() + "";

        // when
        List<Driver> actually = dao.findAllDriversEmployedAtTheSameYear(expected);

        // then

        for (Driver driver : actually) {
            String year = driver.getEmploymentDate().toString().substring(0, 4);
            assertEquals(expected, year);
        }
    }
}
