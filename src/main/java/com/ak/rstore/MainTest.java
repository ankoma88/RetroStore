package com.ak.rstore;

import com.ak.rstore.manager.ShopManager;
import com.ak.rstore.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.Objects;

public class MainTest {
    static final Logger log = LoggerFactory.getLogger(MainTest.class);
    private static ShopManager manager = ShopManager.INSTANCE;

    public static void main(String[] args) {
        String cName = "";
        String pName = "";
        String pDesc = "";
        String pPrice = "";
        String pAmount = "";
        String pYear = "";
        String pPhoto = "";
        String pCat = "";

        if (cName == null || Objects.equals(cName, "")) cName = "No category";
        if (pCat == null || Objects.equals(pCat, "")) pCat = "No category";


        if (pName == null || Objects.equals(pName, "")) pName = "No name";
        if (pDesc == null || Objects.equals(pDesc, "")) pDesc = "No description";
        if (pPrice == null || Objects.equals(pDesc, "")) pPrice = "0";
        if (pAmount == null || Objects.equals(pDesc, "")) pAmount = "0";
        if (pYear == null || Objects.equals(pDesc, "")) pYear = "0";
        if (pPhoto == null || Objects.equals(pDesc, "")) pPhoto = "No photo";

        String[] params = {pName, pDesc, pPrice, pAmount, pYear, pPhoto, pCat};

        Product newProd = new Product(params[0], params[1], new BigDecimal(params[2]), Integer.valueOf(params[3]), Integer.valueOf(params[4]), params[5]);


        System.out.println(newProd);

//        System.out.println(new BigDecimal(0));


//
//        for (int i = 2; i < 11; i++) {
//            Category category = new Category("c" + i);
//            manager.addCategory(category);
//
//        }
//
//        Category category = new Category("c" + i);
//        manager.addCategory(category);
//
//        Product product1 = new Product("product1", "desc1");
//        manager.addProduct(product1);
//
//        Product product2 = new Product("product2", "desc2");
//        manager.addProduct(product2);
////
//        Customer cus1 = new Customer("cus1");
//        manager.addNewCustomer(cus1);
////
//        Product p1 = manager.findProductByName("product1");
//        Product p2 = manager.findProductByName("product2");
//        List<Product> products = new ArrayList<>();
//        products.add(p1);
//        products.add(p2);
//        Category c = manager.findCategoryByName("c1");
////        Customer customer1 = manager.findCustomerByName("cus1");
////        log.info("Log: found customer "+customer1);
////
////        manager.addOrder(products,customer1);
//
//        manager.deleteALlShopOrders();
//
//
//        manager.deleteCategoryButLeaveProducts(c);
//
//        manager.setCategoryToProduct(p1, c);
//        manager.setCategoryToProduct(p2, c);
////
////        manager.deleteCategoryWithAllProducts(c);
//
//
//        manager.deleteCategoryButLeaveProducts(c);
//
//
//        Category category = manager.findCategoryByName("No category");
//        manager.deleteCategoryButLeaveProducts(category);
//
//        List<Product> uncategorized = manager.retrieveUncategorizedProducts();
//        System.out.println(uncategorized.size());
//
//        Product updP = manager.findProductByName("product1");
//        Category updC = manager.findCategoryByName("c35");
//        updP.setCategory(updC);
//        manager.updateProduct(updP);
//
//        manager.changeCategoryForProduct("product2","c35");
//        manager.deleteProduct(product2);
//
//
//        manager.deleteAllProducts();
//
//        List<Product> products = manager.findAllProducts();
//        System.out.println(products.size());
//
//        manager.deleteAllProducts();
//
//
//        manager.addOrder(products,customer1);
//
//
//        Customer customer = manager.findCustomerById(5);
//
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
//
//
//        List<Product> productList = manager.retrieveNewProducts();
//        log.info("Log: new products: "+productList.toString());
//
//        List<Product> products1 = c.getProducts();
//        log.info("Log: size " + products1.size());
//
//
//        manager.addProduct(new Product("product3", "p3Desc",
//                new BigDecimal(Integer.valueOf(150)),
//                Integer.valueOf("1"),
//                Integer.valueOf("1900"), "no photo"));

    }


}

