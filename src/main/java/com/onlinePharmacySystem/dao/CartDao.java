package com.onlinePharmacySystem.dao;

import java.util.List;

import com.onlinePharmacySystem.model.CartDetails;
import com.onlinePharmacySystem.model.CartItem;

public interface CartDao {
	boolean addCart(CartItem cart);
	List<CartItem> getCarts (int userId);
    List<CartDetails> cartDetails(int userId);
}
