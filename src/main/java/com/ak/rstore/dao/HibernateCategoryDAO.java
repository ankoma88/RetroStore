package com.ak.rstore.dao;

import com.ak.rstore.model.Category;
import com.ak.rstore.util.ORMUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HibernateCategoryDAO implements CategoryDAO {



    @Override
    public void createCategory(Category category) {
        Session session = ORMUtil.currentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(category);
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
    public void saveOrUpdateCategory(Category category) {
        Session session = ORMUtil.currentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.saveOrUpdate(category);
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
    public boolean deleteCategory(Category category) {
        Session session = ORMUtil.currentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(category);
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
    public int deleteAllCategories() {
        Session session = ORMUtil.currentSession();
        String hql = "DELETE FROM Category cat";
        Query query = session.createQuery(hql);
        return query.executeUpdate();
    }

    @Override
    public Category findCategoryById(int catId) {
        Session session = ORMUtil.currentSession();
        return (Category) session.get(Category.class, catId);
    }

    @Override
    public Category findCategoryByName(String catName) {
        Session session = ORMUtil.currentSession();
        String hql = "FROM Category cat WHERE cat.name= :catName";
        Query query = session.createQuery(hql).setParameter("catName", catName);
        return (Category) query.list().get(0);
    }

    @Override
    public List findAllCategories() {
        Session session = ORMUtil.currentSession();
        String hql = "FROM Category cat";
        Query query = session.createQuery(hql);
        List<Category> results = query.list();
        ORMUtil.closeSession();
        return results;

    }
}
