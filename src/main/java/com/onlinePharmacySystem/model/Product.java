package com.onlinePharmacySystem.model;

import java.util.Date;

public class Product {
	
	private int productId;
	private String name;
	private String descreption;
	private int quantity;
	private String imagePath;
	private Date date;
	private double price;
	
	
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Product(int productId, String name, String descreption, int quantity, String imagePath, Date date,double price) {
		super();
		this.productId = productId;
		this.name = name;
		this.descreption = descreption;
		this.quantity = quantity;
		this.imagePath = imagePath;
		this.date = date;
		this.price = price;
	}
	public Product() {
		super();
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", descreption=" + descreption + ", quantity="
				+ quantity + ", imagePath=" + imagePath + ", date=" + date + ", price=" + price + "]";
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getName() {
		return name;
	}
	
	public String getDescreption() {
		return descreption;
	}
	public void setDescreption(String descreption) {
		this.descreption = descreption;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setName(String name) {
		this.name = name;
		
	}
	
	
}
