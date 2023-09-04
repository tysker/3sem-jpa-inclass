package dk.lyngby.dao;

import dk.lyngby.model.Customer;
import dk.lyngby.model.Order;
import dk.lyngby.model.Product;

import java.util.List;

public interface IOrderDAO {

    public Order saveOrderAndAddToCustomer(Order order, Customer customer);

    public List<Order> findAllOrdersByCustomer(Customer customer);

    Order saveOrderLineForProductAndAddToOrder(Product product, Order order);

    public double totalPriceOfAnOrder(Order order);
}
