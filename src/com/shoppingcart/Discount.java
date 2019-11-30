package com.shoppingcart;

public interface  Discount {
	
	/**
	 * Calculates total discount for given cart
	 * @param cart
	 * @return total discount amount for given cart
	 */
	public abstract double calculateTotalDiscountFor(ShoppingCart cart);

	/**
	 * Checks the discount is valid(satisfy the rule of Discount)
	 * @param cart
	 * @return false if the discount is not applicable otherwise return true
	 */
	public abstract boolean isValidFor(ShoppingCart cart);
	

	

}
