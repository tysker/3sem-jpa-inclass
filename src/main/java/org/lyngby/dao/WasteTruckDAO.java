package org.lyngby.dao;

import org.lyngby.model.Driver;
import org.lyngby.model.WasteTruck;

import java.util.List;

public interface WasteTruckDAO {

    int saveWasteTruck(String brand, String registrationNumber, int capacity);

    WasteTruck getWasteTruckById(int id);

    void setWasteTruckAvailable(WasteTruck wasteTruck, boolean available);

    void deleteWasteTruck(int id);

    void addDriverToWasteTruck(WasteTruck wasteTruck, Driver driver);

    void removeDriverFromWasteTruck(WasteTruck wasteTruck, String id);

    List<WasteTruck> getAllAvailableTrucks();

}
