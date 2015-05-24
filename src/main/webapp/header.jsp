<header id="header">
    <div class="header_top">
        <div class="container">
            <div class="row">
                <div class="col-sm-6">
                    <div class="contactinfo">
                        <ul class="nav nav-pills">
                            <li><a href="#"><i class="fa fa-phone"></i> +38 067 408 71 42</a></li>
                            <li><a href="#"><i class="fa fa-envelope"></i> anton.komarovskyi@gmail.com</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="header-middle">
        <div class="container">
            <div class="row">
                <div class="col-sm-4">
                    <div class="logo pull-left">
                        <a href="home.jsp"><img src="images/home/logo.png" alt=""/></a>
                    </div>
                </div>
                <div class="col-sm-8">
                    <div class="shop-menu pull-right">
                        <ul class="nav navbar-nav">
                            <li><c:if test="${sessionScope.currentCustomer.loginName eq 'admin'}">
                                <a href="admin.jsp"><i class="fa fa-crosshairs"></i> Admin</a></c:if></li>
                            <li><a href="<c:url value="/Cart.do"/>"><i class="fa fa-shopping-cart"></i> Cart</a></li>
                            <li><c:if test="${sessionScope.currentCustomer ne null}">
                                <a href="/Logout.do"> <i class="fa fa-crosshairs"></i> Logout</a></c:if></li>
                            <li><c:if test="${sessionScope.currentCustomer eq null}">
                                <a href="login.jsp"><i class="fa fa-lock"></i>Login</a></c:if></li>
                            <li><c:if test="${sessionScope.currentCustomer eq null}">
                                <a href="registration.jsp"><i class="fa fa-lock"></i>Register</a></c:if></li>
                            <li><c:if test="${sessionScope.currentCustomer ne null}">
                                <c:out value="Welcome, ${sessionScope.currentCustomer.firstName}"> </c:out></c:if></li>

                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="header-bottom">
        <div class="container">
            <div class="row">
                <div class="col-sm-9">
                    <div class="mainmenu pull-left">
                        <ul class="nav navbar-nav collapse navbar-collapse">
                            <li><a href="home.jsp">Home<i class="fa"></i></a></li>
                            <li><a href="contacts.jsp">Contact</a></li>
                        </ul>
                    </div>
                </div>
                <div class="col-sm-3">
                    <div class="search_box pull-right">
                        <form method="get" action="${root}/Search.do">
                            <input type="text" name="searchProduct" placeholder="Search product" value=""/>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>