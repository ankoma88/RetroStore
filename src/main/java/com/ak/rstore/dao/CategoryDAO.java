package com.ak.rstore.dao;

import com.ak.rstore.model.Category;

import java.util.List;

public interface CategoryDAO {
    void createCategory(Category category);
    void saveOrUpdateCategory(Category category);
    boolean deleteCategory(Category category);
    int deleteAllCategories();
    Category findCategoryById(int catId);
    Category findCategoryByName(String catName);
    List findAllCategories();
}
