package com.ak.rstore.dao.impl;

import com.ak.rstore.dao.interfaces.CategoryDAO;
import com.ak.rstore.exceptions.RecordAlreadyExistsException;
import com.ak.rstore.model.Category;
import com.ak.rstore.util.ORMUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CategoryDAOImpl implements CategoryDAO {
    static final Logger log = LoggerFactory.getLogger(CategoryDAOImpl.class);

    @Override
    public void createCategory(Category category) throws RecordAlreadyExistsException {
        if (category.getName() == null) {
            category.setName("No category");
        }
        Category catFound = findCategoryByName(category.getName());
        if (catFound != null) {
            throw new RecordAlreadyExistsException();
        }
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
    public boolean deleteCategoryAndAllProducts(Category category) {
        Session session = ORMUtil.currentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Category cat = (Category) session.load(Category.class, category.getCat_id());
            if (cat != null) {
                session.delete(cat);
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
    public int deleteAllCategoriesWithProducts() {
        Session session = ORMUtil.currentSession();
        String hql = "FROM Category cat";
        Query query = session.createQuery(hql);
        List<Category> results = query.list();
        Transaction tx = null;
        try {
            for (Category c : results) {
                tx = session.beginTransaction();
                Category cat = (Category) session.load(Category.class, c.getCat_id());
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
    public Category findCategoryById(int catId) {
        Session session = ORMUtil.currentSession();
        Category category = (Category) session.load(Category.class, catId);
//        ORMUtil.closeSession();
        return category;
    }

    @Override
    public Category findCategoryByName(String catName) {
        Session session = ORMUtil.currentSession();
        String hql = "FROM Category cat WHERE cat.name= :catName";
        Query query = session.createQuery(hql).setParameter("catName", catName);
        List results = query.list();
//        ORMUtil.closeSession();
        if (results.size() != 0) {
            return (Category) results.get(0);
        } else return null;
    }

    @Override
    public List findAllCategories() {
        Session session = ORMUtil.currentSession();
        String hql = "FROM Category cat";
        Query query = session.createQuery(hql);
        List<Category> results = query.list();
//        ORMUtil.closeSession();
        if (results == null) {
            results = new ArrayList<Category>();
        }
        return results;
    }

}






















