<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
     int id = Integer.parseInt(request.getParameter("id"));
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
      <h2>Add Description for Product ID: <%= id %></h2>
     <form action="<%= request.getContextPath() %>/ManageProductsServlet">
        <input type="hidden" name="action" value="add-description">
         <input type="hidden" name="id" value="<%= id %>">
        <label for="description">Description:</label><br>
        <textarea name="description" id="description" rows="5" cols="40" required></textarea><br><br>

        <input type="submit" value="Save Description" /></body>
     
     </form>
    
        
     
</html>