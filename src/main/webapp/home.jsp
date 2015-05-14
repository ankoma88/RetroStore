<%@ page import="com.ak.rstore.model.Category" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Retro Store</title>
<meta name="keywords" content="souvenirs, antiques, art" />
<meta name="description" content="Souvenirs, Antiques and Art Online Shop" />
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet" type="text/css" />
</head>
<body>


<div id="container">
	<div id="menu">
    	<ul>
            <li><a href="home.jsp" class="current">Home</a></li>
            <li><a href="#">Search</a></li>
            <li><a href="#">Categories</a></li>
            <li><a href="#">New Releases</a></li>
            <li><a href="#">Contact</a></li>
            <li><a href="#">Cart</a></li>
            <li><a href="#">Login</a></li>
    	</ul>
    </div> <!-- end of menu -->
    
    <div id="header">
    </div> <!-- end of header -->
    
    <div id="content">
    	
        <div id="content_left">
        	<div class="content_left_section">
            	<h1>Categories</h1>
                <% List<Category> categories = (List<Category>) request.getSession().getAttribute("categories");
                    Iterator it = categories.iterator();
                    while (it.hasNext()) {
                        out.println("<li><a href=\"#\">"+it.next()+"</a></li>");
                    }
                %>
                <ul>
                    <li><a href="#">Some category</a></li>
                    <li><a href="#">Some category</a></li>
                    <li><a href="#">Some category</a></li>
            	</ul>
            </div>
			<div class="content_left_section">
            	<h1>New releases</h1>
                <ul>
                    <li><a href="#">Some new release</a></li>
                    <li><a href="#">Some new release</a></li>
                    <li><a href="#">Some new release</a></li>
            	</ul>
            </div>
        </div> <!-- end of content left -->
        
        <div id="content_right">
        	<div class="product_box">
            	<h1>Product name  <span>(category)</span></h1>
                <ul>
                    <li><img src="<c:url value="/resources/images/products/sample_product.jpg"/>" alt="image" /></li>
                </ul>

                <div class="product_info">
                	<p>Product description</p>
                  <h3>$Price</h3>
                    <div class="buy_now_button"><a href="#">Buy Now</a></div>
                    <div class="detail_button"><a href="#">Detail</a></div>
                </div>
                <div class="cleaner">&nbsp;</div>
            </div>

            <div class="cleaner_with_height">&nbsp;</div>

            <div class="product_box">
                <h1>Product name  <span>(category)</span></h1>
                <ul>
                    <li><img src="<c:url value="/resources/images/products/sample_product.jpg"/>" alt="image" /></li>
                </ul>

                <div class="product_info">
                    <p>Product description</p>
                    <h3>$Price</h3>
                    <div class="buy_now_button"><a href="#">Buy Now</a></div>
                    <div class="detail_button"><a href="#">Detail</a></div>
                </div>
                <div class="cleaner">&nbsp;</div>
            </div>

            <div class="cleaner_with_height">&nbsp;</div>

        </div> <!-- end of content right -->

    </div> <!-- end of content -->
    
    <div id="footer">
        Copyright © 2015 <a href="#"><strong>Retro Store</strong></a>
    </div>
    <!-- end of footer -->

</div> <!-- end of container -->

</body>
</html>