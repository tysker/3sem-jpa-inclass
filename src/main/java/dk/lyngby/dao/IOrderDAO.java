package dk.lyngby.dao;

import dk.lyngby.dto.OrderDTO;
import dk.lyngby.model.Customer;
import dk.lyngby.model.Order;
import dk.lyngby.model.OrderLine;
import dk.lyngby.model.Product;

import java.util.List;

public interface IOrderDAO {

    public Order saveOrderAndAddToCustomer(Order order, Customer customer);

    Order getOrderById(int id);

    public List<OrderDTO> findAllOrdersByCustomer(Customer customer);

    Order saveOrderLineForSpecifikProductAndAddItToAnOrder(Product product, Order order);

    public double totalPriceOfAnOrder(Order order);
}
