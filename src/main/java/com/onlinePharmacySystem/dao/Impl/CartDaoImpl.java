package com.onlinePharmacySystem.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.onlinePharmacySystem.dao.CartDao;
import com.onlinePharmacySystem.model.CartDetails;
import com.onlinePharmacySystem.model.CartItem;

public class CartDaoImpl implements CartDao{
	DataSource dataSource ;

	public CartDaoImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public boolean addCart(CartItem cart) {
		
		try {
			Connection con = dataSource.getConnection();
			String query = "INSERT INTO CART_ITEM (CUSTOMER_ID,PRODUCT_ID) VALUES (?,?)";
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, cart.getUserId());
			st.setInt(2, cart.getProductId());
			int row = st.executeUpdate();
			if (row == 1) {
				System.out.println("add cart success");
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
		
	}

	@Override
	public List<CartItem> getCarts(int userId) {
		
		List<CartItem> allCarts = new ArrayList<CartItem>();
		try {
			
			Connection con = dataSource.getConnection();
			String query = "SELECT * FROM CART_ITEM WHERE CUSTOMER_ID = ?";
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, userId);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				CartItem cart = new CartItem();
				cart.setCardId(rs.getInt("CART_ITEM_ID"));
				cart.setProductId(rs.getInt("PRODUCT_ID"));
				cart.setUserId(rs.getInt("CUSTOMER_ID"));
				allCarts.add(cart);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return allCarts;
		
		
	}

	@Override
	public List<CartDetails> cartDetails(int userId) {
		
 		List<CartDetails> cartDetails = new ArrayList<CartDetails>();
		try {
			Connection con = dataSource.getConnection();
			String query = "SELECT  ci.CART_ITEM_ID , p.NAME , p.PRICE , ci.QUANTITY FROM CART_ITEM ci \r\n"
					+ "JOIN PRODUCTS p ON ci.PRODUCT_ID = p.PRODUCT_ID \r\n"
					+ "WHERE ci.CUSTOMER_ID = ?";
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, userId);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				CartDetails cart = new CartDetails();
				cart.setProductName(rs.getString("NAME"));
				cart.setPrice(rs.getDouble("PRICE"));
				cart.setCartItemId(rs.getInt("CART_ITEM_ID"));
				cart.setQuantity(rs.getInt("QUANTITY"));
				cartDetails.add(cart);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cartDetails;
		
	}

}
