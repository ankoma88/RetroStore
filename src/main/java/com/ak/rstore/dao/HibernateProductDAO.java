package com.ak.rstore.dao;

import com.ak.rstore.model.Category;
import com.ak.rstore.model.Product;
import com.ak.rstore.model.ShopOrder;
import com.ak.rstore.util.ORMUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateProductDAO implements ProductDAO {
    @Override
    public void createProduct(Product product) {
        Session session = ORMUtil.currentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(product);
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
    public void saveOrUpdateProduct(Product product) {
        Session session = ORMUtil.currentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(product);
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
    public boolean deleteProduct(Product product) {
        Session session = ORMUtil.currentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(product);
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
    public int deleteAllProducts() {
        Session session = ORMUtil.currentSession();
        String hql = "DELETE FROM Product p";
        Query query = session.createQuery(hql);
        return query.executeUpdate();
    }

    @Override
    public Product findProductById(int productId) {
        Session session = ORMUtil.currentSession();
        return (Product) session.get(Product.class, productId);
    }

    @Override
    public Product findProductByName(String name) {
        Session session = ORMUtil.currentSession();
        String hql = "FROM Product p WHERE p.name= :name";
        Query query = session.createQuery(hql).setParameter("name", name);
        return (Product) query.list().get(0);
    }

    @Override
    public List findAllProductsOfCategory(Category category) {
        Session session = ORMUtil.currentSession();
        String hql = "FROM Product p WHERE p.category= :cat";
        Query query = session.createQuery(hql).setParameter("cat", category);
        return query.list();
    }

    @Override
    public List findAllProducts() {
        Session session = ORMUtil.currentSession();
        String hql = "FROM Product p";
        Query query = session.createQuery(hql);
        return query.list();
    }

    @Override
    public List findAllProductsInOrder(ShopOrder order) {
        Session session = ORMUtil.currentSession();
        String hql = "FROM Product p WHERE p.order= :shopOrder";
        Query query = session.createQuery(hql).setParameter("shopOrder", order);
        return query.list();
    }
}
