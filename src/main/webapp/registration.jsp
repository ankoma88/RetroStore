<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>


<html>
<%@ include file="head.jsp" %>

<body>
<%@ include file="header.jsp" %>

<section id="form">


<h2>Registration</h2>
<form method="post" action="${root}/Register.do">
    <div style="text-align: center">
        <table class="table table-hover">
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
            </tbody>
        </table>
    </div>
</form>

</section>

<%@ include file="footer.jsp" %>



<script src="js/jquery.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>

