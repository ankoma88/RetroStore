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
                   <li><a href="${root}/Showcase.do?catName=${category.name}"><c:out value="${category.name}"/></a></li>
                    </c:forEach>
            </div>

            <div class="content_left_section">
                <h2>New Releases</h2>
                <c:forEach items="${applicationScope.newProducts}" var="newProduct">
                    <li><a href="${root}/Showcase.do?prodName=${newProduct.name}"><c:out value="${newProduct.name}"/></a></li>
                </c:forEach>
            </div>
        </div>

        <div id="content_right">

            <c:choose>
                <c:when test="${requestScope.choice eq 'product' and requestScope.chosenProduct ne null}">
                    <div class="product_box">
                        <h1>${requestScope.chosenProduct.name}<span>(${requestScope.chosenProduct.category.name})</span></h1>
                        <img src="${root}/resources/images/products/sample_product.jpg"/>
                        <div class="product_info">
                            <p>${requestScope.chosenProduct.description}</p>
                            <h3>$Price</h3>
                            <div class="buy_now_button"><a href="#">Buy Now</a></div>
                        </div>
                        <div class="cleaner">&nbsp;</div>
                    </div>
                    <div class="cleaner_with_height">&nbsp;</div>
                </c:when>
                <c:when test="${requestScope.choice eq 'category'}">
                    <c:forEach items="${requestScope.chosenCategory.products}" var="productOfCat">

                   <div class="product_box">
                            <h1>${productOfCat.name}<span>(${requestScope.chosenCategory})</span></h1>
                            <img src="${root}/resources/images/products/sample_product.jpg"/>
                            <div class="product_info">
                                <p>${productOfCat.description}</p>
                                <h3>USD ${productOfCat.price}</h3>
                                <div class="buy_now_button"><a href="#">Buy Now</a></div>
                            </div>
                            <div class="cleaner">&nbsp;</div>
                        </div>
                        <div class="cleaner_with_height">&nbsp;</div>

                    </c:forEach>
                </c:when>
                <c:otherwise>
                        <c:forEach items="${applicationScope.newProducts}" var="newProduct">
                            <div class="product_box">
                                <h1>${newProduct.name}<span>(${newProduct.category})</span></h1>
                                <img src="${root}/resources/images/products/sample_product.jpg"/>
                                <div class="product_info">
                                    <p>${newProduct.description}</p>
                                    <h3>USD ${newProduct.price}</h3>
                                    <div class="buy_now_button"><a href="#">Buy Now</a></div>
                                </div>
                                <div class="cleaner">&nbsp;</div>
                            </div>
                            <div class="cleaner_with_height">&nbsp;</div>
                        </c:forEach>
                </c:otherwise>
            </c:choose>

        </div>

    </div>

    <%@ include file="/resources/templates/footer.jsp" %>


</div>

</body>
</html>