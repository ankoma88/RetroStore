package com.ak.rstore.dao;

public class HibernateDAOFactory extends DAOFactory {
    @Override
    public CustomerDAO getCustomerDAO() {
        return new HibernateCustomerDAO();
    }

    @Override
    public CategoryDAO getCategoryDAO() {
        return new HibernateCategoryDAO();
    }

    @Override
    public ProductDAO getProductDAO() {
        return new HibernateProductDAO();
    }

    @Override
    public ShopOrderDAO getShopOrderDAO() {
        return new HibernateShopOrderDAO();
    }
}
