<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>


<html>
<%@ include file="head.jsp" %>

<body>
<%@ include file="header.jsp" %>

<section>
    <div class="container">
        <div class="row">
            <div class="col-sm-4">
                <div class="left-sidebar">
                    <h2>Categories</h2>
                    <c:forEach items="${applicationScope.categories}" var="category">
                        <div class="brands-name">
                            <ul class="nav nav-pills nav-stacked">
                                <li> <table class="table table-hover ">
                                    <tr>
                                        <td colspan="2">
                                            <a href="${root}/Admin.do?choice=chooseCatForEdtPrds&edtProdsOfCatName=${category.name}"><c:out value="${category.name}"/></a>
                                        </td>
                                    </tr>
                                    <tr>
                                    <td>
                                        <a class="btn btn-default btn-sm" href="${root}/Admin.do?choice=delCatFull&delCatName=${category.name}">
                                            Hard Delete</a>
                                    </td>
                                        <td>
                                            <a class="btn btn-default btn-sm" href="${root}/Admin.do?choice=delCatSoft&delLeaveCatName=${category.name}">
                                                Soft Delete</a>
                                        </td>
                                    </tr>
                                </table>
                                </li>
                            </ul>
                        </div>
                    </c:forEach>
                    <br/>

                    <div class="brands_products">
                        <h2>Edit Products</h2>
                        <div class="brands-name">
                            <ul class="nav nav-pills nav-stacked">
                                <c:forEach items="${requestScope.categoryForEditingProds.products}" var="prodForEdt">
                                    <li><table class="table table-hover ">
                                        <tr>
                                            <td>
                                                <a class="btn btn-default btn-sm" href="${root}/Admin.do?choice=edtProd&prodForEdtName=${prodForEdt.name}">Edit "${prodForEdt.name}"</a>
                                            </td>
                                            <td>
                                                <a class="btn btn-default btn-sm" href="${root}/Admin.do?choice=delProd&delProdName=${prodForEdt.name}">Delete</a>
                                            </td>
                                        </tr>
                                    </table>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                    <br/>

                </div>
            </div>

            <div class="col-sm-6 padding-right">
                <div style="text-align: center">

                    <form method="post" action="${root}/Admin.do?choice=editP&editableProductId=${requestScope.editableProduct.productId}&oldCatName=${requestScope.editableProduct.category}">
                        <div style="text-align: center">
                            <table class="table table-hover">
                                <thead>
                                <tr>
                                    <th colspan="2">Edit product ${requestScope.editableProduct.name}</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>New product name</td>
                                    <td><label>
                                        <input type="text" name="pName" value="${requestScope.editableProduct.name}"/>
                                    </label></td>
                                </tr>
                                <tr>
                                    <td>New product description</td>
                                    <td><label>
                                        <input type="text" name="pDesc" value="${requestScope.editableProduct.description}"/>
                                    </label></td>
                                </tr>
                                <tr>
                                    <td>Change category</td>
                                    <td><label>
                                        <input type="text" name="pCat" value="${requestScope.editableProduct.category}"/>
                                    </label></td>
                                </tr>
                                <tr>
                                    <td>New amount value</td>
                                    <td><label>
                                        <input type="number" name="pAmount" value="${requestScope.editableProduct.amount}"/>
                                    </label></td>
                                </tr>
                                <tr>
                                    <td>New price</td>
                                    <td><label>
                                        <input type="text" name="pPrice" pattern="^\d+(\.|\,)\d{2}$" value="${requestScope.editableProduct.price}"/>
                                    </label></td>
                                </tr>
                                <tr>
                                    <td>Change year of production</td>
                                    <td><label>
                                        <input type="number" name="pYear" value="${requestScope.editableProduct.year}"/>
                                    </label></td>
                                </tr>
                                <tr>
                                    <td>Change photo</td>
                                    <td><label>
                                        <input type="text" name="pPhoto" value="${requestScope.editableProduct.photo}"/>
                                    </label></td>
                                </tr>
                                <tr>
                                    <td><input type="submit" value="Submit" /></td>
                                    <td><input type="reset" value="Reset" /></td>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </form>

                    <br/>

                    <form method="post" action="${root}/Admin.do?choice=addCat">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th colspan="2">Add new category</th>
                            </tr>
                            </thead>
                            <tr>
                                <td>Category name</td>
                                <td><label>
                                    <input type="text" name="cName" value=""/>
                                </label></td>
                            </tr>
                            <tr>
                                <td><input type="submit" value="Submit" /></td>
                                <td><input type="reset" value="Reset" /></td>
                            </tr>
                        </table>
                        </form>
                    </div>

                <br/>

                <form method="post" action="${root}/Admin.do?choice=addProd">
                    <div style="text-align: center">
                        <table class="table table-hover">
                            <thead>
                            <tr>
                                <th colspan="2">Add new product</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>Product name</td>
                                <td><label>
                                    <input type="text" name="pName" value=""/>
                                </label></td>
                            </tr>
                            <tr>
                                <td>Product description</td>
                                <td><label>
                                    <input type="text" name="pDesc" value=""/>
                                </label></td>
                            </tr>
                            <tr>
                                <td>Category</td>
                                <td><label>
                                    <input type="text" name="pCat" value=""/>
                                </label></td>
                            </tr>
                            <tr>
                            <tr>
                                <td>Amount</td>
                                <td><label>
                                    <input type="number" name="pAmount" value=""/>
                                </label></td>
                            </tr>
                            <tr>
                                <td>Price</td>
                                <td><label>
                                    <input type="number"  pattern="^\d+(\.|\,)\d{2}$" name="pPrice" value=""/>
                                </label></td>
                            </tr>
                            <tr>
                                <td>Year of production</td>
                                <td><label>
                                    <input type="number" name="pYear" value=""/>
                                </label></td>
                            </tr>
                            <tr>
                                <td>Photo</td>
                                <td><label>
                                    <input type="text" name="pPhoto" value=""/>
                                </label></td>
                            </tr>
                            <tr>
                                <td><input type="submit" value="Submit" /></td>
                                <td><input type="reset" value="Reset" /></td>
                            </tr>
                            </tbody>
                        </table>
                    </div>
                </form>

                <br/>




            </div>
        </div>
    </div>
</section>

<%@ include file="footer.jsp" %>


<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>