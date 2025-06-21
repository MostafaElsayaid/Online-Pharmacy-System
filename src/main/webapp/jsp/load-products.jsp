<%@page import="com.onlinePharmacySystem.model.Product"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../../css/product.css">

<title>Insert title here</title>
</head>
<div class="layer">
<form action="<%= request.getContextPath() %>/ManageProductsServlet">
        <input type="hidden" name="action" value="load-products">
        </form>
    <table>
        <h1>Products</h1>
        <thead>
        <tr>
            <th>ID</th>
            <th>NAME</th>
            <th>Description</th>
            <th>Quantity</th>
            <th>Image</th>
            <th>Date</th>
        </tr>
        </thead>
        <tbody>
        <%
         List<Product> Products = (List<Product>)session.getAttribute("Products");
        %>
        <%
        	for(Product product : Products){
        %>
        <tr>
            <td><strong><%=product.getProductId() %></strong></td>
            <td><%=product.getName() %></td>
            <td><%=product.getDescreption() %></td>
            <td><%=product.getQuantity()%></td>
            <td><img alt="<%=product.getName()%>" src="<%=product.getImagePath()%>"></td>
            <td><%=product.getDate() %></td>
        </tr>
        <%
        	}
        %>
        
        </tbody>
    </table>



</div>

</body>
</html>