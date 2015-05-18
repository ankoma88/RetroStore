package com.ak.rstore.servlets;

import com.ak.rstore.manager.StoreHouseManager;
import com.ak.rstore.model.Category;
import com.ak.rstore.model.Customer;
import com.ak.rstore.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class StartServlet extends HttpServlet {
    static final Logger log = LoggerFactory.getLogger(StartServlet.class);
    private static StoreHouseManager manager = StoreHouseManager.INSTANCE;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        loadData(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        loadData(req, resp);
    }

    private void loadData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Category> categories = manager.retrieveCategories();
        List<Product> newProducts = manager.retrieveNewProducts();
        getServletContext().setAttribute("categories", categories);
        getServletContext().setAttribute("newProducts", newProducts);

        Customer sessionUser = (Customer) req.getSession().getAttribute("currentCustomer");

        if (sessionUser != null && sessionUser.getLoginName().equals("admin")) {
            req.getRequestDispatcher("admin.jsp").forward(req, resp);
            return;
        }


        req.getRequestDispatcher("home.jsp").forward(req,resp);
    }
}




























