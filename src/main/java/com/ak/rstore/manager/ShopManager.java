package com.ak.rstore.manager;

import com.ak.rstore.dao.interfaces.DAOFactory;
import com.ak.rstore.exceptions.NoSuchRecordException;
import com.ak.rstore.exceptions.RecordAlreadyExistsException;
import com.ak.rstore.model.Category;
import com.ak.rstore.model.Customer;
import com.ak.rstore.model.Product;
import com.ak.rstore.model.ShopOrder;
import com.ak.rstore.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;

public class ShopManager {
    static final Logger log = LoggerFactory.getLogger(ShopManager.class);

    public static final ShopManager INSTANCE = new ShopManager();
    private DAOFactory daoFactory;

    private ShopManager() {
        daoFactory = DAOFactory.getDAOFactory(Properties.ORM);
    }

    public List<Category> retrieveCategories() {
        return daoFactory.getCategoryDAO().findAllCategories();
    }

    public List<Product> retrieveNewProducts() {
        List<Product> products = daoFactory.getProductDAO().findAllProducts();
        Date today = new Date();
        List<Product> newProducts = new LinkedList<Product>();
        for (Product p : products) {
            if ((today.getTime() - p.getReceiptDate().getTime())/86400000 < 7) {
                newProducts.add(p);
            }
        }
        return products;
    }

    public List<Product> retrieveAllProductsOfCategory(Category category) {
        List<Product> products = daoFactory.getProductDAO().findAllProductsOfCategory(category);
        return products;
    }

    public List<Product> retrieveUncategorizedProducts() {
        List<Product> unCategorizedProducts = new ArrayList<>();
        Category defaultCategory = daoFactory.getCategoryDAO().findCategoryByName("No category");
        if (defaultCategory == null) {
            return unCategorizedProducts;
        }
        unCategorizedProducts = daoFactory.getProductDAO().findAllProductsOfCategory(defaultCategory);
        return unCategorizedProducts;
    }

    public void addProduct(Product product) {
        try {
            daoFactory.getProductDAO().createProduct(product);
        } catch (RecordAlreadyExistsException e) {
            log.info("Log: " + e.MESSAGE);
        }
    }

    public void setCategoryToProduct(Product product, Category category) {
        daoFactory.getProductDAO().setCategoryToProduct(product.getProductId(),category);
    }

    public void addCategory(Category cat) {
        try {
            daoFactory.getCategoryDAO().createCategory(cat);
        } catch (RecordAlreadyExistsException e) {
            log.info("Log: "+e.MESSAGE);
        }
    }

    public Category findCategoryByName(String catName) {
        Category c = daoFactory.getCategoryDAO().findCategoryByName(catName);
        return c;
    }

    public Product findProductByName(String pName) {
        Product p = daoFactory.getProductDAO().findProductByName(pName);
        return p;
    }

    public void deleteCategoryWithAllProducts(Category category) {
        daoFactory.getCategoryDAO().deleteCategoryAndAllProducts(category);
    }

    public void deleteCategoryWithAllProducts(String categoryName) {
        Category category = findCategoryByName(categoryName);
        if (category == null) {
            return;
        }
        daoFactory.getCategoryDAO().deleteCategoryAndAllProducts(category);
    }

    public void deleteCategoryButLeaveProducts(Category category) {
      List<Product> products = daoFactory.getProductDAO().findAllProductsOfCategory(category);
        if (products == null || products.size() == 0) {
            deleteCategoryWithAllProducts(category);
            log.info("Log: No products found, category deleted.");
            return;
        }
        Category defaultCategory = findCategoryByName("No category");
        if (defaultCategory == null) {
            defaultCategory = new Category("No category");
            log.info("Log: New default category created.");
            addCategory(defaultCategory);
            log.info("Log: Found " + products.size() + " products and put into default category");
        }
        for (Product p : products) {
            setCategoryToProduct(p,defaultCategory);
            log.info("Log: Setting category to product "+p.getName());
        }
        if (!category.getName().equals("No category")){
            deleteCategoryWithAllProducts(category);
            log.info("Log: Category deleted.");
        }

    }

