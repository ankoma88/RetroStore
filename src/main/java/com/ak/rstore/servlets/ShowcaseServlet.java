package com.ak.rstore.servlets;

import com.ak.rstore.manager.ShopManager;
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
    private static ShopManager manager = ShopManager.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String catName = req.getParameter("catName");
        String prodName = req.getParameter("prodName");

        if (catName != null) {
            chooseCategory(req, resp, catName);
        } else if (prodName != null) {
            chooseProduct(req, resp, prodName);
        }
        req.getRequestDispatcher("home.jsp").forward(req, resp);
    }

    private void chooseCategory(HttpServletRequest req, HttpServletResponse resp, String catName) {
        Category chosenCategory = manager.findCategoryByName(catName);
        if (chosenCategory == null) {
            chosenCategory = new Category("No such category");
        }
        req.setAttribute("chosenCategory", chosenCategory);
        req.setAttribute("choice", "category");
    }

    private void chooseProduct(HttpServletRequest req, HttpServletResponse resp, String prodName) {
        Product chosenProduct = manager.findProductByName(prodName);
        req.setAttribute("chosenProduct", chosenProduct);
        req.setAttribute("choice", "product");
    }
}
