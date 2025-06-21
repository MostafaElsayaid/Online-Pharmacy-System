package com.onlinePharmacySystem.model;

public class CartDetails {
	 private int cartItemId;
	    private String productName;
	    private double price;
	    private int quantity;
		
	    
	    
	    
	    public CartDetails() {
			super();
			// TODO Auto-generated constructor stub
		}
		public CartDetails(int cartItemId, String productName, double price, int quantity) {
			super();
			this.cartItemId = cartItemId;
			this.productName = productName;
			this.price = price;
			this.quantity = quantity;
		}
		public int getCartItemId() {
			return cartItemId;
		}
		public void setCartItemId(int cartItemId) {
			this.cartItemId = cartItemId;
		}
		public String getProductName() {
			return productName;
		}
		public void setProductName(String productName) {
			this.productName = productName;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		@Override
		public String toString() {
			return "CartDetails [cartItemId=" + cartItemId + ", productName=" + productName + ", price=" + price
					+ ", quantity=" + quantity + "]";
		}
		
}
