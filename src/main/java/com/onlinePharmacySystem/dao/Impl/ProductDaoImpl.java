package com.onlinePharmacySystem.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.onlinePharmacySystem.dao.ProductDao;
import com.onlinePharmacySystem.model.Product;

public class ProductDaoImpl implements ProductDao{
	private DataSource ds ;
	public ProductDaoImpl(DataSource dataSource) {
		this.ds = dataSource;
	}

	@Override
	public List<Product> loadProducts()  {
		List<Product> Products = new ArrayList<Product>();

		try {
			Connection con = ds.getConnection();
			String query = "SELECT * FROM HR.PRODUCTS ORDER BY PRODUCT_ID";
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(query);
			
			
			
			while(rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("PRODUCT_ID"));
				product.setName(rs.getString("NAME"));
				product.setPrice(rs.getDouble("PRICE"));
				product.setImagePath(rs.getString("IMAGE_PATH"));
				product.setDescreption(rs.getString("DESCREPTION"));
				product.setQuantity(rs.getInt("QUANTITY"));
				product.setDate(rs.getDate("CREATED_AT"));
				Products.add(product);
			}
			
			
			
		} catch (SQLException e) {
			System.out.println("Error is in dao impl");
			System.out.println(e.getMessage());
			
		}
		return Products;
	}

	@Override
	public Product loadProduct(int id) {
		try {
			Connection con = ds.getConnection();
			Product product = new Product();
			String query = "SELECT * FROM PRODUCTS WHERE PRODUCT_ID =? ";
			PreparedStatement st = con.prepareStatement(query);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				
				product.setProductId(rs.getInt("PRODUCT_ID"));
				product.setName(rs.getString("NAME"));
				product.setPrice(rs.getDouble("PRICE"));
				product.setImagePath(rs.getString("IMAGE_PATH"));
				product.setDescreption(rs.getString("DESCREPTION"));
				product.setQuantity(rs.getInt("QUANTITY"));
				product.setDate(rs.getDate("CREATED_AT"));
				return product;

				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

	@Override
	public boolean removeProduct(int id)  {
		try {
			
			Connection con = ds.getConnection();
			String query1 = "DELETE FROM CART_ITEM WHERE PRODUCT_ID = ?";
			String query2 = "DELETE FROM PRODUCTS WHERE PRODUCT_ID = ?";
			PreparedStatement st1 = con.prepareStatement(query1);
			PreparedStatement st2 = con.prepareStatement(query2);
			st1.setInt(1, id);
			st2.setInt(1, id);
			int row1 = st1.executeUpdate();
			int row2 = st2.executeUpdate();
			
			System.out.println("error here"+row1+row2);
			if(row1 > 0 || row2 >0) {
				System.out.println("Item deldeted Succecfully");
				return true;
			}
		} catch (Exception e) {
			e.getStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean addProduct(Product product) {
		try {
			 Connection con = ds.getConnection();
			 String query = "INSERT INTO PRODUCTS(NAME ,DESCREPTION ,PRICE ,IMAGE_PATH ,QUANTITY ) VALUES(?,?,?,?,?)";
			 PreparedStatement st = con.prepareStatement(query);
			 st.setString(1, product.getName());
			 st.setString(2, product.getDescreption());
			 st.setDouble(3, product.getPrice());
			 st.setInt(4, product.getQuantity());
			 st.setString(5, product.getImagePath());
			 int row = st.executeUpdate();
			 if(row > 0) {
				 System.out.println("item add succecfuly");
				 return true;
			 }
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		return false;
		
	}

	@Override
	public boolean updateProduct(Product product) {
		 System.out.println(product);
		try {
			 Connection con = ds.getConnection();
			 String query = "UPDATE PRODUCTS SET NAME =?,  DESCREPTION =? , PRICE =? , IMAGE_PATH =? , QUANTITY =? WHERE PRODUCT_ID =?";
			 PreparedStatement st = con.prepareStatement(query);
			 st.setString(1, product.getName());
			 st.setString(2, product.getDescreption());
			 st.setDouble(3, product.getPrice());
			 st.setString(4, product.getImagePath());
			 st.setInt(5, product.getQuantity());
			 
			 st.setInt(6, product.getProductId());
			 int row = st.executeUpdate();
			 if(row > 0) {
				 System.out.println("item updated succecfuly");
				 return true;
			 }
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		return false;
	}

	@Override
	public boolean addDescription(int id, String description) throws SQLException {
		System.out.println("id"+id+"des"+description);
		Connection con = ds.getConnection();
		String query = "UPDATE PRODUCTS SET DESCREPTION = ? WHERE PRODUCT_ID = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, description);
		ps.setInt(2, id);
		int row = ps.executeUpdate();
		if (row > 0) {
			System.out.println("Add Description succesfully");
			return true;
		}else {
			return false;
		}
	}

}
