package com.ak.rstore;

import com.ak.rstore.manager.StoreHouseManager;
import com.ak.rstore.model.Category;
import com.ak.rstore.model.Customer;
import com.ak.rstore.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class MainTest {
    static final Logger log = LoggerFactory.getLogger(MainTest.class);
    private static StoreHouseManager manager = StoreHouseManager.INSTANCE;

    public static void main(String[] args) {



        Category category = new Category("c1");
        manager.addCategory(category);

        Product product1 = new Product("product1", "desc1");
        manager.addProduct(product1);

        Product product2 = new Product("product2", "desc2");
        manager.addProduct(product2);
//
        Customer cus1 = new Customer("cus1");
        manager.addNewCustomer(cus1);
//
        Product p1 = manager.findProductByName("product1");
        Product p2 = manager.findProductByName("product2");
        List<Product> products = new ArrayList<>();
        products.add(p1);
        products.add(p2);
        Category c = manager.findCategoryByName("c1");
//        Customer customer1 = manager.findCustomerByName("cus1");
//        log.info("Log: found customer "+customer1);
//
//        manager.addOrder(products,customer1);

//        manager.deleteALlShopOrders();


//        manager.deleteCategoryButLeaveProducts(c);
//
        manager.setCategoryToProduct(p1, c);
        manager.setCategoryToProduct(p2, c);
//
////        manager.deleteCategoryWithAllProducts(c);
//
//
//        manager.deleteCategoryButLeaveProducts(c);


//        Category category = manager.findCategoryByName("No category");
//        manager.deleteCategoryButLeaveProducts(category);

//        List<Product> uncategorized = manager.retrieveUncategorizedProducts();
//        System.out.println(uncategorized.size());
//
//        Product updP = manager.findProductByName("product1");
//        Category updC = manager.findCategoryByName("c35");
//        updP.setCategory(updC);
//        manager.updateProduct(updP);

//        manager.changeCategoryForProduct("product2","c35");
//        manager.deleteProduct(product2);


//        manager.deleteAllProducts();

//        List<Product> products = manager.findAllProducts();
//        System.out.println(products.size());
//
//        manager.deleteAllProducts();


//        manager.addOrder(products,customer1);


//        Customer customer = manager.findCustomerById(5);

//        Customer customer = manager.findCustomerByName("cus1");
//        List<ShopOrder> orders =   manager.findCustomerOrders(customer);
//        log.info("Log: "+orders.size());
//
//        for (ShopOrder o : orders) {
//            log.info("Log: "+o.toString());
//        }
//
//
//        manager.deleteShopOrder(orders.get(0));


//        List<Product> productList = manager.retrieveNewProducts();
//        log.info("Log: new products: "+productList.toString());

        List<Product> products1 = c.getProducts();
        log.info("Log: size "+products1.size());
    }


}

