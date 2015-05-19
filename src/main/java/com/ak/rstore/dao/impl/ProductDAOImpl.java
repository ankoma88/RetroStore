package com.ak.rstore.dao.impl;

import com.ak.rstore.dao.interfaces.ProductDAO;
import com.ak.rstore.exceptions.RecordAlreadyExistsException;
import com.ak.rstore.model.Category;
import com.ak.rstore.model.Product;
import com.ak.rstore.model.ShopOrder;
import com.ak.rstore.util.ORMUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    @Override
    public void createProduct(Product product) throws RecordAlreadyExistsException {
        Product p = findProductByName(product.getName());
        if (p != null) {
            throw new RecordAlreadyExistsException();
        }
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
    public void setCategoryToProduct(int productId, Category category) {
        Product product = findProductById(productId);
        product.setCategory(category);
        saveOrUpdateProduct(product);
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
            Product p = (Product) session.load(Product.class, product.getProductId());
            if (p != null) {
                session.delete(p);
            }
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
        String hql = "FROM Product p";
        Query query = session.createQuery(hql);
        List<Product> results = query.list();
        Transaction tx = null;
        try {
            for (Product c : results) {
                tx = session.beginTransaction();
                Product product = (Product) session.load(Product.class, c.getProductId());
                if (product != null) {
                    session.delete(product);
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
    public Product findProductById(int productId) {
        Session session = ORMUtil.currentSession();
        return (Product) session.get(Product.class, productId);
    }

    @Override
    public Product findProductByName(String name) {
        Session session = ORMUtil.currentSession();
        String hql = "FROM Product p WHERE p.name= :name";
        Query query = session.createQuery(hql).setParameter("name", name);
        List results = query.list();
//        ORMUtil.closeSession();
        if (results.size() != 0) {
            return (Product) results.get(0);
        } else return null;
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
        List results = query.list();
//        ORMUtil.closeSession();
        if (results == null) {
            results = new ArrayList<>();
        }
        return results;
    }

    @Override
    public List findAllProductsInOrder(ShopOrder order) {
        Session session = ORMUtil.currentSession();
        String hql = "FROM Product p WHERE p.order= :shopOrder";
        Query query = session.createQuery(hql).setParameter("shopOrder", order);
        List results = query.list();
//        ORMUtil.closeSession();
        if (results == null) {
            results = new ArrayList<>();
        }
        return results;
    }



}















