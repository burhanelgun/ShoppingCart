package com.shoppingcart;

public class Coupon implements Discount {
	
	/**
	 * Minimum purchase amount rule
	 */
	private double minPurchaseAmount;
	
	/**
	 * Discount amount e.g. %50, 5.99 TL
	 */
	protected double discount;
	
	/**
	 * Type of the discount e.g. Rate, Amount.
	 */
	protected DiscountType discountType;
	
	
	public Coupon(double minPurchaseAmount, double discount,DiscountType discountType) {
		if(discountType==DiscountType.Rate && discount>100) {
			throw new IllegalArgumentException("Discount rate cannot be more than 100: "+discount);
		}
		else if(discountType==DiscountType.Rate && discount<=0) {
			throw new IllegalArgumentException("Discount rate cannot be less than or equal to zero: "+discount);
		}
		else if(discountType==DiscountType.Amount && discount<=0) {
			throw new IllegalArgumentException("Discount amount cannot be less than or equal to zero: "+discount);
		}
		else if(minPurchaseAmount<0) {
			throw new IllegalArgumentException("Minimum purchase amount cannot be less than or equal to zero: "+minPurchaseAmount);
		}
		
		this.minPurchaseAmount = minPurchaseAmount;
		this.discount = discount;
		this.discountType = discountType;
	}
	
	
	/**
	 * Calculates and return the total discount for given cart, if campaign does not satisfy the minimum purchase amount rule for the given cart
	 * than the Coupon is not applicable(it returns 0.0 TL discount amount)
	 */
	@Override
	public double calculateTotalDiscountFor(ShoppingCart cart) {
		
		//if discount amount is less than or equal than the getTotalAmountAfterDiscounts of the cart
		if(cart.getTotalAmountAfterDiscounts()>=getDiscountAmountFor(cart)) {
			return getDiscountAmountFor(cart);
		}
		//otherwise use getTotalAmountAfterDiscounts of the cart as the discount amount
		else {
			return cart.getTotalAmountAfterDiscounts();
		}
	
	}

	/**
	 * if Coupon rule does not satisfy, then it will return false and Coupon is not applicable
	 * @param campaign 
	 * @return if Coupon rule does not satisfy, then it will return false and Coupon is not applicable otherwise return true
	 */
	@Override
	public boolean isValidFor(ShoppingCart cart) {
		if(cart.getTotalAmountAfterDiscounts()<minPurchaseAmount) {
			System.out.println("Coupon is not applicaple, your total purchase amount("+cart.getTotalAmountAfterDiscounts()+") is less than minimum total purchase amount("+minPurchaseAmount +")");
			return false;
		}
		else {
			return true;
		}
	}


	/**
	 * Coupon discount amount in TL format (convert if DiscountType is Rate, otherwise don't change)
	 * if cart total amount after discounts is less than the minimum purchase amount than return 0
	 */
	public double getDiscountAmountFor(ShoppingCart cart) {
		//if the Coupon rule is satisfied for the given cart then Coupon is applicable 
		if(this.isValidFor(cart)) {
			if(discountType==DiscountType.Rate) {
				return (cart.getTotalAmountAfterDiscounts()*discount/100.0);
			}
			else if(discountType==DiscountType.Amount) {
				return discount;
			}
			else {
				return 0;
			}
		}
		else {
			return 0.0;
		}
	
	}

	



}
