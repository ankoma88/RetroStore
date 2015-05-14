package com.ak.rstore;

import com.ak.rstore.manager.CategoryManager;
import com.ak.rstore.model.Category;

import java.util.List;

public class MainTest {
    public static void main(String[] args) {
//        SessionFactory sessionFactory = ORMUtil.getSessionFactory();
//        Session session = sessionFactory.openSession();
//
//        session.beginTransaction();
//
//        Product p1 = new Product();
//        p1.setName("Picture");
//
//        Category c1 = new Category();
//        c1.setName("Pictures");
//
//        int productId = (Integer) session.save(p1);
//        session.getTransaction().commit();
//
//
//        Product product = (Product) session.get(Product.class, productId);
//        System.out.println(product);
//
//
//
//
//        session.beginTransaction();
//
//        HashSet<Product> productSet = new HashSet<Product>();
//        productSet.add(p1);
//
//        c1.setProductSet(productSet);
//        int categoryId = (Integer) session.save(c1);
//        session.getTransaction().commit();
//
//        Category category = (Category) session.get(Category.class, categoryId);
//        System.out.println(category);
//
//        Set<Product> set = category.getProductSet();
//
//        for (Product p : set) {
//            System.out.println(p);
//        }
//
//        session.close();



        List<Category> categories = CategoryManager.retrieveCategories();

        System.out.println(categories.size());



    }
}
