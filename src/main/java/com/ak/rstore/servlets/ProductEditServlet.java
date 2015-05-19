//package com.ak.rstore.servlets;
//
//import com.ak.rstore.exceptions.NoSuchRecordException;
//import com.ak.rstore.exceptions.RecordAlreadyExistsException;
//import com.ak.rstore.manager.ShopManager;
//import com.ak.rstore.model.Category;
//import com.ak.rstore.model.Product;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.math.BigDecimal;
//import java.util.Objects;
//
//@WebServlet("/ProductEdit.do")
//public class ProductEditServlet extends HttpServlet {
//    static final Logger log = LoggerFactory.getLogger(StartServlet.class);
//    private static ShopManager manager = ShopManager.INSTANCE;
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String choice = req.getParameter("choice");
//
//        String pName = req.getParameter("pName");
//        String pDesc = req.getParameter("pDesc");
//        String pAmount = req.getParameter("pAmount");
//        String pPrice = req.getParameter("pPrice");
//        String pYear = req.getParameter("pYear");
//        String pPhoto = req.getParameter("pPhoto");
//        String pCat = req.getParameter("pCat");
//
//        String editableProductId = req.getParameter("editableProductId");
//        String oldCatName = req.getParameter("oldCatName");
//
//        if (pCat == null|| Objects.equals(pCat, "")) pCat = "No category";
//        if (pName == null|| Objects.equals(pName, "")) pName = "No name";
//        if(pDesc==null|| Objects.equals(pDesc, "")) pDesc = "No description";
//        if(pAmount==null|| Objects.equals(pAmount, "")) pAmount = "0";
//        if(pPrice==null|| Objects.equals(pPrice, "")) pPrice = "0";
//        if(pYear==null|| Objects.equals(pYear, "")) pYear = "0";
//        if(pPhoto==null|| Objects.equals(pPhoto, "")) pPhoto = "No photo";
//
//        if(editableProductId==null|| Objects.equals(editableProductId, "")) editableProductId = "0";
//        if(oldCatName==null|| Objects.equals(oldCatName, "")) oldCatName = "No category";
//
//        String[] params = {pName, pDesc, pAmount, pPrice, pYear, pPhoto, pCat};
//
//        switch (choice) {
//            case ("editP"):
//                changeProduct(req, resp, params, editableProductId, oldCatName); break;
//            default: resp.sendRedirect("admin.jsp");
//        }
//
//    }
//
//
//
//    private void changeProduct(HttpServletRequest req, HttpServletResponse resp, String[] params, String editableProductId, String oldCatName) throws ServletException, IOException {
//        Product updProd = manager.findProductById(Integer.parseInt(editableProductId));
//        if (updProd == null) {
//            resp.sendRedirect("admin.jsp");
//            return;
//        }
//
//        if (params[0] != null && !Objects.equals(params[0], updProd.getName())) updProd.setName(params[0]);
//        if (params[1] != null && !Objects.equals(params[1], updProd.getDescription())) updProd.setDescription(params[1]);
//        if (params[2] != null && Integer.valueOf(params[2]) != updProd.getAmount())
//            updProd.setAmount(Integer.valueOf(params[2]));
//        if (params[3] != null && !Objects.equals(new BigDecimal(params[3]), updProd.getPrice()))
//            updProd.setPrice(new BigDecimal(params[3]));
//        if (params[4] != null && Integer.valueOf(params[4]) != updProd.getYear()) updProd.setYear(Integer.valueOf(params[4]));
//
//        //TODO photo
//
//        manager.updateProduct(updProd);
//
//        changeCategoryOfProduct(editableProductId, params[6], oldCatName);
//
//        req.setAttribute("prodEditResult", "Product updated.");
//        resp.sendRedirect("/Start.do");
//    }
//
//    private void changeCategoryOfProduct(String editableProductId, String pCat, String oldCatName) {
//        Category chkCat;
//        if ((pCat != null) && !Objects.equals(pCat, oldCatName)) {
//            chkCat = manager.findCategoryByName(pCat);
//            if (chkCat == null) {
//                chkCat = new Category(pCat);
//                manager.addCategory(chkCat);
//                try {
//                    manager.changeCategoryForProduct(Integer.parseInt(editableProductId), chkCat.getName());
//                } catch (NoSuchRecordException e) {
//                    log.info("Log: " + e.MESSAGE);
//                    e.printStackTrace();
//                } catch (RecordAlreadyExistsException e) {
//                    log.info("Log: " + e.MESSAGE);
//                    e.printStackTrace();
//                }
//            } else {
//                try {
//                    manager.changeCategoryForProduct(Integer.parseInt(editableProductId), chkCat.getName());
//                } catch (NoSuchRecordException e) {
//                    log.info("Log: " + e.MESSAGE);
//                    e.printStackTrace();
//                } catch (RecordAlreadyExistsException e) {
//                    log.info("Log: " + e.MESSAGE);
//                    e.printStackTrace();
//                }
//            }
//
//        }
//    }
//
//}
