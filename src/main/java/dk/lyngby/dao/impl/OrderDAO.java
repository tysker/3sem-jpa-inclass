package dk.lyngby.dao.impl;

import dk.lyngby.dto.OrderDTO;
import dk.lyngby.model.Customer;
import dk.lyngby.model.Order;
import dk.lyngby.model.OrderLine;
import dk.lyngby.model.Product;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class OrderDAO implements dk.lyngby.dao.IOrderDAO {

    private static EntityManagerFactory emf;
    private static OrderDAO instance;

    public static OrderDAO getInstance(EntityManagerFactory factory) {
        if (instance == null) {
            emf = factory;
            instance = new OrderDAO();
        }
        return instance;
    }

    @Override
    public Order saveOrderAndAddToCustomer(Order order, Customer customer) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            customer.addOrder(order);
            em.merge(customer);
            em.persist(order);
            em.getTransaction().commit();
        }
        return order;
    }

    @Override
    public Order getOrderById(int id) {
        try (var em = emf.createEntityManager()) {
            return em.find(Order.class, id);
        }
    }

    @Override
    public List<OrderDTO> findAllOrdersByCustomer(Customer customer) {
        try (var em = emf.createEntityManager()) {
            return em.createQuery("SELECT new dk.lyngby.dto.OrderDTO(o.orderId, o.date) FROM Order o WHERE o.customer = :customer", OrderDTO.class)
                    .setParameter("customer", customer)
                    .getResultList();
        }
    }

    @Override
    public Order saveOrderLineForSpecifikProductAndAddItToAnOrder(Product product, Order order) {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            OrderLine orderLine = new OrderLine(5, product, order);
            em.merge(order);
            em.persist(product);
            em.persist(orderLine);
            em.getTransaction().commit();
        }
        return order;
    }

    @Override
    public double totalPriceOfAnOrder(Order order) {
        try (var em = emf.createEntityManager()) {
            return em.createQuery(
                    "SELECT new dk.lyngby.dto.TotalPriceDTO(p.price, ol.quantity) " +
                            "FROM OrderLine ol " +
                            "JOIN Product p " +
                            "ON ol.product = p " +
                            "WHERE ol.order = :order",
                            dk.lyngby.dto.TotalPriceDTO.class)
                    .setParameter("order", order)
                    .getResultList()
                    .stream()
                    .mapToDouble(dk.lyngby.dto.TotalPriceDTO::getTotalPriceOfAnOrder)
                    .sum();
        }
    }
}
