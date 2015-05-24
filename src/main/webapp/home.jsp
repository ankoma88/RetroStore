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
            <div class="col-sm-3">
                <div class="left-sidebar">
                    <h2>Categories</h2>
                    <c:forEach items="${applicationScope.categories}" var="category">
                        <div class="brands-name">
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="${root}/Showcase.do?choice=category&catName=${category.name}"><c:out value="${category.name}"/></a></li>
                            </ul>
                        </div>
                    </c:forEach>
                    <br/>

                    <div class="brands_products">
                        <h2>New Products</h2>

                        <div class="brands-name">
                            <ul class="nav nav-pills nav-stacked">
                                <c:forEach items="${applicationScope.newProducts}" var="newProduct">
                                    <li><a href="${root}/Showcase.do?choice=product&prodName=${newProduct.name}"><c:out value="${newProduct.name}"/></a></li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                    <br/>

                </div>
            </div>

            <div class="col-sm-9 padding-right">
                <c:choose>
                    <c:when test="${requestScope.choice eq 'product' and requestScope.chosenProduct ne null}">
                    <table class="table table-hover ">
                        <tr>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Amount</th>
                            <th>Year</th>
                            <th>Photo</th>
                            <th>Buy</th>
                        </tr>
                        <tr>
                            <td>
                                <p>${requestScope.chosenProduct.name}</p>
                            </td>
                            <td>
                                <p>${requestScope.chosenProduct.description}</p>
                            </td>
                            <td>
                                <p>$${requestScope.chosenProduct.price}</p>
                            </td>
                            <td>
                                <p>${requestScope.chosenProduct.amount}</p>
                            </td>
                            <td>
                                <p>${requestScope.chosenProduct.year}</p>
                            </td>
                            <td>
                                <img src="${requestScope.chosenProduct.photo}" alt=""/>
                            </td>
                            <td>
                                <a href="#" class="btn btn-default add-to-cart"><i
                                        class="fa fa-shopping-cart"></i>Add to cart</a>
                            </td>
                        </tr>
                    </table>
                    </c:when>
                    <c:when test="${requestScope.choice eq 'category'}">
                        <table class="table table-hover ">
                            <tr>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Price</th>
                                <th>Amount</th>
                                <th>Year</th>
                                <th>Photo</th>
                                <th>Buy</th>
                            </tr>
                            <c:forEach

                                       items="${requestScope.chosenCategory.products}" var="productOfCat">
                                <tr>
                                    <td>
                                        <p>${productOfCat.name}</p>
                                    </td>
                                    <td>
                                        <p>${productOfCat.description}</p>
                                    </td>
                                    <td>
                                        <p>$${productOfCat.price}</p>
                                    </td>
                                    <td>
                                        <p>${productOfCat.amount}</p>
                                    </td>
                                    <td>
                                        <p>${productOfCat.year}</p>
                                    </td>
                                    <td>
                                        <img src="${productOfCat.photo}" alt=""/>
                                    </td>
                                    <td>
                                        <a href="#" class="btn btn-default add-to-cart"><i
                                                class="fa fa-shopping-cart"></i>Add to cart</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <table class="table table-hover ">
                            <tr>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Price</th>
                                <th>Amount</th>
                                <th>Year</th>
                                <th>Photo</th>
                                <th>Buy</th>
                            </tr>
                            <c:forEach items="${applicationScope.newProducts}" var="newProduct"
                                      >
                                <tr>
                                    <td>
                                        <p>${newProduct.name}</p>
                                    </td>
                                    <td>
                                        <p>${newProduct.description}</p>
                                    </td>
                                    <td>
                                        <p>$${newProduct.price}</p>
                                    </td>
                                    <td>
                                        <p>${newProduct.amount}</p>
                                    </td>
                                    <td>
                                        <p>${newProduct.year}</p>
                                    </td>
                                    <td>
                                        <img src="${newProduct.photo}" alt=""/>
                                    </td>
                                    <td>
                                        <a href="#" class="btn btn-default add-to-cart"><i
                                                class="fa fa-shopping-cart"></i>Add to cart</a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</section>

<%@ include file="footer.jsp" %>


<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>