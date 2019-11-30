package com.shoppingcart;
import java.util.Map;

public class Campaign implements Discount{
	
	/**
	 * Category of the campaign
	 */
	private Category category;
	
	/**
	 * Minimum item count rule for the campain
	 */
	private int minimumItemCount;
	
	/**
	 * Discount amount e.g. %50, 5.99 TL
	 */
	protected double discount;
	
	/**
	 * Type of the discount e.g. Rate, Amount.
	 */
	protected DiscountType discountType;
	
	
	public Campaign(Category category,double discount,int minimumItemCount,DiscountType discountType) {
		
		if(discountType==DiscountType.Rate && discount>100) {
			throw new IllegalArgumentException("Discount rate cannot be more than 100: "+discount);
		}
		else if(discountType==DiscountType.Rate && discount<=0) {
			throw new IllegalArgumentException("Discount rate cannot be less than or equal to zero: "+discount);
		}
		else if(discountType==DiscountType.Amount && discount<=0) {
			throw new IllegalArgumentException("Discount amount cannot be less than or equal to zero: "+discount);
		}
		else if(minimumItemCount<0) {
			throw new IllegalArgumentException("Minimum item count cannot be less than zero: "+minimumItemCount);
		}
		this.category=category;
		this.discount=discount;
		this.minimumItemCount=minimumItemCount;
		this.discountType=discountType;
	}

	/**
	 * Calculates and return the total discount for given cart, if campaign does not satisfy the minimum item count rule for the given cart
	 * than the Campaign is not applicable(it returns 0.0 TL discount amount)
	 */
	@Override
	public double calculateTotalDiscountFor(ShoppingCart cart) {
		
		//if the campaign rule is satisfied for the given cart then campaign is applicable 
		if(this.isValidFor(cart)) {
			double campainDiscountAmount=0.0;

			for (Map.Entry<Product, Integer> entry: cart.getProducts().entrySet() )  {
				//if category of the product is equal to the category of the campaign
				if(entry.getKey().getCategory().equals(category)) {
					//if discount amount is smaller than or equal to the price of the product
					if(getDiscountAmountFor(entry.getKey())<=entry.getKey().getPrice()) {
						campainDiscountAmount=campainDiscountAmount+(getDiscountAmountFor(entry.getKey())*entry.getValue());
					}
					//otherwise product price is used as discount amount
					else {
						campainDiscountAmount=campainDiscountAmount+entry.getKey().getPrice()*entry.getValue();
					}
				}
			}
			
			return campainDiscountAmount;

		}
		else {
			//if the campaign rule is not satisfied for the given cart, then campaign is not applicable
			return 0.0;
		}

		
	}

	
	/**
	 * if Campaign rule does not satisfy, then it will return false
	 * @param campaign 
	 * @return if Campaign rule does not satisfy, then it will return false and Campaign is not applicable otherwise return true
	 */
	@Override
	public boolean isValidFor(ShoppingCart cart) {
		
		int itemCount=0;
		
		//calculates count of the product that has same Category with the Campaign
		for (Map.Entry<Product, Integer> entry: cart.getProducts().entrySet() )  {
			if(entry.getKey().getCategory().equals(category)) {
				itemCount=itemCount+entry.getValue();
			}
		}
		
		if (itemCount>minimumItemCount) {
			return true;
		}
		else {
			return false;
		}
	}

	
	/**
	 * it calculates the discount amount for given product according to DiscountType
	 * @param product
	 * @return discount amount for given product according to discount type
	 */
	public double getDiscountAmountFor(Product product) {
		if(discountType==DiscountType.Rate) {
			return (product.getPrice()*discount/100.0);
		}
		else if(discountType==DiscountType.Amount) {
			return discount;
		}
		else {
			return 0;
		}
		
	}




}
