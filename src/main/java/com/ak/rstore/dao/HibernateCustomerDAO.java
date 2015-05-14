package com.ak.rstore.dao;

import com.ak.rstore.model.Customer;
import com.ak.rstore.util.ORMUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateCustomerDAO implements CustomerDAO {
    @Override
    public void createCustomer(Customer customer) {
        Session session = ORMUtil.currentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(customer);
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
    public void saveOrUpdateCustomer(Customer customer) {
        Session session = ORMUtil.currentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(customer);
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
    public boolean deleteCustomer(Customer customer) {
        Session session = ORMUtil.currentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(customer);
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
    public int deleteAllCustomers() {
        Session session = ORMUtil.currentSession();
        String hql = "DELETE FROM Customer cust";
        Query query = session.createQuery(hql);
        return query.executeUpdate();
    }

    @Override
    public Customer findCustomerById(int customerId) {
        Session session = ORMUtil.currentSession();
        return (Customer) session.get(Customer.class, customerId);
    }

    @Override
    public Customer findCustomerByName(String loginName) {
        Session session = ORMUtil.currentSession();
        String hql = "FROM Customer cust WHERE cust.loginName= :loginName";
        Query query = session.createQuery(hql).setParameter("loginName", loginName);
        return (Customer) query.list().get(0);
    }

    @Override
    public List findAllCustomers() {
        Session session = ORMUtil.currentSession();
        String hql = "FROM Customer cust";
        Query query = session.createQuery(hql);
        return query.list();
    }
}
