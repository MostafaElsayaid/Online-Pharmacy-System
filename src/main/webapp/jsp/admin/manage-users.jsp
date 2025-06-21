<%@page import="com.onlinePharmacySystem.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Users</title>
</head>
<body><%@page import="com.onlinePharmacySystem.model.Product"%>
<%@ page import="java.util.List" %>

    <%
    List<User> Users = (List<User>)session.getAttribute("Users");
    System.out.println(Users);
    %>
    

<%
    String message = (String) session.getAttribute("message");
    if (message != null) {
%>
    <p style="color: green;"><%= message %></p>
<%
        session.removeAttribute("message"); // 
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Manage Users</title>
<link rel="stylesheet" href="<%= request.getContextPath() %>/ManageProducts.css">
</head>
<body>
   
    <div class="container">
        <h1>👤 Manage Users</h1>

        <div class="links">
            <a href="<%=request.getContextPath()%>/jsp/admin/add-product.jsp">➕ Add New User</a>
            <a href="/OnlinePharmacySystem/jsp/admin/dashboard.jsp">⬅️ Back to Dashboard</a>
        </div>

        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Password</th>
                <th>Role</th>
                <th>Date</th>
                <th>Actions</th>
            </tr>
            <%
                for(User user : Users) {
            %>
            <tr>
                <td><%= user.getUSER_ID()%></td>
                <td><%= user.getFULL_NAME()%></td>
                <td><%= user.getEMAIL()%> </td>
                <td><%= user.getPASSWORD()%></td>
                <td><%= user.getUSER_ROLE()%></td>
                <td><%=user.getCREATED_AT() %></td>
                <td>
                    <a href="<%=request.getContextPath() %>/ManageProductsServlet?action=load-product&id=<%=user.getUSER_ID()%>">✏️ Edit</a> |
                    <a href="<%=request.getContextPath() %>/ManageProductsServlet?action=remove-product&id=<%=user.getUSER_ID()%>">❌ Delete</a> |
                    
                 <a href="<%=request.getContextPath()%>/jsp/admin/add-description.jsp?id=<%=user.getUSER_ID()%>">✏️ Edit Role</a>
                
                </td>
            </tr>
            <% } %>
        </table>
    </div>
</body>
</html>

 <h1>Manage users</h1>
</body>
</html>