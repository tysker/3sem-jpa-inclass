package dk.lyngby.dao.impl;

import dk.lyngby.config.HibernateConfig;
import dk.lyngby.model.Customer;
import dk.lyngby.model.Order;
import dk.lyngby.model.OrderLine;
import dk.lyngby.model.Product;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        Order order = orderDAO.saveOrderAndAddToCustomer(o1, customer);

        // then

        assertEquals(customer.getName(), order.getCustomer().getName());

    }

    @Test
    void findAllOrdersByCustomer() {
        // given

        // when

        // then
    }

    @Test
    void saveOrderLineForProductAndAddToOrder() {
        // given

        // when

        // then
    }

    @Test
    void totalPriceOfAnOrder() {
        // given
        Customer customer = customerDAO.getCustomer(1);

        // when


        // then
    }
}