package com.ak.rstore.dao.interfaces;

import com.ak.rstore.exceptions.RecordAlreadyExistsException;
import com.ak.rstore.model.Category;
import com.ak.rstore.model.Product;
import com.ak.rstore.model.ShopOrder;

import java.util.List;

public interface ProductDAO {
    void createProduct(Product product) throws RecordAlreadyExistsException;

    void setCategoryToProduct(int productId, Category category);

    void saveOrUpdateProduct(Product product);
    boolean deleteProduct(Product product);
    int deleteAllProducts();
    Product findProductById(int productId);
    Product findProductByName(String name);
    List findAllProductsOfCategory(Category category);
    List findAllProducts();
    List findAllProductsInOrder(ShopOrder order);
}
