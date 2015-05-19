package com.ak.rstore.servlets;

import com.ak.rstore.manager.ShopManager;
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
import java.util.Objects;

public class StartServlet extends HttpServlet {
    static final Logger log = LoggerFactory.getLogger(StartServlet.class);
    private static ShopManager manager = ShopManager.INSTANCE;


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        loadData(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        loadData(req, resp);
    }

    private void loadData(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reqFrom = req.getParameter("reqFrom");

        List<Category> categories = manager.retrieveCategories();
        List<Product> newProducts = manager.retrieveNewProducts();
        getServletContext().setAttribute("categories", categories);
        getServletContext().setAttribute("newProducts", newProducts);

        if (Objects.equals(reqFrom, "fromAdm")) {
            resp.sendRedirect("admin.jsp");
            return;
        }

        if (checkAndForwardToAdminPage(req, resp)) {
            resp.sendRedirect("admin.jsp");
            return;
        }


        resp.sendRedirect("home.jsp");
    }

    private boolean checkAndForwardToAdminPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer sessionUser = (Customer) req.getSession().getAttribute("currentCustomer");
        return (sessionUser != null && sessionUser.getLoginName().equals("admin"));
    }



}




























