package com.ak.rstore.servlets;

import com.ak.rstore.exceptions.NoSuchRecordException;
import com.ak.rstore.manager.StoreHouseManager;
import com.ak.rstore.model.Category;
import com.ak.rstore.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Objects;

@WebServlet("/Admin.do")
public class AdminServlet extends HttpServlet {
    static final Logger log = LoggerFactory.getLogger(StartServlet.class);
    private static StoreHouseManager manager = StoreHouseManager.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String cName = req.getParameter("cName");
        String pName = req.getParameter("pName");
        String pDesc = req.getParameter("pDesc");
        String pPrice = req.getParameter("pPrice");
        String pAmount = req.getParameter("pAmount");
        String pYear = req.getParameter("pYear");
        String pPhoto = req.getParameter("pPhoto");
        String pCat = req.getParameter("pCat");

        if (Objects.equals(pAmount, "")) {
            pAmount = "0";
        }

        if (Objects.equals(pPrice, "")) {
            pPrice = "0";
        }
        if (Objects.equals(pYear, "")) {
            pYear = "0";
        }


        if (cName != null && !Objects.equals(cName, "")) {
            Category checkCat = manager.findCategoryByName(cName);
            if (checkCat != null) {
                req.setAttribute("catAddResult", "Such category already exists.");
            } else {
                manager.addCategory(new Category(cName));
                req.setAttribute("catAddResult", "Category added.");
            }
            resp.sendRedirect("/Start.do");

        } else if (pName != null && pDesc != null) {

            Product checkProd = manager.findProductByName(pName);
            if (checkProd != null) {
                req.setAttribute("prodAddResult", "Such product already exists.");
            } else {

                Category newC = null;
                Category chkCat = manager.findCategoryByName(pCat);
                if (chkCat == null) {
                    manager.addCategory(new Category(pCat));
                    newC = manager.findCategoryByName(pCat);
                } else {
                    newC = chkCat;
                }



                Product newProd = new Product(pName, pDesc,
                        new BigDecimal(pPrice),Integer.valueOf(pAmount), Integer.valueOf(pYear), pPhoto);

                manager.addProduct(newProd);

                try {
                    manager.changeCategoryForProduct(newProd.getName(), newC.getName());
                } catch (NoSuchRecordException e) {
                    log.info("Log: "+e.MESSAGE);
                }


                req.setAttribute("prodAddResult", "Product added.");
                resp.sendRedirect("/Start.do");
            }

        }

        else  {
            req.getRequestDispatcher("admin.jsp").forward(req, resp);
        }


    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String delCatName = req.getParameter("delCatName");
        String delLeaveCatName = req.getParameter("delLeaveCatName");
        String delProdName = req.getParameter("delProdName");

        String edtProdsOfCatName = req.getParameter("edtProdsOfCatName");
        String prodForEdtName = req.getParameter("prodForEdtName");

        if (delCatName != null) {
            Category categoryForDel =  manager.findCategoryByName(delCatName);
            if (categoryForDel != null) {
                manager.deleteCategoryWithAllProducts(categoryForDel);
                req.setAttribute("catDelResult", "Category deleted.");
            } else {
                req.setAttribute("catDelResult", "Can't delete non-existing category.");
            }
            resp.sendRedirect("/Start.do");

        } else if (delLeaveCatName != null) {
            Category categoryForSoftDel = manager.findCategoryByName(delLeaveCatName);
            if (categoryForSoftDel != null) {
                manager.deleteCategoryButLeaveProducts(categoryForSoftDel);
                req.setAttribute("catDelResult", "Category deleted but not its products.");
            } else {
                req.setAttribute("catDelResult", "Can't delete non-existing category.");
            }
            resp.sendRedirect("/Start.do");


        } else if (edtProdsOfCatName != null) {
            Category categoryForEditingProds = manager.findCategoryByName(edtProdsOfCatName);
            if (categoryForEditingProds != null) {
                req.setAttribute("categoryForEditingProds", categoryForEditingProds);
                req.getRequestDispatcher("admin.jsp").forward(req, resp);
            }

        } else if (prodForEdtName != null) {
            Product edtblPrdct = manager.findProductByName(prodForEdtName);
            if (edtblPrdct != null) {
                req.setAttribute("editableProduct", edtblPrdct);
            }
            req.getRequestDispatcher("admin.jsp").forward(req, resp);
        }
         else if (delProdName != null) {
            Product prodForDel = manager.findProductByName(delProdName);
            if (prodForDel != null) {
                manager.deleteProduct(prodForDel);
                req.setAttribute("prodDelResult", "Product deleted.");
            } else {
                req.setAttribute("prodDelResult", "Can't delete product.");
            }
            resp.sendRedirect("/Start.do");
        }



    }
}
