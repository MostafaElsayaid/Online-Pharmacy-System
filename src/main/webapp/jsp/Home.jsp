<%@page import="com.onlinePharmacySystem.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.onlinePharmacySystem.model.Product" %>
<%
         List<Product> Products = (List<Product>)session.getAttribute("Products");
         if (Products==null){
        	 %>
                 <h3> There is some problem </h3>
                 
              
         <% return;}%>
        
<%
         User user = (User)session.getAttribute("User");
       if (user==null){
	     %>
        <h3> There is some problems pls Login  </h3>
     
<% return;}%>
<%
    Integer cartCount = (Integer) session.getAttribute("cartCount");
    if (cartCount == null) {
        cartCount = 0; // أول مرة يدخل
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home - Online Pharmacy</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <style>
        body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f9f9f9; }
        .navbar { background-color: #2c3e50; padding: 1em; color: white; display: flex; justify-content: space-between; align-items: center; }
        .navbar a { color: white; margin: 0 1em; text-decoration: none; font-weight: bold; }
        .container { padding: 2em; display: flex; flex-wrap: wrap; gap: 1.5em; justify-content: center; }
        .card { background-color: white; border: 1px solid #ddd; border-radius: 10px; width: 250px; padding: 1em; box-shadow: 0 2px 4px rgba(0,0,0,0.1); }
        .card h3 { margin: 0.5em 0; }
        .card p { color: #555; }
        .btn { background-color: #27ae60; color: white; border: none; padding: 0.5em 1em; border-radius: 5px; cursor: pointer; }
        .btn:hover { background-color: #219150; }
         ul {
        list-style: none;
        margin: 0;
        padding: 0;
        display: flex; /* ✅ جنب بعض */
        align-items: center;
        background-color: orange;
        padding: 10px 20px;
        gap: 20px; /* مسافة بين العناصر */
    }

    li {
        display: inline-block;
    }

    a {
        text-decoration: none;
        color: #333;
        font-size: 18px;
        transition: color 0.2s;
    }

    a:hover {
        color: #007bff;
    }

    button {
        position: relative;
        display: inline-block;
        font-size: 26px;
        background: none;
        border: none;
        cursor: pointer;
    }

    button span {
        position: absolute;
        top: -10px;
        right: -10px;
        background-color: red;
        color: white;
        border-radius: 50%;
        padding: 2px 6px;
        font-size: 14px;
    }

    form {
        margin: 0; /* يشيل الفراغ */
    }
    </style>
</head>
<body>

     <form action="<%= request.getContextPath() %>/ManageProductsServlet">
        <input type="hidden" name="action" value="load-products">
         <input type="hidden" value="submit">
        </form>
    <div class="navbar">
        <div><strong>Online Pharmacy</strong></div>
        <div>
             <h3>Welcome <%=user.getFULL_NAME() %></h3>
             <ul>
                  
                  <li>  <form action="<%= request.getContextPath() %>/AddToCartServlet">
             <input type="hidden" name="action" value="showCartDetails">
              <input type="hidden" name="id" value="<%=user.getUSER_ID()%>">
             
             <button type="submit" style="position: relative; display: inline-block; font-size: 30px;">
               🛒
			    <span style="
			        position: absolute;
			        top: -10px;
			        right: -10px;
			        background-color: red;
			        color: white;
			        border-radius: 50%;
			        padding: 2px 7px;
			        font-size: 14px;">
			        <%=cartCount %>
			    </span> 
			</button>
			</form> </li>
			<li> <a href="<%=request.getContextPath()%>/home.jsp">Home</a> </li>
             <li> <a href="<%=request.getContextPath()%>/cart.jsp">Cart</a></li> 
             <li><a href="<%=request.getContextPath()%>/logout">Logout</a></li>
             </ul>
            
			
			
             
			     
            
            
        </div>
    </div>

    <div class="container">
         <%
            if (session.getAttribute("addCart")!=null){
            	String message = (String)session.getAttribute("addCart");
            	%>
                <span style="color:green"><%=message %></span>
            <% session.removeAttribute("addCart");  } %>
         
        <% if (Products != null && !Products.isEmpty()) { 
            for (Product p : Products) { %>
                <div class="card">
                    <h3><%= p.getName()%></h3>
                    <p><%= p.getDescreption() %></p>
                    <p><strong>Price:</strong> $<%= p.getPrice() %></p>
                    <form action="<%=request.getContextPath()%>/AddToCartServlet" method="post">
                        <input type="hidden" name="action" value="add">
                        <input type="hidden" name="productId" value="<%= p.getProductId() %>">
                        <input type="hidden" name="userId" value="<%= user.getUSER_ID() %>">
                        <button class="btn" type="submit">Add to Cart</button>
                    </form>
                </div>
        <%  }} else { %>
            <p>No products available at the moment.</p>
        <% } %>
    </div>
</body>
</html>