    public void deleteAllCategories() {
        deleteAllProducts();
        daoFactory.getCategoryDAO().deleteAllCategoriesWithProducts();
    }

    public void deleteAllProducts() {
        daoFactory.getProductDAO().deleteAllProducts();
    }

    public void updateProduct(Product product) {
        daoFactory.getProductDAO().saveOrUpdateProduct(product);
    }

    public void changeCategoryForProduct(int pId, String newButExistingCategoryName) throws NoSuchRecordException, RecordAlreadyExistsException {
        Product product = findProductById(pId);
        Category category = findCategoryByName(newButExistingCategoryName);
        if (category == null || Objects.equals(category.getName(), "")) {
            Category defCat = findCategoryByName("No category");
            if (defCat == null) {
                daoFactory.getCategoryDAO().createCategory(new Category("No category"));
            }
            category = findCategoryByName("No category");
        }
        log.info("Log: product: "+product);
        log.info("Log: category: "+category.getName());
        product.setCategory(category);
        updateProduct(product);
    }

//    public void changeCategoryForProduct(int pId, String newButExistingCategoryName) throws NoSuchRecordException {
//        Product product = findProductById(pId);
//        Category category = findCategoryByName(newButExistingCategoryName);
//        if (category == null) {
//            throw new NoSuchRecordException();
//        }
//        product.setCategory(category);
//        updateProduct(product);
//    }

    public void changeAmountForProduct(String productName, int amount) throws NoSuchRecordException {
        Product product = findProductByName(productName);
        product.setAmount(amount);
        updateProduct(product);
    }

    public void changePriceForProduct(String productName, BigDecimal price) throws NoSuchRecordException {
        Product product = findProductByName(productName);
        product.setPrice(price);
        updateProduct(product);
    }

    public List<Product> findAllProducts() {
        List<Product> products = daoFactory.getProductDAO().findAllProducts();
        return products;
    }

    public void deleteProduct(Product product) {
        daoFactory.getProductDAO().deleteProduct(product);
    }

    public void deleteALlShopOrders() {
        daoFactory.getShopOrderDAO().deleteAllOrders();
    }

    public void deleteAllCustomers() {
        daoFactory.getCustomerDAO().deleteAllCustomers();
    }

    public void updateCustomer(Customer customer) {
        daoFactory.getCustomerDAO().saveOrUpdateCustomer(customer);
    }

    public void addOrder(List<Product> productList, Customer customer) {

            daoFactory.getShopOrderDAO().createOrderWithProductList(productList, customer);

    }

    public void addNewCustomer(Customer customer)  {
        try {
            daoFactory.getCustomerDAO().createCustomer(customer);
        } catch (RecordAlreadyExistsException e) {
            log.info("Log: " + e.MESSAGE);
        }
    }

    public Customer findCustomerByName(String loginName) {
        Customer customer = daoFactory.getCustomerDAO().findCustomerByName(loginName);
        return customer;
    }

    public Customer findCustomerById(int id) {
        Customer customer = daoFactory.getCustomerDAO().findCustomerById(id);
        return customer;
    }

    public List<ShopOrder> findCustomerOrders(Customer customer) {
        List<ShopOrder> orders = daoFactory.getShopOrderDAO().findAllOrdersOfCertainCustomer(customer);
        return orders;
    }

    public void deleteShopOrder(ShopOrder order) {
      ShopOrder shopOrder = daoFactory.getShopOrderDAO().findOrderById(order.getOrderId());
      daoFactory.getShopOrderDAO().deleteOrder(shopOrder);

    }

    public Product findProductById(int pId) {
        Product product = daoFactory.getProductDAO().findProductById(pId);
        return product;
    }

}





