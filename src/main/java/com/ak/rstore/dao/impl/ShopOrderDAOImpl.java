package com.ak.rstore.dao.impl;

import com.ak.rstore.dao.interfaces.ShopOrderDAO;
import com.ak.rstore.model.Customer;
import com.ak.rstore.model.Product;
import com.ak.rstore.model.ShopOrder;
import com.ak.rstore.util.ORMUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class ShopOrderDAOImpl implements ShopOrderDAO {
    static final Logger log = LoggerFactory.getLogger(ShopOrderDAOImpl.class);

//    @Override
//    public void createOrder(ShopOrder order) {
//        Session session = ORMUtil.currentSession();
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//            session.persist(order);
//            tx.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            if (tx != null) {
//                tx.rollback();
//            }
//        } finally {
//            ORMUtil.closeSession();
//        }
//    }
//
//    @Override
//    public void saveOrUpdateOrder(ShopOrder order) {
//        Session session = ORMUtil.currentSession();
//        Transaction tx = null;
//        try {
//            tx = session.beginTransaction();
//            session.saveOrUpdate(order);
//            tx.commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            if (tx != null) {
//                tx.rollback();
//            }
//        } finally {
//            ORMUtil.closeSession();
//        }
//    }

    @Override
    public void createOrderWithProductList(List<Product> productList, Customer customer) {
        ShopOrder shopOrder = new ShopOrder();
        shopOrder.setOrderedProducts(productList);
        Session session = ORMUtil.currentSession();
        String hql = "FROM Customer cust WHERE cust.loginName= :loginName";
        Query query = session.createQuery(hql).setParameter("loginName", customer.getLoginName());
        String ln = customer.getLoginName();
        log.info("Log: loginname "+ln);

        Customer foundCustomer = (Customer) query.list().get(0);
        shopOrder.setCustomer(foundCustomer);

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(shopOrder);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            ORMUtil.closeSession();
        }
    }


    @Override
    public boolean deleteOrder(ShopOrder order) {
        Session session = ORMUtil.currentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(order);
            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            ORMUtil.closeSession();
        }
        return true;
    }

    @Override
    public int deleteAllOrders()  {
        Session session = ORMUtil.currentSession();
        String hql = "FROM ShopOrder o";
        Query query = session.createQuery(hql);
        List<ShopOrder> results = query.list();
        Transaction tx = null;
        try {
            for (ShopOrder o : results) {
                tx = session.beginTransaction();
                ShopOrder shopOrder = (ShopOrder) session.load(ShopOrder.class, o.getOrderId());
                if (shopOrder != null) {
                    session.delete(shopOrder);
                }
                tx.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            ORMUtil.closeSession();
        }
        return 0;
    }

    @Override
    public ShopOrder findOrderById(int orderId) {
        Session session = ORMUtil.currentSession();
        return (ShopOrder) session.get(ShopOrder.class, orderId);
    }

    @Override
    public List findAllOrders()  {
        Session session = ORMUtil.currentSession();
        String hql = "FROM ShopOrder o";
        Query query = session.createQuery(hql);
        List<ShopOrder> results = query.list();
//        ORMUtil.closeSession();
        if (results == null) {
            results = new ArrayList<ShopOrder>();
        }
        return results;
    }

    @Override
    public List findAllOrdersOfCertainCustomer(Customer customer) {
        Session session = ORMUtil.currentSession();
        String hql = "FROM ShopOrder o WHERE o.customer = :customer";
        Query query = session.createQuery(hql).setParameter("customer", customer);
        return query.list();
    }
}
