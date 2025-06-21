package com.onlinePharmacySystem.dao;

import java.sql.SQLException;
import java.util.List;

import com.onlinePharmacySystem.model.Product;

public interface ProductDao {
	List<Product> loadProducts() throws SQLException;
	Product loadProduct(int id);
	boolean removeProduct(int id) ;
	boolean addProduct(Product product) throws SQLException;
	boolean updateProduct(Product product);
	boolean addDescription(int id,String description) throws SQLException;
	

}
