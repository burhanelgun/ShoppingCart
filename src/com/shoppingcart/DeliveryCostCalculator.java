package com.shoppingcart;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DeliveryCostCalculator {

	/**
	 * Cost of per delivery
	 */
	private double costPerDelivery;
	
	/**
	 * Cost of per product
	 */
	private double costPerProduct;
	
	/**
	 * Fixed cost
	 */
	private double fixedCost;
	
	public DeliveryCostCalculator() {
		this.costPerDelivery = 10.0;
		this.costPerProduct =  5.0;
		this.fixedCost = 2.99;
		
	}
	
	public DeliveryCostCalculator(double costPerDelivery, double costPerProduct) {
		this.costPerDelivery = costPerDelivery;
		this.costPerProduct = costPerProduct;
		this.fixedCost = 2.99;
	}
	
	public DeliveryCostCalculator(double costPerDelivery, double costPerProduct, double fixedCost) {
		this(costPerDelivery, costPerProduct);
		this.fixedCost = fixedCost;
	}
	
	

	/**
	 * Calculates the delivery cost for the given card
	 * @param cart takes a ShoppingCart
	 * @return delivery cost for the given cart
	 */
	public double calculateFor(ShoppingCart cart) {

		Set<Category> categoriesSet = new HashSet<Category>();
		
		for(Map.Entry<Product, Integer> entry: cart.getProducts().entrySet()) {
			categoriesSet.add(entry.getKey().getCategory());
		}
		
		//number of distinct categories in the chart
		int numberOfDeliveries=categoriesSet.size();
		
		//number of different products
		int numberOfProducts=cart.getProducts().size();
		
		return ((costPerDelivery*numberOfDeliveries)+(costPerProduct*numberOfProducts)+fixedCost);		
	}
	
	public double getFixedCost() {
		return fixedCost;
	}
	
}
