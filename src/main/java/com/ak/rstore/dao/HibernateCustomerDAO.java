package com.ak.rstore.dao;

import com.ak.rstore.exceptions.RecordAlreadyExistsException;
import com.ak.rstore.model.Customer;
import com.ak.rstore.util.ORMUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class HibernateCustomerDAO implements CustomerDAO {
    static final Logger log = LoggerFactory.getLogger(HibernateCategoryDAO.class);

    @Override
    public void createCustomer(Customer customer) throws RecordAlreadyExistsException {
        Customer custFound = findCustomerByName(customer.getLoginName());
        if (custFound != null) {
            throw new RecordAlreadyExistsException();
        }
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
        String hql = "FROM Customer c";
        Query query = session.createQuery(hql);
        List<Customer> results = query.list();
        Transaction tx = null;
        try {
            for (Customer c : results) {
                tx = session.beginTransaction();
                Customer cat = (Customer) session.load(Customer.class, c.getCustomerId());
                if (cat != null) {
                    session.delete(cat);
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
    public Customer findCustomerById(int customerId) {
        Session session = ORMUtil.currentSession();
        return (Customer) session.get(Customer.class, customerId);
    }

    @Override
    public Customer findCustomerByName(String loginName) {
        Session session = ORMUtil.currentSession();
        String hql = "FROM Customer cust WHERE cust.loginName= :loginName";
        Query query = session.createQuery(hql).setParameter("loginName", loginName);
        List results = query.list();
//        ORMUtil.closeSession();
        if (results.size() != 0) {
            return (Customer) results.get(0);
        } else return null;
    }

    @Override
    public List findAllCustomers() {
        Session session = ORMUtil.currentSession();
        String hql = "FROM Customer cust";
        Query query = session.createQuery(hql);
        List<Customer> results = query.list();
//        ORMUtil.closeSession();
        if (results == null) {
            results = new ArrayList<>();
        }
        return results;
    }
}
