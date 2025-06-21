<%@page import="com.onlinePharmacySystem.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
     User user = (User)session.getAttribute("User");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
     <style type="text/css">
     body {
    margin: 0;
    padding: 0;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background-color: #f4f6f9;
}

.dashboard-container {
    max-width: 1200px;
    margin: 60px auto;
    background-color: #ffffff;
    padding: 40px;
    border-radius: 12px;
    box-shadow: 0 10px 25px rgba(0, 0, 0, 0.1);
}

h1 {
    color: #2c3e50;
    text-align: center;
    margin-bottom: 30px;
}

.dashboard-links {
    display: flex;
    justify-content: center;
    gap: 20px;
    margin-bottom: 30px;
}

.dashboard-links a {
    padding: 12px 20px;
    background-color: #2980b9;
    color: #fff;
    text-decoration: none;
    border-radius: 8px;
    font-size: 16px;
    transition: background-color 0.3s ease, transform 0.2s ease;
}

.dashboard-links a:hover {
    background-color: #1f618d;
    transform: translateY(-2px);
}

table {
    width: 100%;
    border-collapse: collapse;
    background-color: #ecf0f1;
    border-radius: 8px;
    overflow: hidden;
}

th, td {
    padding: 12px 16px;
    text-align: center;
    border-bottom: 1px solid #bdc3c7;
}

th {
    background-color: #2c3e50;
    color: #ffffff;
    text-transform: uppercase;
}

tr:nth-child(even) {
    background-color: #f2f2f2;
}

tr:hover {
    background-color: #d6eaf8;
    cursor: pointer;
}

img {
    width: 80px;
    height: auto;
    border-radius: 6px;
    box-shadow: 0 0 5px rgba(0,0,0,0.1);
}

td a {
    color: #2980b9;
    text-decoration: none;
    font-weight: bold;
}

td a:hover {
    color: #c0392b;
}
     
     </style>
    <link rel="stylesheet" type="text/css" href="../../css/Admin.css">
</head>
<body>
    <div class="dashboard-container">
      <% 
          if (user==null){
        	  %>
             <h1> You are not log in </h1>
          <% return; }%>
      
         <h1>👨‍⚕️ Welcome <%=user.getFULL_NAME() %></h1>
        <h2>👨‍⚕️ Admin Dashboard</h1>

        <div class="dashboard-links">
            <a href="<%= request.getContextPath() %>/ManageUsersServlet">👤 Manage Users</a>
            <a  href="<%= request.getContextPath() %>/ManageProductsServlet">💊 Manage Products</a>
            <a href="manage-orders.jsp">📦 Manage Orders</a>
            
        </div>
    </div>
</body>
</html>
