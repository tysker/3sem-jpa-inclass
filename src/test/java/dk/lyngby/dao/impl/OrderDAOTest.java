package dk.lyngby.dao.impl;

import dk.lyngby.config.HibernateConfig;
import dk.lyngby.dto.OrderDTO;
import dk.lyngby.model.Customer;
import dk.lyngby.model.Order;
import dk.lyngby.model.OrderLine;
import dk.lyngby.model.Product;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderDAOTest {

    private static EntityManagerFactory emf;
    private static OrderDAO orderDAO;

    private static CustomerDAO customerDAO;

    @BeforeAll
    static void setUpAll() {
        emf = HibernateConfig.getEntityManagerFactoryConfig();
        orderDAO = OrderDAO.getInstance(emf);
        customerDAO = CustomerDAO.getInstance(emf);
    }

    @BeforeEach
    void setUp() {
        Customer c1 = new Customer("John", "john@mail.com");
        Customer c2 = new Customer("Steve", "steve@mail.com");
        Product milk = new Product("Milk", Product.Category.FOOD, 10.0);
        Product bread = new Product("Bread", Product.Category.FOOD, 15.0);
        Product radio = new Product("Radio", Product.Category.ELECTRONICS, 100.0);
        Order o1 = new Order();
        Order o2 = new Order();

        c1.addOrder(o1);
        OrderLine oL1 = new OrderLine(2, milk, o1);
        OrderLine oL2 = new OrderLine(1, bread, o1);

        OrderLine oL3 = new OrderLine(1, radio, o2);
        OrderLine oL4 = new OrderLine(1, bread, o2);
        c1.addOrder(o2);


        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.createNamedQuery("OrderLine.deleteAllRows").executeUpdate();
            em.createNamedQuery("Order.deleteAllRows").executeUpdate();
            em.createNamedQuery("Product.deleteAllRows").executeUpdate();
            em.createNamedQuery("Customer.deleteAllRows").executeUpdate();
            em.createNativeQuery("ALTER SEQUENCE customers_customer_id_seq RESTART WITH 1").executeUpdate();
            em.createNativeQuery("ALTER SEQUENCE orders_order_id_seq RESTART WITH 1").executeUpdate();
            em.createNativeQuery("ALTER SEQUENCE products_product_id_seq RESTART WITH 1").executeUpdate();
            em.createNativeQuery("ALTER SEQUENCE orderlines_orderline_id_seq RESTART WITH 1").executeUpdate();


            em.persist(c1);
            em.persist(c2);
            em.persist(milk);
            em.persist(bread);
            em.persist(radio);
            em.persist(o1);
            em.persist(o2);
            em.persist(oL1);
            em.persist(oL2);
            em.persist(oL3);
            em.persist(oL4);


            em.getTransaction().commit();
        }
    }

    @AfterAll
    static void tearDownAll() {
        emf.close();
    }

    @Test
    void saveOrderAndAddToCustomer() {

        // given
        Order o1 = new Order();
        Customer customer = customerDAO.getCustomer(1);

        // when
        BiFunction<Order, Customer, Order> saveOrderAndAddToCustomer = orderDAO::saveOrderAndAddToCustomer;

        // then

        assertEquals(customer.getName(), saveOrderAndAddToCustomer.apply(o1, customer).getCustomer().getName());

    }

    @Test
    void findAllOrdersByCustomer() {
        // given
        Customer customer = customerDAO.getCustomer(1);

        // when
        Function<Customer, List<OrderDTO>> findAllOrdersByCustomer = orderDAO::findAllOrdersByCustomer;

        // then
        assertEquals(2, findAllOrdersByCustomer.apply(customer).size());
    }

    @Test
    void saveOrderLineForProductAndAddToOrder() {
        // given
        Product product = new Product("Sweatshirt", Product.Category.CLOTHES, 125.0);
        Order order = orderDAO.getOrderById(1);

        // when
        BiFunction<Product, Order, Order> saveOrderLineForSpecifikProductAndAddItToAnOrder = orderDAO::saveOrderLineForSpecifikProductAndAddItToAnOrder;


        // then
        assertEquals(2, saveOrderLineForSpecifikProductAndAddItToAnOrder.apply(product, order).getCustomer().getOrders().size());
    }

    @Test
    void totalPriceOfAnOrder() {
        // given
        Order order = orderDAO.getOrderById(2);

        // when
        Function<Order, Double> totalPriceOfAnOrder = orderDAO::totalPriceOfAnOrder;

        // then
        assertEquals(115.0, totalPriceOfAnOrder.apply(order));
    }
}