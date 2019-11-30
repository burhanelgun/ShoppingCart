package com.shoppingcart;

import java.util.ArrayList;
import java.util.List;

public class Driver {

	public static void main(String[] args) {

		System.out.println("--------------- DRIVE 1 (Without Campaign - Without Coupon)---------------");

		ShoppingCart cart = new ShoppingCart();
		
		Category foodCategory=new Category("Food");
		Category educationCategory=new Category("Education");
		Category technologyCategory=new Category("Technology");
		
		Product appleProduct = new Product("Apple", 3.0, foodCategory);
		Product lemonProduct = new Product("Lemon", 2.0, foodCategory);
		Product bookProduct = new Product("Book", 15.0, educationCategory);
		Product mouseProduct = new Product("Mouse", 40.0, technologyCategory);

		cart.addItem(appleProduct, 1);
		cart.addItem(lemonProduct, 1);
		cart.addItem(bookProduct, 2);
		cart.addItem(mouseProduct, 1);

		cart.print();

		
		System.out.println("\n\n--------------- DRIVE 2 (With Campaign - Without Coupon)---------------");

		List<Campaign> campaignList = new ArrayList<Campaign>();
		Campaign campaign1= new Campaign(educationCategory,20.0,1,DiscountType.Rate);
		campaignList.add(campaign1);

		ShoppingCart cart1 = new ShoppingCart();
		cart1.addItem(appleProduct, 1);
		cart1.addItem(lemonProduct, 1);
		cart1.addItem(bookProduct, 2);
		cart1.addItem(mouseProduct, 1);

		cart1.applyDiscount(campaignList);
		
		cart1.print();
		
		System.out.println("\n\n--------------- DRIVE 3 (With Campaign - Without Coupon) ---------------");
		
		Campaign campaign2= new Campaign(educationCategory,20.0,1,DiscountType.Amount);
		ShoppingCart cart2 = new ShoppingCart();
		cart2.addItem(appleProduct, 1);
		cart2.addItem(lemonProduct, 1);
		cart2.addItem(bookProduct, 2);
		cart2.addItem(mouseProduct, 1);

		campaignList.clear();
		campaignList.add(campaign2);
		cart2.applyDiscount(campaignList);
		
		cart2.print();
		
		System.out.println("\n\n--------------- DRIVE 4 (With Campaign - With Coupon) ---------------");
		
		Campaign campaign3= new Campaign(educationCategory,20.0,1,DiscountType.Amount);
		Coupon coupon= new Coupon(30,10.0,DiscountType.Amount);

		ShoppingCart cart3 = new ShoppingCart();
		cart3.addItem(appleProduct, 1);
		cart3.addItem(lemonProduct, 1);
		cart3.addItem(bookProduct, 2);
		cart3.addItem(mouseProduct, 1);

		campaignList.clear();
		campaignList.add(campaign3);
		cart3.applyDiscount(campaignList);
		cart3.applyCoupon(coupon);

		cart3.print();
		
		System.out.println("\n\n--------------- DRIVE 5 (With Campaign - With Coupon) ---------------");
		
		Campaign campaign4= new Campaign(educationCategory,20.0,1,DiscountType.Amount);
		Coupon coupon1= new Coupon(30,20.0,DiscountType.Rate);

		ShoppingCart cart4 = new ShoppingCart();
		cart4.addItem(appleProduct, 1);
		cart4.addItem(lemonProduct, 1);
		cart4.addItem(bookProduct, 2);
		cart4.addItem(mouseProduct, 1);

		campaignList.clear();
		campaignList.add(campaign4);
		cart4.applyDiscount(campaignList);
		cart4.applyCoupon(coupon1);

		cart4.print();
		System.out.println("\n\n--------------- DRIVE 6 (Without Campaign - With Coupon) ---------------");
		
		Coupon coupon2= new Coupon(30,20.0,DiscountType.Rate);

		ShoppingCart cart5 = new ShoppingCart();
		cart5.addItem(appleProduct, 1);
		cart5.addItem(lemonProduct, 1);
		cart5.addItem(bookProduct, 2);
		cart5.addItem(mouseProduct, 1);

		cart5.applyCoupon(coupon2);

		cart5.print();
		
		System.out.println("\n\n--------------- DRIVE 7 (With Campaign - Without Coupon) ---------------");
		
		Campaign campaign5= new Campaign(foodCategory,50.0,0,DiscountType.Rate);
		Campaign campaign6= new Campaign(technologyCategory,1,0,DiscountType.Rate);
		Campaign campaign7= new Campaign(educationCategory,1.0,0,DiscountType.Rate);

		ShoppingCart cart6 = new ShoppingCart();
		cart6.addItem(appleProduct, 1);
		cart6.addItem(lemonProduct, 1);
		cart6.addItem(bookProduct, 2);
		cart6.addItem(mouseProduct, 1);

		
		campaignList.clear();
		campaignList.add(campaign5);
		campaignList.add(campaign6);
		campaignList.add(campaign7);
		cart6.applyDiscount(campaignList);

		cart6.print();
		
		
		System.out.println("\n\n--------------- DRIVE 8 (With Campaign - Without Coupon) ---------------");
		
		Campaign campaign11= new Campaign(foodCategory,0.1,0,DiscountType.Amount);
		Campaign campaign12= new Campaign(technologyCategory,5,0,DiscountType.Rate);
		Campaign campaign13= new Campaign(educationCategory,10.0,0,DiscountType.Rate);
		
		

		ShoppingCart cart8 = new ShoppingCart();
		cart8.addItem(appleProduct, 1);
		cart8.addItem(lemonProduct, 1);
		cart8.addItem(bookProduct, 2);
		cart8.addItem(mouseProduct, 1);

		campaignList.clear();
		campaignList.add(campaign11);
		campaignList.add(campaign12);
		campaignList.add(campaign13);

		cart8.applyDiscount(campaignList);

		cart8.print();
		
		
		System.out.println("\n\n--------------- DRIVE 9 (With Campaign - With Coupon) ---------------");
		
		Campaign campaign15= new Campaign(foodCategory,0.1,0,DiscountType.Amount);
		Campaign campaign16= new Campaign(technologyCategory,5,0,DiscountType.Rate);
		Campaign campaign17= new Campaign(educationCategory,10.0,0,DiscountType.Rate);
		
		Coupon coupon3= new Coupon(30,50.0,DiscountType.Rate);


		ShoppingCart cart9 = new ShoppingCart();
		cart9.addItem(appleProduct, 1);
		cart9.addItem(lemonProduct, 1);
		cart9.addItem(bookProduct, 2);
		cart9.addItem(mouseProduct, 1);

		campaignList.clear();
		campaignList.add(campaign15);
		campaignList.add(campaign16);
		campaignList.add(campaign17);
		
		cart9.applyDiscount(campaignList);
		cart9.applyCoupon(coupon3);

		cart9.print();
		
		System.out.println("\n\n--------------- DRIVE 10 (Apply Campaign After Coupon) ---------------");
		
		Campaign campaign18= new Campaign(foodCategory,0.1,0,DiscountType.Amount);
		Campaign campaign19= new Campaign(technologyCategory,5,0,DiscountType.Rate);
		Campaign campaign20= new Campaign(educationCategory,10.0,0,DiscountType.Rate);
		
		Coupon coupon4= new Coupon(30,50.0,DiscountType.Rate);


		ShoppingCart cart10 = new ShoppingCart();
		cart10.addItem(appleProduct, 1);
		cart10.addItem(lemonProduct, 1);
		cart10.addItem(bookProduct, 2);
		cart10.addItem(mouseProduct, 1);

		campaignList.clear();
		campaignList.add(campaign18);
		campaignList.add(campaign19);
		campaignList.add(campaign20);
		
		cart10.applyCoupon(coupon4);
		cart10.applyDiscount(campaignList);

		cart9.print();
			

	}

}
