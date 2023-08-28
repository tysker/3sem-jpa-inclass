package org.lyngby.dao;

import org.lyngby.model.Driver;

import java.math.BigDecimal;
import java.util.List;

public interface DriverDAO {

    String saveDriver(String name, String surname, BigDecimal salary);
    Driver getDriverById(String id);
    Driver updateDriver(Driver driver);
    void deleteDriver(String id);
    List<Driver> findAllDriversEmployedAtTheSameYear(String year);
    List<BigDecimal> fetchAllDriversWithSalaryGreaterThan(BigDecimal threshold);
    double fetchHighestSalary();
    List<String> fetchFirstNameOfAllDrivers();
    long calculateNumberOfDrivers();
    Driver fetchDriverWithHighestSalary();
}
