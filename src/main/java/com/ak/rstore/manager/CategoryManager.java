package com.ak.rstore.manager;

import com.ak.rstore.dao.CategoryDAO;
import com.ak.rstore.dao.DAOFactory;
import com.ak.rstore.model.Category;
import com.ak.rstore.util.Properties;
import java.util.*;

public class CategoryManager {

    public static List<Category> retrieveCategories() {
        DAOFactory daoFactory = DAOFactory.getDAOFactory(Properties.ORM);
        CategoryDAO categoryDAO = daoFactory.getCategoryDAO();
        List categories = categoryDAO.findAllCategories();
        if (categories == null) {
            return new ArrayList<Category>();
        }
        return categories;
    }

}
