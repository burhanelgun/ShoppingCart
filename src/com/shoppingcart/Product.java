package com.shoppingcart;

public class Product {
	/**
	 * Title of the product
	 */
	private String title;
	
	/**
	 * Price of the product
	 */
	private double price;
	
	/**
	 * Category of the product
	 */
	private Category category;
	
	public Product(String title,double price, Category category) {
		
		if (title.length()<1) {
			throw new IllegalArgumentException("Invalid title: "+title);
		}
		if (price<0) {
			throw new IllegalArgumentException("Price can not be negative: "+price);
		}
		this.title=title;
		this.price=price;
		this.category=category;
	}

	public double getPrice() {
		return price;
	}
	
	public Category getCategory() {
		return category;
	}
	
	public String getTitle() {
		return title;
	}
	
	@Override
	public String toString() {
		return "Title: "+title+", Price: "+price+", Category:"+category;
	}

}
