package dk.lyngby.dao.impl;

import dk.lyngby.model.Product;
import jakarta.persistence.EntityManagerFactory;

public class ProductDAO implements dk.lyngby.dao.IProductDAO{

    private static EntityManagerFactory emf;
    private static ProductDAO instance;

    public static ProductDAO getInstance(EntityManagerFactory factory) {
        if(instance == null) {
            emf = factory;
            instance = new ProductDAO();
        }
        return instance;
    }
    @Override
    public int saveProduct(Product product) {
        return 0;
    }

    @Override
    public Product getProduct(int id) {
        return null;
    }
}
