<%@page import="com.onlinePharmacySystem.model.Product"%>
<%@ page import="java.util.List" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    
    List<Product> Products = (List<Product>)session.getAttribute("Products");
%>
<%
    String message = (String) session.getAttribute("message");
    if (message != null) {
%>
    <p style="color: green;"><%= message %></p>
<%
        session.removeAttribute("message"); // علشان تظهر مرة واحدة بس
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Products</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/ManageProducts.css">
</head>
<body>
   
    <div class="container">
        <h1>💊 Manage Products</h1>

        <div class="links">
            <a href="<%=request.getContextPath()%>/jsp/admin/add-product.jsp">➕ Add New Product</a>
            <a href="/OnlinePharmacySystem/jsp/admin/dashboard.jsp">⬅️ Back to Dashboard</a>
        </div>

        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Description</th>
                <th>Stock</th>
                <th>Image</th>
                <th>Date</th>
                <th>Actions</th>
            </tr>
            <%
                for(Product p : Products) {
            %>
            <tr>
                <td><%= p.getProductId() %></td>
                <td><%= p.getName() %></td>
                <td><%= p.getPrice() %></td>
                <td><%= p.getDescreption() %></td>
                <td><%= p.getQuantity() %></td>
                 <td><img src="<%= p.getImagePath() %>" width="50"></td>
                <td><%=p.getDate() %></td>
                <td>
                    <a href="<%=request.getContextPath() %>/ManageProductsServlet?action=load-product&id=<%=p.getProductId()%>">✏️ Edit</a> |
                    <a href="<%=request.getContextPath() %>/ManageProductsServlet?action=remove-product&id=<%=p.getProductId()%>">❌ Delete</a> |
                    <%
                 if (p.getDescreption() == null) {
                      %>
                 <a href="<%=request.getContextPath()%>/jsp/admin/add-description.jsp?id=<%=p.getProductId()%>">✏️ Add discription</a>
                <%
                 }
                 %>
                </td>
            </tr>
            <% } %>
        </table>
    </div>
</body>
</html>
