	<div id="menu">
    	<ul>
            <li><a href="home.jsp" class="current">Home</a></li>
            <li><a href="search.jsp">Search</a></li>
            <li><a href="#">Cart</a></li>
            <li><a href="contact.jsp">Contact</a></li>
            <li><c:if test="${sessionScope.currentCustomer eq null}">
                <a href="registration.jsp">Register</a></c:if></li>
            <li><c:if test="${sessionScope.currentCustomer eq null}">
                <a href="login.jsp">Login</a></c:if></li>
            <li><c:if test="${sessionScope.currentCustomer ne null}">
                <a href="/Logout.do">Logout</a></c:if></li>
            <li><p><c:if test="${sessionScope.currentCustomer ne null}">
                <c:out value="Welcome, ${sessionScope.currentCustomer.firstName}"> </c:out></c:if></p></li>
            <li><p><c:if test="${sessionScope.logResult ne null}">
                <c:out value="${sessionScope.logResult}"> </c:out></c:if></p></li>
            <li><p><c:if test="${sessionScope.currentCustomer.loginName eq 'admin'}">
                <a href="admin.jsp">Admin</a></c:if></li>
        </ul>
    </div>
    
    <div id="header">
    </div>
