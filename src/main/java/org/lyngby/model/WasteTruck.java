package org.lyngby.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@ToString(exclude = "drivers")
@Entity
@Table(name = "truck")
@NamedQueries({
        @NamedQuery(name = "Truck.deleteAllTrucks", query = "DELETE FROM WasteTruck")
})
public class WasteTruck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @Column(name = "brand", nullable = false)
    private String brand; // example: "Mercedes-Benz"

    @Column(name = "registration_number", nullable = false, unique = true)
    private String registrationNumber; // example: "3D1-A25-12M"

    @Column(name = "capacity", nullable = false)
    private int capacity; // in liters

    @Column(name = "is_available", nullable = false)
    private boolean isAvailable;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "truck_id")
    private Set<Driver> drivers = new HashSet<>();

    public WasteTruck(String brand, String registrationNumber, int capacity) {
        this.brand = brand;
        this.registrationNumber = registrationNumber;
        this.capacity = capacity;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public void addDriver(Driver driver) {
        if(driver != null) drivers.add(driver);
    }

    public void removeDriver(String id) {
        drivers.removeIf(driver -> driver.getDriverId().equals(id));
    }
}
