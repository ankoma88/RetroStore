package com.ak.rstore.servlets;

import com.ak.rstore.manager.ShopManager;
import com.ak.rstore.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Register.do")
public class RegistrationServlet extends HttpServlet {
    static final Logger log = LoggerFactory.getLogger(RegistrationServlet.class);
    private static ShopManager manager = ShopManager.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("loginName");
        String pass = req.getParameter("password");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String email = req.getParameter("email");

        Customer newCustomer = manager.findCustomerByName(login);

        if (newCustomer != null) {
            req.getSession().setAttribute("logResult", "You are already registered. Please login.");
            req.getRequestDispatcher("home.jsp").forward(req, resp);
        } else {
            newCustomer = new Customer(firstName, lastName, login, pass, email);
            manager.addNewCustomer(newCustomer);
            req.getSession().setAttribute("logResult", "Registration successful! Please login.");

            req.getRequestDispatcher("home.jsp").forward(req, resp);
        }


    }




}
