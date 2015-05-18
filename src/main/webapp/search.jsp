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

                    <div class="product_box">
                        <div class="product_info">
                            <form method="get" action="${root}/Search.do">
                                <div style="text-align: center">
                                    <table border="1" width="30%" cellpadding="5">
                                        <tbody>
                                        <tr>
                                            <td>Search product:</td>
                                            <td><label>
                                                <input type="text" name="searchProduct" value=""/>
                                            </label></td>
                                        </tr>
                                        <tr>
                                            <td><input type="submit" value="Submit" /></td>
                                        </tr>
                                        <tr>
                                            <td colspan="2"><c:if test="${requestScope.searchResult ne null}">
                                                <c:out value="${requestScope.searchResult}"> </c:out></c:if></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </form>
                        </div>
                        <div class="cleaner">&nbsp;</div>
                    </div>
                    <div class="cleaner_with_height">&nbsp;</div>
        </div>

    </div>

    <%@ include file="/resources/templates/footer.jsp" %>


</div>

</body>
</html>