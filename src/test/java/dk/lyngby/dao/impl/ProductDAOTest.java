package dk.lyngby.dao.impl;

import dk.lyngby.config.HibernateConfig;
import dk.lyngby.model.Product;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ProductDAOTest {

    private static EntityManagerFactory emf;
    private static ProductDAO productDao;

    @BeforeAll
    static void setUpAll() {
        emf = HibernateConfig.getEntityManagerFactoryConfig();
        productDao = ProductDAO.getInstance(emf);
    }

    @BeforeEach
    void setUp() {
        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.createNamedQuery("Product.deleteAllRows").executeUpdate();
            em.createNativeQuery("ALTER SEQUENCE products_product_id_seq RESTART WITH 1").executeUpdate();
            em.persist(new Product("Milk", Product.Category.FOOD, 10.0));
            em.getTransaction().commit();
        }
    }

    @Test
    void saveProduct() {
        // given
        Product product = new Product("Test", Product.Category.FOOD, 10.0);

        // when
        int i = productDao.saveProduct(product);

        // then
        assertNotNull(i);
    }

    @Test
    void getProduct() {
        // given
        String productName = "Milk";

        // when
        Product product = productDao.getProduct(1);

        // then
        assertEquals(productName, product.getName());
    }
}