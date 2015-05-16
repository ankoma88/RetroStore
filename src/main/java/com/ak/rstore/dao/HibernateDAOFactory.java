package com.ak.rstore.dao;

public class HibernateDAOFactory extends DAOFactory {

    private static CustomerDAO customerDAO = null;
    private static CategoryDAO categoryDAO = null;
    private static ShopOrderDAO shopOrderDAO = null;
    private static ProductDAO productDAO = null;

    private static HibernateDAOFactory instance = null;

    public static synchronized HibernateDAOFactory getInstance() {
        if (instance == null) instance = new HibernateDAOFactory();
        return instance;
    }

    private HibernateDAOFactory() {
    }

    @Override
    public CustomerDAO getCustomerDAO() {
        if (customerDAO == null) {
            customerDAO = new HibernateCustomerDAO();
        }
        return customerDAO;
    }

    @Override
    public CategoryDAO getCategoryDAO() {
        if (categoryDAO == null) {
            categoryDAO = new HibernateCategoryDAO();
        }
        return categoryDAO;
    }

    @Override
    public ProductDAO getProductDAO() {
        if (productDAO == null) {
            productDAO = new HibernateProductDAO();
        }
        return productDAO;
    }

    @Override
    public ShopOrderDAO getShopOrderDAO() {
        if (shopOrderDAO == null) {
            shopOrderDAO = new HibernateShopOrderDAO();
        }
        return shopOrderDAO;
    }
}
