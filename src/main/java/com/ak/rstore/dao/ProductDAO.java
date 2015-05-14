package com.ak.rstore.dao;

import com.ak.rstore.model.Category;
import com.ak.rstore.model.Product;
import com.ak.rstore.model.ShopOrder;

import java.util.List;

public interface ProductDAO {
    void createProduct(Product product);
    void saveOrUpdateProduct(Product product);
    boolean deleteProduct(Product product);
    int deleteAllProducts();
    Product findProductById(int productId);
    Product findProductByName(String name);
    List findAllProductsOfCategory(Category category);
    List findAllProducts();
    List findAllProductsInOrder(ShopOrder order);
}
