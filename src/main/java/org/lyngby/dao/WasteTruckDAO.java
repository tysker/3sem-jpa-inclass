package org.lyngby.dao;

import jakarta.persistence.EntityManagerFactory;
import lombok.NoArgsConstructor;
import org.lyngby.model.Driver;
import org.lyngby.model.WasteTruck;

import java.util.List;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class WasteTruckDAO implements IWasteTruckDAO {

    private static EntityManagerFactory emf;
    private static WasteTruckDAO instance;

    public static WasteTruckDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new WasteTruckDAO();
        }
        return instance;
    }


    @Override
    public int saveWasteTruck(String brand, String registrationNumber, int capacity) {
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            WasteTruck wasteTruck = new WasteTruck(brand, registrationNumber, capacity);
            em.persist(wasteTruck);
            em.getTransaction().commit();
            return wasteTruck.getId();
        }
    }

    @Override
    public WasteTruck getWasteTruckById(int id) {
        try(var em = emf.createEntityManager()){
            return em.find(WasteTruck.class, id);
        }
    }

    @Override
    public void setWasteTruckAvailable(WasteTruck wasteTruck, boolean available) {
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            wasteTruck.setAvailable(available);
            em.merge(wasteTruck);
            em.getTransaction().commit();
        }
    }

    @Override
    public void deleteWasteTruck(int id) {
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            WasteTruck wasteTruck = em.find(WasteTruck.class, id);
            em.remove(wasteTruck);
            em.getTransaction().commit();
        }
    }

    @Override
    public void addDriverToWasteTruck(WasteTruck wasteTruck, Driver driver) {
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            wasteTruck.addDriver(driver);
            em.merge(wasteTruck);
            em.getTransaction().commit();
        }
    }

    @Override
    public void removeDriverFromWasteTruck(WasteTruck wasteTruck, String id) {
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            wasteTruck.removeDriver(id);
            em.merge(wasteTruck);
            em.getTransaction().commit();
        }
    }

    @Override
    public List<WasteTruck> getAllAvailableTrucks() {
        try(var em = emf.createEntityManager()){
            return em.createQuery("SELECT w FROM WasteTruck w WHERE w.isAvailable = true", WasteTruck.class).getResultList();
        }
    }

    public List<WasteTruck> joinToTables() {
        try(var em = emf.createEntityManager()){
            return em.createQuery("SELECT w FROM WasteTruck w JOIN w.drivers d", WasteTruck.class).getResultList();
        }
    }
}
