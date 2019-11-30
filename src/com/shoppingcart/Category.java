package com.shoppingcart;

public class Category {
	
	/**
	 * Title of the category
	 */
	private String title;
	
	/**
	 * Parent category of the category
	 */
	private Category parentCategory;
	
	public Category(String title) {
		if (title.length()==0) {
			throw new IllegalArgumentException("Invalid title: "+title);
		}
		this.title=title;
	}
	
	public Category(String title,Category parentCategory) {
		if (title.length()<1) {
			throw new IllegalArgumentException("Invalid title: "+title);
		}
		this.title=title;
		this.setParentCategory(parentCategory);
	}
	
	
	@Override
	public String toString() {
		if(parentCategory!=null) {
			return title + "<"+ parentCategory;
		}
		else {
			return title;
		}
	}
	
	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}



}
