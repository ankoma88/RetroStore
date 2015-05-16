package com.ak.rstore.dao;

public abstract class DAOFactory {
    public static final int HIBERNATE = 1;
    public static final int JDBC = 0;

    public abstract CustomerDAO getCustomerDAO();
    public abstract CategoryDAO getCategoryDAO();
    public abstract ProductDAO getProductDAO();
    public abstract ShopOrderDAO getShopOrderDAO();

    public static  DAOFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case HIBERNATE:
                return HibernateDAOFactory.getInstance();
            case JDBC:
//                return new JDBCDAOFactory();
            default:
                return HibernateDAOFactory.getInstance();
        }
    }

}
