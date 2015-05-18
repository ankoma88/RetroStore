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

@WebServlet("/ProductEdit.do")
public class ProductEditServlet extends HttpServlet {
    static final Logger log = LoggerFactory.getLogger(StartServlet.class);
    private static StoreHouseManager manager = StoreHouseManager.INSTANCE;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pName = req.getParameter("pName");
        String pDesc = req.getParameter("pDesc");
        String pAmount = req.getParameter("pAmount");
        String pPrice = req.getParameter("pPrice");
        String pYear = req.getParameter("pYear");
        String pPhoto = req.getParameter("pPhoto");
        String pCat = req.getParameter("pCat");

        Integer editableProductId = Integer.parseInt(req.getParameter("editableProductId"));
        String oldCatName = req.getParameter("oldCatName");

        if (editableProductId != 0) {

            Product updProd = manager.findProductById(editableProductId);


            if (pName != null && !Objects.equals(pName, updProd.getName())) {
                updProd.setName(pName);
            }
            if (pDesc != null && !Objects.equals(pDesc, updProd.getDescription())) {
                updProd.setDescription(pDesc);
            }
            if (pAmount != null && Integer.valueOf(pAmount) != updProd.getAmount()) {
                updProd.setAmount(Integer.valueOf(pAmount));
            }
            if (pPrice != null && !Objects.equals(new BigDecimal(pPrice), updProd.getPrice())) {
                updProd.setPrice(new BigDecimal(pPrice));
            }
            if (pYear != null && Integer.valueOf(pYear) != updProd.getYear()) {
                updProd.setYear(Integer.valueOf(pYear));
            }


            manager.updateProduct(updProd);


            Category chkCat = null;
            if (pCat != null && !Objects.equals(pCat, oldCatName)) {
                chkCat = manager.findCategoryByName(pCat);
                if (chkCat == null) {
                    chkCat = new Category(pCat);
                    manager.addCategory(chkCat);
                    try {
                        manager.changeCategoryForProduct(editableProductId, chkCat.getName());
                    } catch (NoSuchRecordException e) {
                        log.info("Log: "+e.MESSAGE);
                        e.printStackTrace();
                    }
                } else {
                    try {
                        manager.changeCategoryForProduct(editableProductId, chkCat.getName());
                    } catch (NoSuchRecordException e) {
                        log.info("Log: "+e.MESSAGE);
                        e.printStackTrace();
                    }
                }

            }


            req.setAttribute("prodEditResult", "Product updated.");
            req.getRequestDispatcher("/Start.do").forward(req, resp);
            return;
        } else  {
            req.getRequestDispatcher("admin.jsp").forward(req, resp);
        }

    }



}
