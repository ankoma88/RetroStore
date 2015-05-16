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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class StartServlet extends HttpServlet {
    static final Logger log = LoggerFactory.getLogger(StartServlet.class);
    StoreHouseManager manager = StoreHouseManager.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        List<Category> categories = manager.retrieveCategories();
        List<Product> newProducts = manager.retrieveNewProducts();
        req.setAttribute("categories", categories);
        req.setAttribute("newProducts", newProducts);


//        RequestDispatcher view = req.getRequestDispatcher("home.jsp");
//        view.forward(req, resp);

        resp.sendRedirect("home.jsp");




    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
