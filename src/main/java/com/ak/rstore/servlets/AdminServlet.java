package com.ak.rstore.servlets;

import com.ak.rstore.exceptions.NoSuchRecordException;
import com.ak.rstore.exceptions.RecordAlreadyExistsException;
import com.ak.rstore.manager.ShopManager;
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
    private static ShopManager manager = ShopManager.INSTANCE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String choice = req.getParameter("choice");

        String delCatName = req.getParameter("delCatName");
        String delLeaveCatName = req.getParameter("delLeaveCatName");
        String delProdName = req.getParameter("delProdName");
        String edtProdsOfCatName = req.getParameter("edtProdsOfCatName");
        String prodForEdtName = req.getParameter("prodForEdtName");

        switch (choice) {
            case ("delCatFull"):
                if (delCatName != null) deleteCategoryCompletely(req, resp, delCatName); break;
            case ("delCatSoft"):
                if (delLeaveCatName != null) deleteCategoryLeaveProducts(req, resp, delLeaveCatName); break;
            case ("chooseCatForEdtPrds"):
                if (edtProdsOfCatName != null) choseCategoryForEditingProducts(req, resp, edtProdsOfCatName); break;
            case ("edtProd"):
                if (prodForEdtName != null) choseProductForEdit(req, resp, prodForEdtName); break;
            case ("delProd"):
                if (delProdName != null) deleteProduct(req, resp, delProdName); break;
            default:
                resp.sendRedirect("admin.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String choice = req.getParameter("choice");

        String cName = req.getParameter("cName");
        String editableProductId = req.getParameter("editableProductId");
        String oldCatName = req.getParameter("oldCatName");

        String pName = req.getParameter("pName");
        String pDesc = req.getParameter("pDesc");
        String pPrice = req.getParameter("pPrice");
        String pAmount = req.getParameter("pAmount");
        String pYear = req.getParameter("pYear");
        String pPhoto = req.getParameter("pPhoto");
        String pCat = req.getParameter("pCat");


        if (pCat == null|| Objects.equals(pCat, "")) pCat = "No category";
        if (pName == null|| Objects.equals(pName, "")) pName = "No name";
        if(pDesc==null|| Objects.equals(pDesc, "")) pDesc = "No description";
        if(pAmount==null|| Objects.equals(pAmount, "")) pAmount = "0";
        if(pPrice==null|| Objects.equals(pPrice, "")) pPrice = "0";
        if(pYear==null|| Objects.equals(pYear, "")) pYear = "0";
        if(pPhoto==null|| Objects.equals(pPhoto, "")) pPhoto = "No photo";

        if (cName == null|| Objects.equals(cName, "")) cName = "No category";

        if(editableProductId==null|| Objects.equals(editableProductId, "")) editableProductId = "0";
        if(oldCatName==null|| Objects.equals(oldCatName, "")) oldCatName = "No category";

        String[] params = {pName, pDesc, pAmount, pPrice, pYear, pPhoto, pCat};

        switch (choice) {
            case ("addCat"):
                addCategory(req, resp, cName); break;
            case ("addProd"):
                addProduct(req, resp, params); break;
            case ("editP"):
                changeProduct(req, resp, params, editableProductId, oldCatName); break;
            default: req.getRequestDispatcher("/Start.do?reqFrom=fromAdm");
        }

    }

    private void changeProduct(HttpServletRequest req, HttpServletResponse resp, String[] params, String editableProductId, String oldCatName) throws ServletException, IOException {
        Product updProd = manager.findProductById(Integer.parseInt(editableProductId));
        if (updProd == null) {
            resp.sendRedirect("admin.jsp");
            return;
        }

        if (params[0] != null && !Objects.equals(params[0], updProd.getName())) updProd.setName(params[0]);
        if (params[1] != null && !Objects.equals(params[1], updProd.getDescription())) updProd.setDescription(params[1]);
        if (params[2] != null && Integer.valueOf(params[2]) != updProd.getAmount())
            updProd.setAmount(Integer.valueOf(params[2]));
        if (params[3] != null && !Objects.equals(new BigDecimal(params[3]), updProd.getPrice()))
            updProd.setPrice(new BigDecimal(params[3]));
        if (params[4] != null && Integer.valueOf(params[4]) != updProd.getYear()) updProd.setYear(Integer.valueOf(params[4]));

        //TODO photo

        manager.updateProduct(updProd);

        changeCategoryOfProduct(editableProductId, params[6], oldCatName);

        req.setAttribute("prodEditResult", "Product updated.");
        resp.sendRedirect("/Start.do");
    }

    private void changeCategoryOfProduct(String editableProductId, String pCat, String oldCatName) {
        Category chkCat;
        if ((pCat != null) && !Objects.equals(pCat, oldCatName)) {
            chkCat = manager.findCategoryByName(pCat);
            if (chkCat == null) {
                chkCat = new Category(pCat);
                manager.addCategory(chkCat);
                try {
                    manager.changeCategoryForProduct(Integer.parseInt(editableProductId), chkCat.getName());
                } catch (NoSuchRecordException e) {
                    log.info("Log: " + e.MESSAGE);
                    e.printStackTrace();
                } catch (RecordAlreadyExistsException e) {
                    log.info("Log: " + e.MESSAGE);
                    e.printStackTrace();
                }
            } else {
                try {
                    manager.changeCategoryForProduct(Integer.parseInt(editableProductId), chkCat.getName());
                } catch (NoSuchRecordException e) {
                    log.info("Log: " + e.MESSAGE);
                    e.printStackTrace();
                } catch (RecordAlreadyExistsException e) {
                    log.info("Log: " + e.MESSAGE);
                    e.printStackTrace();
                }
            }

        }
    }

    private void addProduct(HttpServletRequest req, HttpServletResponse resp, String[] params ) throws IOException, ServletException {
        Product checkProd = manager.findProductByName(params[0]);
        if (checkProd != null) {
            req.setAttribute("prodAddResult", "Such product already exists.");
            req.getRequestDispatcher("admin.jsp").forward(req, resp);
        } else {
            Product newProd = new Product(params[0], params[1], new BigDecimal(params[3]), Integer.valueOf(params[2]), Integer.valueOf(params[4]), params[5]);
            manager.addProduct(newProd);
            changeCatForProd(newProd, params[6]);
            req.setAttribute("prodAddResult", "Product added.");
            resp.sendRedirect("/Start.do");
        }
    }

    private void changeCatForProd(Product newProd, String pCat) {
        Category newC = null;
        Category chkCat = manager.findCategoryByName(pCat);
        if (chkCat == null) {
            manager.addCategory(new Category(pCat));
            newC = manager.findCategoryByName(pCat);
        } else {
            newC = chkCat;
        }
        try {
            manager.changeCategoryForProduct(newProd.getProductId(), newC.getName());
        } catch (NoSuchRecordException e) {
            log.info("Log: "+e.MESSAGE);
        } catch (RecordAlreadyExistsException e) {
            e.printStackTrace();
        }
    }

    private void addCategory(HttpServletRequest req, HttpServletResponse resp, String cName) throws IOException {
        Category checkCat = manager.findCategoryByName(cName);
        if (checkCat != null) {
            req.setAttribute("catAddResult", "Such category already exists.");
        } else {
            manager.addCategory(new Category(cName));
            req.setAttribute("catAddResult", "Category added.");
        }
        resp.sendRedirect("/Start.do");
    }

    private void deleteCategoryCompletely(HttpServletRequest req, HttpServletResponse resp, String delCatName) throws IOException {
        Category categoryForDel =  manager.findCategoryByName(delCatName);
        if (categoryForDel != null) {
            manager.deleteCategoryWithAllProducts(categoryForDel);
            req.setAttribute("catDelResult", "Category deleted.");
        } else {
            req.setAttribute("catDelResult", "Can't delete non-existing category.");
        }
        resp.sendRedirect("/Start.do");
    }

    private void deleteCategoryLeaveProducts(HttpServletRequest req, HttpServletResponse resp, String delLeaveCatName) throws IOException {
        Category categoryForSoftDel = manager.findCategoryByName(delLeaveCatName);
        if (categoryForSoftDel != null) {
            manager.deleteCategoryButLeaveProducts(categoryForSoftDel);
            req.setAttribute("catDelResult", "Category deleted but not its products.");
        } else {
            req.setAttribute("catDelResult", "Can't delete non-existing category.");
        }
        resp.sendRedirect("/Start.do");
    }

    private void choseCategoryForEditingProducts (HttpServletRequest req, HttpServletResponse resp, String edtProdsOfCatName) throws IOException, ServletException {
        Category categoryForEditingProds = manager.findCategoryByName(edtProdsOfCatName);
        if (categoryForEditingProds != null) {
            req.setAttribute("categoryForEditingProds", categoryForEditingProds);
            req.getRequestDispatcher("admin.jsp").forward(req, resp);
        }
    }

    private void choseProductForEdit (HttpServletRequest req, HttpServletResponse resp, String prodForEdtName) throws IOException, ServletException {
        Product edtblPrdct = manager.findProductByName(prodForEdtName);
        if (edtblPrdct != null) {
            req.setAttribute("editableProduct", edtblPrdct);
        }
        req.getRequestDispatcher("admin.jsp").forward(req, resp);
    }

    private void deleteProduct (HttpServletRequest req, HttpServletResponse resp, String delProdName) throws IOException, ServletException {
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
