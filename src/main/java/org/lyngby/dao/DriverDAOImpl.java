package org.lyngby.dao;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;
import lombok.NoArgsConstructor;
import org.lyngby.model.Driver;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class DriverDAOImpl implements DriverDAO {

    private static EntityManagerFactory emf;
    private static DriverDAOImpl instance;

    public static DriverDAOImpl getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new DriverDAOImpl();
        }
        return instance;
    }

    @Override
    public String saveDriver(String name, String surname, BigDecimal salary){
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            Driver driver = new Driver(name, surname, salary);
            em.persist(driver);
            em.getTransaction().commit();
            return driver.getDriverId();
        }
    }

    @Override
    public Driver getDriverById(String id) {
        try(var em = emf.createEntityManager()){
            return em.find(Driver.class, id);
        }
    }

    @Override
    public Driver updateDriver(Driver driver) {
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.merge(driver);
            em.getTransaction().commit();
            return driver;
        }
    }

    @Override
    public void deleteDriver(String id) {
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            Driver driver = em.find(Driver.class, id);
            em.remove(driver);
            em.getTransaction().commit();
        }
    }

    @Override
    public List<Driver> findAllDriversEmployedAtTheSameYear(String year) {
        try(var em = emf.createEntityManager()) {
            TypedQuery<Driver> query = em.createQuery(
                    "SELECT d FROM Driver d WHERE YEAR(d.employmentDate) = :year",
                    Driver.class
            );
            query.setParameter("year", year);
            return query.getResultList();
        }
    }

    @Override
    public List<BigDecimal> fetchAllDriversWithSalaryGreaterThan10000() {
        return null;
    }

    @Override
    public double fetchHighestSalary() {
        return 0;
    }

    @Override
    public List<String> fetchFirstNameOfAllDrivers() {
        return null;
    }

    @Override
    public long calculateNumberOfDrivers() {
        return 0;
    }

    @Override
    public Driver fetchDriverWithHighestSalary() {
        return null;
    }
}
