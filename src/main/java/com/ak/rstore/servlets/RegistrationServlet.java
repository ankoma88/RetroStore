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
    static final Logger log = LoggerFactory.getLogger(StartServlet.class);
    private static ShopManager manager = ShopManager.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String loginName = req.getParameter("loginName");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        Customer check = manager.findCustomerByName(loginName);
        if (check != null) {
            req.setAttribute("regResult", "Such customer already exists.");
            req.getRequestDispatcher("registration.jsp").forward(req, resp);
        } else {
            manager.addNewCustomer(new Customer(firstName, lastName, loginName, password, email));
            req.setAttribute("regResult", "Registration successful!");
            req.getRequestDispatcher("registration.jsp").forward(req, resp);
        }
    }
}
