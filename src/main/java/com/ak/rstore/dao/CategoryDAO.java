package com.ak.rstore.dao;

import com.ak.rstore.exceptions.RecordAlreadyExistsException;
import com.ak.rstore.model.Category;

import java.util.List;

public interface CategoryDAO {
    void createCategory(Category category) throws RecordAlreadyExistsException;
    boolean deleteCategoryAndAllProducts(Category category);
    int deleteAllCategoriesWithProducts();
    Category findCategoryById(int catId);
    Category findCategoryByName(String catName);
    List findAllCategories();
}
