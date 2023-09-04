package dk.lyngby.dao;

import dk.lyngby.model.Customer;

import java.util.List;

public interface ICustomerDAO {

    public int saveCustomer(Customer customer);

    public Customer getCustomer(int id);

    public List<Customer> getAllCustomers();
}
