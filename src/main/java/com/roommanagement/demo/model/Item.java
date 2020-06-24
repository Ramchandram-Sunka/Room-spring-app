package com.roommanagement.demo.model;

import java.util.Date;

public class Item {
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getListOfItems() {
		return listOfItems;
	}
	public void setListOfItems(String listOfItems) {
		this.listOfItems = listOfItems;
	}
	public String getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(String dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	private String userEmail;
	private String listOfItems;
	private String dateOfPurchase;
	private double price;

}
