package com.onlinePharmacySystem.model;

public class CartItem {
  int cardId ;
  int productId;
  int userId;
  int quantity;
public int getQuantity() {
	return quantity;
}
public void setQuantity(int quantity) {
	this.quantity = quantity;
}
public int getCardId() {
	return cardId;
}
public void setCardId(int cardId) {
	this.cardId = cardId;
}
public int getProductId() {
	return productId;
}
public CartItem(int cardId, int productId, int userId) {
	super();
	this.cardId = cardId;
	this.productId = productId;
	this.userId = userId;
}
public CartItem() {
	super();
	// TODO Auto-generated constructor stub
}
public void setProductId(int productId) {
	this.productId = productId;
}
public int getUserId() {
	return userId;
}
@Override
public String toString() {
	return "CartItem [cardId=" + cardId + ", productId=" + productId + ", iserId=" + userId + "]";
}
public void setUserId(int iserId) {
	this.userId = iserId;
}
  
}
