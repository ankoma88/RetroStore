package com.ak.rstore.dao;

import com.ak.rstore.model.Customer;
import com.ak.rstore.model.Product;
import com.ak.rstore.model.ShopOrder;

import java.util.List;

public interface ShopOrderDAO {
    void createOrder(ShopOrder order);
    void saveOrUpdateOrder(ShopOrder order);
    void createOrderWithProductList(ShopOrder order,List<Product> productList);
    boolean deleteOrder(ShopOrder order);
    int deleteAllOrders();
    ShopOrder findOrderById(int orderId);
    List findAllOrders();
    List findAllOrdersOfCertainCustomer(Customer customer);
}
