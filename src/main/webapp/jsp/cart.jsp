<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@page import="com.onlinePharmacySystem.model.CartDetails"%>

<html>
<head>
    <title>Your Cart</title>
    <style>
        body { font-family: Arial; }
        table { width: 80%; margin: auto; border-collapse: collapse; }
        th, td { padding: 12px; border: 1px solid #ccc; text-align: center; }
        button { padding: 5px 10px; font-size: 16px; }
        input[type=number] { width: 50px; text-align: center; font-size: 16px; }
    </style>
    <script>
        function increaseQuantity(id) {
            let input = document.getElementById('qty-' + id);
            input.value = parseInt(input.value) + 1;
            updateTotal(id);
        }

        function decreaseQuantity(id) {
            let input = document.getElementById('qty-' + id);
            if (parseInt(input.value) > 1) {
                input.value = parseInt(input.value) - 1;
                updateTotal(id);
            }
        }

        function updateTotal(id) {
            let qty = parseInt(document.getElementById('qty-' + id).value);
            let price = parseFloat(document.getElementById('price-' + id).innerText);
            document.getElementById('total-' + id).innerText = (qty * price).toFixed(2);
        }
    </script>
</head>
<body>
<h2 style="text-align:center;">🛒 Your Cart</h2>

<form action="<%= request.getContextPath() %>/AddToCartServlet">
        <input type="hidden" name="action" value="updateCart">
    <table>
        <tr>
            <th>Product</th>
            <th>Price</th>
            <th>Quantity</th>
            <th>Total</th>
        </tr>

        <%  List<CartDetails> cartItems = (List<CartDetails>) session.getAttribute("cartDetails");
            double grandTotal = 0;
            for (CartDetails item : cartItems) {
                int id = item.getCartItemId();
                double itemTotal = item.getQuantity() * item.getPrice();
                grandTotal += itemTotal;
        %>
        <tr>
            <td><%= item.getProductName() %></td>
            <td id="price-<%= id %>"><%= item.getPrice() %></td>
            <td>
                <button type="button" onclick="decreaseQuantity(<%= id %>)">-</button>
                <input type="number" name="quantity-<%= id %>" id="qty-<%= id %>" value="<%= item.getQuantity() %>" min="1" readonly>
                <button type="button" onclick="increaseQuantity(<%= id %>)">+</button>
            </td>
            <td>$<span id="total-<%= id %>"><%= itemTotal %></span></td>
        </tr>
        <% } %>
        <tr>
            <td colspan="3" style="text-align:right;"><strong>Grand Total:</strong></td>
            <td>$<strong><%= grandTotal %></strong></td>
        </tr>
    </table>

    <div style="text-align:center; margin-top: 20px;">
        <button type="submit">✅ تحديث السلة</button>
    </div>
</form>
</body>
</html>
