package com.ak.rstore.servlets;

import com.ak.rstore.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginRegFilter implements Filter {
    static final Logger log = LoggerFactory.getLogger(StartServlet.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        Customer curCustomer = (Customer) req.getSession().getAttribute("currentCustomer");
        String url = req.getRequestURI();

        if (url.contains("/Logout.do")) {
            if (curCustomer != null) {
                req.getSession().setAttribute("currentCustomer", null);
            }
            resp.sendRedirect("home.jsp");
        } else {
            if (curCustomer != null) {
                req.getSession().setAttribute("currentCustomer", null);
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
