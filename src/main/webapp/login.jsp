<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="root" value="${pageContext.request.contextPath}"/>


<html>
<%@ include file="head.jsp" %>

<body>
<%@ include file="header.jsp" %>

<section id="form">
                    <h2>Login to your account</h2>
                    <form method="post" action="${root}/Login.do">
                        <div style="text-align: center">
                            <table class="table table-hover">
                                <tbody>
                                <tr>
                                    <td>Enter name</td>
                                    <td><label>
                                        <input type="text" name="login" value=""/>
                                    </label></td>
                                </tr>
                                <tr>
                                    <td>Enter password</td>
                                    <td><label>
                                        <input type="password" name="pass" value=""/>
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

