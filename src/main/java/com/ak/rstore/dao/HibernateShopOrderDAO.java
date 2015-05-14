package com.ak.rstore.dao;

import com.ak.rstore.model.Customer;
import com.ak.rstore.model.Product;
import com.ak.rstore.model.ShopOrder;
import com.ak.rstore.util.ORMUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateShopOrderDAO implements ShopOrderDAO {
    @Override
    public void createOrder(ShopOrder order) {
        Session session = ORMUtil.currentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(order);
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
    public void saveOrUpdateOrder(ShopOrder order) {
        Session session = ORMUtil.currentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(order);
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
    public void createOrderWithProductList(ShopOrder order, List<Product> productList) {
        order.setOrderedProducts(productList);
        Session session = ORMUtil.currentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(order);
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
    public int deleteAllOrders() {
        Session session = ORMUtil.currentSession();
        String hql = "DELETE FROM ShopOrder o";
        Query query = session.createQuery(hql);
        return query.executeUpdate();
    }

    @Override
    public ShopOrder findOrderById(int orderId) {
        Session session = ORMUtil.currentSession();
        return (ShopOrder) session.get(ShopOrder.class, orderId);
    }

    @Override
    public List findAllOrders() {
        Session session = ORMUtil.currentSession();
        String hql = "FROM ShopOrder o";
        Query query = session.createQuery(hql);
        return query.list();
    }

    @Override
    public List findAllOrdersOfCertainCustomer(Customer customer) {
        Session session = ORMUtil.currentSession();
        String hql = "FROM ShopOrder o WHERE o.customer = :customer";
        Query query = session.createQuery(hql).setParameter("customer", customer);
        return query.list();
    }
}
