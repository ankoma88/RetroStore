package com.ak.rstore.dao.impl;

import com.ak.rstore.dao.interfaces.DAOFactory;
import com.ak.rstore.dao.interfaces.CategoryDAO;
import com.ak.rstore.dao.interfaces.CustomerDAO;
import com.ak.rstore.dao.interfaces.ProductDAO;
import com.ak.rstore.dao.interfaces.ShopOrderDAO;

public class DAOFactoryImpl extends DAOFactory {

    private static CustomerDAO customerDAO = null;
    private static CategoryDAO categoryDAO = null;
    private static ShopOrderDAO shopOrderDAO = null;
    private static ProductDAO productDAO = null;

    private static DAOFactoryImpl instance = null;

    public static synchronized DAOFactoryImpl getInstance() {
        if (instance == null) instance = new DAOFactoryImpl();
        return instance;
    }

    private DAOFactoryImpl() {
    }

    @Override
    public CustomerDAO getCustomerDAO() {
        if (customerDAO == null) {
            customerDAO = new CustomerDAOImpl();
        }
        return customerDAO;
    }

    @Override
    public CategoryDAO getCategoryDAO() {
        if (categoryDAO == null) {
            categoryDAO = new CategoryDAOImpl();
        }
        return categoryDAO;
    }

    @Override
    public ProductDAO getProductDAO() {
        if (productDAO == null) {
            productDAO = new ProductDAOImpl();
        }
        return productDAO;
    }

    @Override
    public ShopOrderDAO getShopOrderDAO() {
        if (shopOrderDAO == null) {
            shopOrderDAO = new ShopOrderDAOImpl();
        }
        return shopOrderDAO;
    }
}
