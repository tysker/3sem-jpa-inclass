package dk.lyngby.dao.impl;

import dk.lyngby.model.Customer;
import dk.lyngby.model.Order;
import dk.lyngby.model.Product;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class OrderDAO implements dk.lyngby.dao.IOrderDAO{

    private static EntityManagerFactory emf;
    private static OrderDAO instance;

    public static OrderDAO getInstance(EntityManagerFactory factory) {
        if(instance == null) {
            emf = factory;
            instance = new OrderDAO();
        }
        return instance;
    }
    @Override
    public Order saveOrderAndAddToCustomer(Order order, Customer customer) {
        try (var em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            customer.addOrder(order);
            em.merge(customer);
            em.persist(order);
            em.getTransaction().commit();
        }
        return order;
    }

    @Override
    public List<Order> findAllOrdersByCustomer(Customer customer) {
        return null;
    }

    @Override
    public Order saveOrderLineForProductAndAddToOrder(Product product, Order order) {
        return null;
    }

    @Override
    public double totalPriceOfAnOrder(Order order) {
        return 0;
    }
}
