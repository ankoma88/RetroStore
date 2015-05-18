package com.ak.rstore.servlets;

import com.ak.rstore.manager.StoreHouseManager;
import com.ak.rstore.model.Category;
import com.ak.rstore.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowcaseServlet extends HttpServlet {
    static final Logger log = LoggerFactory.getLogger(ShowcaseServlet.class);
    private static StoreHouseManager manager = StoreHouseManager.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String catName = req.getParameter("catName");
        String prodName = req.getParameter("prodName");

        if (catName != null) {
            Category chosenCategory = manager.findCategoryByName(catName);
            if (chosenCategory == null) {
                chosenCategory = new Category("No such category");
            }
            req.setAttribute("chosenCategory", chosenCategory);
            req.setAttribute("choice","category");
        } else if (prodName != null) {
                Product chosenProduct = manager.findProductByName(prodName);
                req.setAttribute("chosenProduct", chosenProduct);
                req.setAttribute("choice","product");
        }



        req.getRequestDispatcher("home.jsp").forward(req, resp);

    }
}
