package org.lyngby.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;
import org.lyngby.config.HibernateTestConfig;
import org.lyngby.model.Driver;
import org.lyngby.model.WasteTruck;


import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class WasteTruckDAOImplTest {

    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static WasteTruckDAO dao;
    private static Driver jacob;

    @BeforeAll
    static void setUpAll() {
        emf = HibernateTestConfig.getEntityManagerFactoryConfig();
        dao = WasteTruckDAO.getInstance(emf);
        jacob = new Driver("Jacob", "Jensen", new BigDecimal(12500));
    }

    @BeforeEach
    void setUpEach() {
        em = emf.createEntityManager();
        deleteDB();
        createTrucks();
    }

    @AfterEach
    void tearDownEach() {
        em.close();
    }


    private static void deleteDB() {
        em.getTransaction().begin();
        em.createNativeQuery("ALTER SEQUENCE truck_id_seq RESTART WITH 1").executeUpdate();
        em.createNamedQuery("Driver.deleteAllDrivers").executeUpdate();
        em.createNamedQuery("Truck.deleteAllTrucks").executeUpdate();
        em.getTransaction().commit();
    }

    private static void createTrucks() {
        // Create drivers
        Driver michelle = new Driver("Michelle", "Larsen", new BigDecimal(15000));
        Driver patrick = new Driver("Patrick", "Hansen", new BigDecimal(18000));
        Driver steve = new Driver("Steve", "Schmidt", new BigDecimal(31000));
        Driver john = new Driver("John", "Doe", new BigDecimal(42000));
        Driver jane = new Driver("Jane", "Denver", new BigDecimal(65000));

        // Create trucks
        WasteTruck mercedes = new WasteTruck("Mercedes", "AB-256-52", 20000);
        mercedes.setAvailable(true);
        WasteTruck volvo = new WasteTruck("Volvo", "AB-256-53", 1500);
        volvo.setAvailable(true);
        WasteTruck scania = new WasteTruck("Scania", "AB-256-54", 10000);

        mercedes.addDriver(michelle);
        mercedes.addDriver(patrick);
        volvo.addDriver(steve);
        scania.addDriver(john);
        scania.addDriver(jane);

        em.getTransaction().begin();
        em.persist(mercedes);
        em.persist(volvo);
        em.persist(scania);

        em.persist(michelle);
        em.persist(patrick);
        em.persist(steve);
        em.persist(john);
        em.persist(jane);
        em.persist(jacob);
        em.getTransaction().commit();
    }

    @Test
    void saveWasteTruck() {

        // given
        int id = dao.saveWasteTruck("BMW", "CD-652-60", 20000);

        // then
        assertNotNull(id);
    }

    @Test
    void getWasteTruckById() {

        // given
        String expected = "Mercedes";

        // when
        WasteTruck actually = dao.getWasteTruckById(1);

        // then
        assertEquals(expected, actually.getBrand());
    }

    @Test
    void setWasteTruckAvailable() {

        // given
        WasteTruck truck = dao.getWasteTruckById(3);
        boolean expected = truck.isAvailable(); // false

        // when
        dao.setWasteTruckAvailable(truck, true);

        // then
        assertNotEquals(expected, truck.isAvailable());
    }

    @Test
    void deleteWasteTruck() {

        // given
        dao.deleteWasteTruck(2);

        // when
        WasteTruck actually = dao.getWasteTruckById(2);

        // then
        assertNull(actually);
    }

    @Test
    void addDriverToWasteTruck() {

        // given
        Driver driver = em.find(Driver.class, jacob.getDriverId()); // (Jacob Jensen)
        WasteTruck truck = dao.getWasteTruckById(1);
        int actually = truck.getDrivers().size();

        // when
        dao.addDriverToWasteTruck(truck, driver);

        // then
        assertEquals(actually + 1, truck.getDrivers().size());
    }

    @Test
    void removeDriverFromWasteTruck() {

        // given
        WasteTruck truck = dao.getWasteTruckById(1);
        int expected = truck.getDrivers().size() - 1;

        // when
        dao.removeDriverFromWasteTruck(truck, truck.getDrivers().stream().findFirst().get().getDriverId());
        int actually = truck.getDrivers().size();

        // then
        assertEquals(expected, actually);
    }

    @Test
    void getAllAvailableTrucks() {

        // given
        int expected = 2;

        // when
        int actually = dao.getAllAvailableTrucks().size();

        // then
        assertEquals(expected, actually);
    }
}