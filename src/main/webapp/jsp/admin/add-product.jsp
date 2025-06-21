<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Product</title>
    <link rel="stylesheet" type="text/css" href="../../css/addProduct.css"> 
</head>
<body>
    <div class="container">
        <h1>➕ Add New Product</h1>

        <form action="<%= request.getContextPath() %>/ManageProductsServlet" method="post" enctype="multipart/form-data">
            <input type="hidden" required name="action" value="add-product">
            <div class="form-group">
    
                <label for="name">Product Name:</label>
                <input type="text" id="name" name="name" required>
            </div>
             <div class="form-group">
            
                <label for="price">Product Price:</label>
                <input type="number" id="price" name="price" required>
            </div>

            <div class="form-group">
                <label for="description">Description:</label>
                <textarea id="description" name="description" rows="3"></textarea>
            </div>

            <div class="form-group">
                <label for="quantity">Stock Quantity:</label>
                <input type="number" id="quantity" name="quantity" required>
            </div>

            <div class="form-group">
                <label for="image">Product Image:</label>
<input type="file" id="image" name="image" accept="image/*">
            </div>
             
            <input type="submit" value="Add" class="button">
              </form>
    </div>
</body>
</html>
