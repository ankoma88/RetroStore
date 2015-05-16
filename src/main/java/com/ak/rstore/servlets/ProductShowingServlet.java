package com.ak.rstore.servlets;

import com.ak.rstore.manager.StoreHouseManager;
import com.ak.rstore.model.Category;
import com.ak.rstore.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ProductShowingServlet extends HttpServlet {
    static final Logger log = LoggerFactory.getLogger(ProductShowingServlet.class);
    StoreHouseManager manager = StoreHouseManager.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Category> categories = manager.retrieveCategories();
        List<Product> newProducts = manager.retrieveNewProducts();
        session.setAttribute("categories", categories);
        session.setAttribute("newProducts", newProducts);

        String categoryName = req.getParameter("cat");
        String productName = req.getParameter("prod");
        log.info("Log: chosen category: "+categoryName);
        log.info("Log: chosen product: "+productName);

        if (categoryName != null) {
            Category chosenCategory = manager.findCategoryByName(categoryName);
            log.info("Log: chosen category from db: "+chosenCategory.getName());
            if (chosenCategory != null) {
                List<Product> productList = manager.retrieveAllProductsOfCategory(chosenCategory);
                session.setAttribute("productsOfCategory", productList);
            }
        }




        RequestDispatcher view = req.getRequestDispatcher("home.jsp");
        view.forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
