<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<html>
<%@ include file="/resources/templates/head.jsp" %>
<body>

<div id="container">
    <%@ include file="/resources/templates/menu.jsp" %>
    
    <div id="content">

        <div id="content_left">
        	<div class="content_left_section">
            	<h2>Categories</h2>
                    <c:forEach items="${applicationScope.categories}" var="category">
                <form method="post" action="${root}/Admin.do">
                    <table><tr>
                        <td><a href="${root}/Admin.do?edtProdsOfCatName=${category.name}"><c:out value="${category.name}"/></a></td>
                        <tr><td><div class="del_button"><a href="${root}/Admin.do?delCatName=${category.name}">Delete category and products</a></div></td>
                        <tr><td><div class="del_button"><a href="${root}/Admin.do?delLeaveCatName=${category.name}">Delete only category</a></div></td>
                    </tr></table>
                </form>
                    </c:forEach>
            </div>

            <div class="content_left_section">
                <h2>Edit Products</h2>
                <c:forEach items="${requestScope.categoryForEditingProds.products}" var="prodForEdt">
                <form method="post" action="${root}/Admin.do">
                    <table><tr>
                        <a href="${root}/Admin.do?prodForEdtName=${prodForEdt.name}"><c:out value="${prodForEdt.name}"/></a>
                        <tr><td><div class="del_button"><a href="${root}/Admin.do?delProdName=${prodForEdt.name}">Delete product</a></div></td>
                    </tr></table>
                </form>

                </c:forEach>
            </div>
        </div>

        <div id="content_right">

            <form method="post" action="${root}/Admin.do">
                <div style="text-align: center">
                    <table border="1" width="30%" cellpadding="5">
                        <thead>
                        <tr>
                            <th colspan="2">Add new category</th>
                        </tr>
                        </thead>
                        <tbody>
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
                        <%--<tr>--%>
                            <%--<td colspan="2"><c:if test="${requestScope.catAddResult ne null}">--%>
                                <%--<c:out value="${requestScope.catAddResult}"> </c:out></c:if></td>--%>
                        <%--</tr>--%>
                        </tbody>
                    </table>
                </div>
            </form>

            <form method="post" action="${root}/Admin.do">
                <div style="text-align: center">
                    <table border="1" width="30%" cellpadding="5">
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
                                <input type="number" name="pPrice" value=""/>
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
                        <%--<tr>--%>
                            <%--<td colspan="2"><c:if test="${requestScope.prodAddResult ne null}">--%>
                                <%--<c:out value="${requestScope.prodAddResult}"> </c:out></c:if></td>--%>
                        <%--</tr>--%>
                        </tbody>
                    </table>
                 </div>
            </form>

            <form method="post" action="${root}/ProductEdit.do?editableProductId=${requestScope.editableProduct.productId}&oldCatName=${requestScope.editableProduct.category}">
                <div style="text-align: center">
                    <table border="1" width="30%" cellpadding="5">
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
                                <input type="number" name="pPrice" value="${requestScope.editableProduct.price}"/>
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
                        <%--<tr>--%>
                        <%--<td colspan="2"><c:if test="${requestScope.prodUpdResult ne null}">--%>
                        <%--<c:out value="${requestScope.prodUpdResult}"> </c:out></c:if></td>--%>
                        <%--</tr>--%>
                        </tbody>
                    </table>
                </div>
            </form>

    </div>

    <%@ include file="/resources/templates/footer.jsp" %>


</div>

</body>
</html>