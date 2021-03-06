package com.ak.rstore.servlets;

import com.ak.rstore.manager.ShopManager;
import com.ak.rstore.model.Customer;
import com.ak.rstore.util.Properties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebServlet("/Login.do")
public class LoginServlet extends HttpServlet {
    static final Logger log = LoggerFactory.getLogger(LoginServlet.class);
    private static ShopManager manager = ShopManager.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String pass = req.getParameter("pass");

        Customer currentCustomer = manager.findCustomerByName(login);

        if (currentCustomer != null) {
            if(currentCustomer.getPassword().equals(pass))
                req.getSession().setAttribute("currentCustomer", currentCustomer);
            if (checkAdmin(currentCustomer)) {
                resp.sendRedirect("admin.jsp");
            } else {
                req.getRequestDispatcher("home.jsp").forward(req, resp);
            }
        } else {
            req.getSession().setAttribute("logResult", "You are not logged. Wrong username or password.");
            req.getRequestDispatcher("home.jsp").forward(req, resp);
        }
    }

    private boolean checkAdmin (Customer currentCustomer) throws IOException, ServletException {
           return (Objects.equals(currentCustomer.getLoginName(), Properties.ADMIN_NAME) &&
                   Objects.equals(currentCustomer.getPassword(), Properties.ADMIN_PASSWORD));
        }




}
