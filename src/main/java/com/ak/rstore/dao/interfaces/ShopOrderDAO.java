package com.ak.rstore.dao.interfaces;

import com.ak.rstore.model.Customer;
import com.ak.rstore.model.Product;
import com.ak.rstore.model.ShopOrder;

import java.util.List;

public interface ShopOrderDAO {
//    void addOrder(ShopOrder order);
//    void saveOrUpdateOrder(ShopOrder order);
    void createOrderWithProductList(List<Product> productList, Customer customer);
    boolean deleteOrder(ShopOrder order);
    int deleteAllOrders();
    ShopOrder findOrderById(int orderId);
    List findAllOrders();
    List findAllOrdersOfCertainCustomer(Customer customer);
}
