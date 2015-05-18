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

            <form method="post" action="${root}/Register.do">
            <div style="text-align: center">
                <table border="1" width="30%" cellpadding="5">
                    <thead>
                    <tr>
                        <th colspan="2">Registration</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>First Name</td>
                        <td><label>
                            <input type="text" name="firstName" value=""/>
                        </label></td>
                    </tr>
                    <tr>
                        <td>Last Name</td>
                        <td><label>
                            <input type="text" name="lastName" value=""/>
                        </label></td>
                    </tr>
                    <tr>
                        <td>Email</td>
                        <td><label>
                            <input type="text" name="email" value=""/>
                        </label></td>
                    </tr>
                    <tr>
                        <td>Login Name</td>
                        <td><label>
                            <input type="text" name="loginName" value=""/>
                        </label></td>
                    </tr>
                    <tr>
                        <td>Password</td>
                        <td><label>
                            <input type="password" name="password" value=""/>
                        </label></td>
                    </tr>
                    <tr>
                        <td><input type="submit" value="Submit" /></td>
                        <td><input type="reset" value="Reset" /></td>
                    </tr>
                    <tr>
                        <td colspan="2"><c:if test="${requestScope.regResult ne null}">
                            <c:out value="${requestScope.regResult}"> </c:out></c:if></td>
                    </tr>
                    <tr>
                        <td colspan="2">Already registered? <a href="login.jsp">Login</a></td>
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
