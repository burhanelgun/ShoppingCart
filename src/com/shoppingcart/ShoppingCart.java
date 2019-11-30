package com.shoppingcart;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingCart {
	
	/**
	 * Stores products and quantities
	 */
	private Map<Product,Integer> products;
	
	/**
	 * Total purchase amount(not added delivery cost)
	 */
	private double totalPurchaseAmount;
	
	/**
	 * Coupon discount can be rate or amount
	 */
	private double couponDiscountAmount;
	
	/**
	 * Campaign discount can be rate or amount
	 */
	private double totalCampaignDiscountAmount;
	
	/**
	 * DeliveryCostCalculator handles the calculating of delivery costs
	 */
	DeliveryCostCalculator deliveryCostCalculator;


	
	public ShoppingCart() {
		products = new HashMap<Product, Integer>();
		totalPurchaseAmount=0;
		couponDiscountAmount=0;
		totalCampaignDiscountAmount=0;
		deliveryCostCalculator = new DeliveryCostCalculator();
	}
	
	public ShoppingCart(double costPerDelivery,double costPerProduct) {
		products = new HashMap<Product, Integer>();
		totalPurchaseAmount=0;
		couponDiscountAmount=0;
		totalCampaignDiscountAmount=0;
		if(costPerDelivery<0) {
			throw new IllegalArgumentException("Cost per delivery cannot be less than 0. Cost per delivery: "+costPerDelivery);
		}
		if(costPerProduct<0) {
			throw new IllegalArgumentException("Cost per product cannot be less than 0. Cost per product: "+costPerProduct);
		}
		deliveryCostCalculator = new DeliveryCostCalculator(costPerDelivery, costPerProduct);
	}

	public ShoppingCart(double costPerDelivery,double costPerProduct,double fixedCost) {
		products = new HashMap<Product, Integer>();
		totalPurchaseAmount=0;
		couponDiscountAmount=0;
		totalCampaignDiscountAmount=0;
		if(costPerDelivery<0) {
			throw new IllegalArgumentException("Cost per delivery cannot be less than 0. Cost per delivery: "+costPerDelivery);
		}
		if(costPerProduct<0) {
			throw new IllegalArgumentException("Cost per product cannot be less than 0. Cost per product: "+costPerProduct);
		}
		if(fixedCost<0) {
			throw new IllegalArgumentException("Fixed cost cannot be less than 0. Fixed cost: "+costPerProduct);
		}
		deliveryCostCalculator = new DeliveryCostCalculator(costPerDelivery, costPerProduct, fixedCost);
	}

	
	/**
	 * Add item to products map
	 * @param item will be add to products map
	 * @param quantity quantity of the item
	 */
	public void addItem(Product product,int quantity) {
		if(quantity<0) {
			throw new IllegalArgumentException("Quantity cannot be less than zero. Quantity: "+quantity);
		}
		else {
			if(products.containsKey(product)) {
				int oldQuantity=products.get(product);
				int newQuantity=oldQuantity+quantity;
				products.put(product,newQuantity);
			}
			else {
				products.put(product,quantity);
			}
			totalPurchaseAmount=totalPurchaseAmount+product.getPrice()*quantity;
		}
	}	
	
	
	/**
	 * Coupons exists for cart discounts
	 * @param coupon
	 * @return
	 */
	public void applyCoupon(Coupon coupon) {

		couponDiscountAmount=coupon.calculateTotalDiscountFor(this);
		totalPurchaseAmount=totalPurchaseAmount-couponDiscountAmount;

	}

	
	/**
	 * Campaigns exist for product price discounts
	 * @param coupon
	 * @return
	 */
	public boolean applyDiscount(List<Campaign> campaignsList) {
		
		//if there is not an applied Coupon before the Campaign
		if(couponDiscountAmount==0.0) {
			//select maximum discount campaign

			double maxCampainDiscountAmount=0.0;
			
			for (int i = 0; i < campaignsList.size(); i++) {
				
				double campainDiscountAmount=campaignsList.get(i).calculateTotalDiscountFor(this);
				
				if(campainDiscountAmount>=maxCampainDiscountAmount) {
					maxCampainDiscountAmount=campainDiscountAmount;
				}
			}

			totalCampaignDiscountAmount=maxCampainDiscountAmount;
			totalPurchaseAmount=totalPurchaseAmount-totalCampaignDiscountAmount;

			return true;
		}
		else {
			System.out.println("***Can not apply Campaign after the Coupon(Only Coupon was applied)***");
			return false;
		}


	}

	/**
	 * Round doubles that has more than two digits after the dot
	 * @param num
	 * @return
	 */
	public double roundDouble(double num) {
		return (double)Math.round(num * 100d) / 100d;
	}

	
	/**
	 * 
	 * @return Total amount after discounts
	 */
	public double getTotalAmountAfterDiscounts() {
		return roundDouble(totalPurchaseAmount);
	}
	
	/**
	 * 
	 * @return Amount of the Coupon discount
	 */
	public double getCouponDiscount() {
		return roundDouble(couponDiscountAmount);
	}
	
	/**
	 * 
	 * @return Amount of the Campaign discount
	 */
	public double getCampaignDiscount() {
		return roundDouble(totalCampaignDiscountAmount);
	}
	
	/**
	 * 
	 * @return Cost of the delivery
	 */
	public double getDeliveryCost() {
		return roundDouble(deliveryCostCalculator.calculateFor(this));
	}
	
	/**
	 * Total price = Total amount after the discounts + Delivery cost
	 * @return total price 
	 */
	public double getTotalPrice() {
		return roundDouble(getTotalAmountAfterDiscounts()+getDeliveryCost());
	}
	
	/**
	 * Total discount = Coupon discount + Campaign discount
	 * @return total discount 
	 */
	public double getTotalDiscount() {
		return roundDouble(getCouponDiscount()+getCampaignDiscount());
	}
	
	/**
	 * 
	 * @return Total purchase amount before the discounts
	 */
	public double getTotalAmountBeforeDiscount() {
		return roundDouble(getTotalAmountAfterDiscounts()+getTotalDiscount());
	}
	
	/**
	 * 
	 * @return Total price amount before the discounts
	 */
	public double getTotalPriceBeforeDiscount() {
		return roundDouble(getTotalPrice()+getTotalDiscount());
	}
	
	public void print() {
		
		//group products by Category
		HashMap<Category, List<Map.Entry<Product, Integer>>> categoricalHashMap = new HashMap<Category, List<Map.Entry<Product, Integer>>>();
		for(Map.Entry<Product, Integer> product: products.entrySet()) {
			if (!categoricalHashMap.containsKey(product.getKey().getCategory())) {
			    List<Map.Entry<Product, Integer>> list = new ArrayList<Map.Entry<Product, Integer>>();
			    list.add(product);
			    categoricalHashMap.put(product.getKey().getCategory(), list);
			} else {
				categoricalHashMap.get(product.getKey().getCategory()).add(product);
			}
		}
		
		//print the products with CategoryName, ProductName, Quantity, Unit Price
		for(Map.Entry<Category, List<Map.Entry<Product, Integer>>> entry: categoricalHashMap.entrySet()) {
			System.out.println("Category:"+entry.getKey());
			List <Map.Entry<Product, Integer>> list= entry.getValue();
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).getKey()+", Quantity:"+list.get(i).getValue());
			}
			System.out.println();
			
		}
		System.out.println("Total Price(Before discounts): "+getTotalPriceBeforeDiscount()+ " ( Total Amount:"+getTotalAmountBeforeDiscount()+" , Delivery Cost:"+getDeliveryCost()+" )");
		//Print Total Price and Total Discount
		System.out.println("Total Discount               : "+(getTotalDiscount())+ " ( Campaign Discount:"+getCampaignDiscount()+" , Coupon Discount:"+getCouponDiscount()+" )");
		System.out.println("Total Price(After discounts) : "+getTotalPrice()+ " ( Total Amount:"+getTotalAmountAfterDiscounts()+" , Delivery Cost:"+getDeliveryCost()+" )");

	}


	
	public Map<Product, Integer> getProducts() {
		return products;
	}
	



	
	
	

}
