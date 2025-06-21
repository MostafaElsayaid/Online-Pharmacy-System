<%@page import="com.onlinePharmacySystem.model.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Product</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/addProduct.css">
</head>
<body>
    <div class="container">
        <h1>✏️ edit Product</h1>
         <%
             Product product = (Product)session.getAttribute("Product");
         %>
        <form action="<%= request.getContextPath() %>/ManageProductsServlet"  enctype="multipart/form-data">
            <input type="hidden" required name="action" value="edit-product">
            <input type="hidden" name="id" value="<%=product.getProductId()%>">
            
            <div class="form-group">
    
                <label for="name">Product Name:</label>
                <input type="text" id="name" name="name" value="<%=product.getName()%>" required>
            </div>
             <div class="form-group">
            
                <label for="price">Product Price:</label>
                <input type="number" id="price" value="<%=product.getPrice()%>" name="price" required>
            </div>

            <div class="form-group">
                <label for="description">Description:</label>
                <textarea id="description" name="description" placeholder="<%=product.getDescreption()%>" rows="3"></textarea>
            </div>

            <div class="form-group">
                <label for="quantity">Stock Quantity:</label>
                <input type="number" id="quantity" value="<%=product.getQuantity()%>" name="quantity" required>
            </div>

            <div class="form-group">
                <label for="image">Product Image:</label>
                <input type="file" id="image" name="image"  accept="image/*">
            </div>
             
            <input type="submit" value="edit" class="button">
              </form>
    </div>
</body>
</html>
