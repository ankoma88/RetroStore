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

@WebServlet("/Cart.do")
public class CartServlet extends HttpServlet {
    static final Logger log = LoggerFactory.getLogger(CartServlet.class);
    private static ShopManager manager = ShopManager.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Customer currentCustomer = (Customer) req.getSession().getAttribute("currentCustomer");


            if (checkIfForwardToAdminPage(req, resp, currentCustomer)) {
                resp.sendRedirect("admin.jsp");
                return;
            }





            resp.sendRedirect("cart.jsp");
        }




    private boolean checkIfForwardToAdminPage(HttpServletRequest req, HttpServletResponse resp, Customer currentCustomer) throws ServletException, IOException {
        return (currentCustomer != null && currentCustomer.getLoginName().equals("admin"));
    }

}
