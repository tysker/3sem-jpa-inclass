package dk.lyngby.dao;

import dk.lyngby.model.Product;

public interface IProductDAO {

    public int saveProduct(Product product);

    public Product getProduct(int id);

}
