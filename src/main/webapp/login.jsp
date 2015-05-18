<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

            <form method="post" action="${root}/Login.do">
                <div style="text-align: center">
                    <table border="1" width="30%" cellpadding="5">
                        <thead>
                        <tr>
                            <th colspan="2">Login</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>Login Name</td>
                            <td><label>
                                <input type="text" name="login" value=""/>
                            </label></td>
                        </tr>
                        <tr>
                            <td>Password</td>
                            <td><label>
                                <input type="password" name="pass" value=""/>
                            </label></td>
                        </tr>
                        <tr>
                            <td><input type="submit" value="Submit" /></td>
                            <td><input type="reset" value="Reset" /></td>
                        </tr>
                        <tr>
                            <td colspan="2"><c:if test="${requestScope.logResult ne null}">
                                <c:out value="${requestScope.logResult}"> </c:out></c:if></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </form>

        </div>

    </div>

    <%@ include file="/resources/templates/footer.jsp" %>

</div>

</body>
</html>
