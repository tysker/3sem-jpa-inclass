package dk.lyngby.dao.impl;

import dk.lyngby.model.Customer;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class CustomerDAO implements dk.lyngby.dao.ICustomerDAO {

    private static EntityManagerFactory emf;
    private static CustomerDAO instance;

    public static CustomerDAO getInstance(EntityManagerFactory factory) {
        if (instance == null) {
            emf = factory;
            instance = new CustomerDAO();
        }
        return instance;
    }

    @Override
    public int saveCustomer(Customer customer) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(customer);
            em.getTransaction().commit();
        }
        return customer.getCustomerId();
    }

    @Override
    public Customer getCustomer(int id) {
        try (var em = emf.createEntityManager()) {
            return em.find(Customer.class, id);
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        try (var em = emf.createEntityManager())
        {
            TypedQuery<Customer> query = em.createQuery("SELECT c FROM Customer c", Customer.class);
            return query.getResultList();
        }
    }
}
