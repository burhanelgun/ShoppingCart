package test.shoppingcart;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.shoppingcart.Campaign;
import com.shoppingcart.Category;
import com.shoppingcart.Coupon;
import com.shoppingcart.DiscountType;
import com.shoppingcart.Product;
import com.shoppingcart.ShoppingCart;

class ShoppingCartTest {
	ShoppingCart cart = new ShoppingCart();
	Category foodCategory=new Category("Food");
	Category educationCategory=new Category("Education");
	Category technologyCategory=new Category("Technology");
	Product appleProduct = new Product("Apple", 3.0, foodCategory);
	Product lemonProduct = new Product("Lemon", 2.0, foodCategory);
	Product bookProduct = new Product("Book", 15.0, educationCategory);
	Product mouseProduct = new Product("Mouse", 40.0, technologyCategory);
	List<Campaign> campaignList = new ArrayList<Campaign>();

	public void setup() {
		cart.addItem(appleProduct, 1);
		cart.addItem(lemonProduct, 1);
		cart.addItem(bookProduct, 2);
		cart.addItem(mouseProduct, 1);
	}
	
	@Test
	void testAddItemNegativeQuantity() {
		ShoppingCart cart2 = new ShoppingCart();

		
	    assertThrows(IllegalArgumentException.class,
            ()->{
        		cart2.addItem(appleProduct, -5);
        	});
		
	}
	
	
	@Test
	void testAddItemWithSize() {
		setup();
		
		double actual=cart.getProducts().size();
		double expected= 4;
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	void testAddItemWithContainsKey() {
		setup();
		
		boolean actual=cart.getProducts().containsKey(appleProduct);
		boolean expected= true;
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	void testAddItemWithGetKey() {
		setup();
		
		Integer actual=cart.getProducts().get(bookProduct);
		Integer expected= 2;
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	void testAddItemWithGetKeyShouldNull() {
		ShoppingCart cart2 = new ShoppingCart();

		
		Integer actual=cart2.getProducts().get(bookProduct);
		Integer expected= null;
		
		assertEquals(expected, actual);	
	}
	
	@Test
	void testAddItemWtihEmptyCardShould0Size() {
		ShoppingCart cart2 = new ShoppingCart();
		Integer actual=cart2.getProducts().size();
		Integer expected= 0;
		assertEquals(expected, actual);	
	}
	


	@Test
	void testApplyCouponWithAmountType() {
		setup();
		
		Coupon coupon= new Coupon(30,10.0,DiscountType.Amount);
		cart.applyCoupon(coupon);
		
		double actual=cart.getCouponDiscount();
		double expected= 10.0;
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testApplyCouponWithAmountTypeShould0Discount() {
		setup();
		
		Coupon coupon= new Coupon(80,10.0,DiscountType.Amount);
		cart.applyCoupon(coupon);
		
		double actual=cart.getCouponDiscount();
		double expected= 0.0;
		
		assertEquals(expected, actual);
	}

	@Test
	void testApplyAmountCoupon() {
		setup();
		
		Coupon coupon= new Coupon(30,10.0,DiscountType.Amount);
		cart.applyCoupon(coupon);
		
		double actual=cart.getTotalAmountAfterDiscounts();
		double expected= 65.0;
		
		assertEquals(expected, actual);
	}
	
	
	@Test
	void testApplyAmountCoupon2() {
		setup();
		
		Coupon coupon= new Coupon(80,10.0,DiscountType.Amount);
		cart.applyCoupon(coupon);
		
		double actual=cart.getTotalAmountAfterDiscounts();
		double expected= 75.0;
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testApplyAmountDiscountCampaign() {
		setup();
		
		Campaign campaign= new Campaign(educationCategory,20.0,1,DiscountType.Amount);
		campaignList.add(campaign);
		cart.applyDiscount(campaignList);
		
		double actual=cart.getCampaignDiscount();
		double expected= 30;
		
		assertEquals(expected, actual);

	}
	
	@Test
	void testApplyRateDiscountCampaign() {
		setup();
		
		Campaign campaign= new Campaign(educationCategory,20.0,1,DiscountType.Rate);
		campaignList.add(campaign);
		cart.applyDiscount(campaignList);
		
		double actual=cart.getCampaignDiscount();
		double expected= 6.0;
		
		assertEquals(expected, actual);

	}
	
	@Test
	void testApplyDiscountWithMultipleCampaign() {
		setup();
		
		Campaign campaign1= new Campaign(foodCategory,50.0,0,DiscountType.Rate);
		Campaign campaign2= new Campaign(technologyCategory,1,0,DiscountType.Rate);
		Campaign campaign3= new Campaign(educationCategory,1.0,0,DiscountType.Rate);
		
		campaignList.add(campaign1);
		campaignList.add(campaign2);
		campaignList.add(campaign3);

		cart.applyDiscount(campaignList);
		
		double actual=cart.getCampaignDiscount();
		double expected= 2.5;
		
		assertEquals(expected, actual);

	}
	
	@Test
	void testApplyDiscountWithMultipleCampaign2() {
		setup();
		
		Campaign campaign1= new Campaign(foodCategory,50.0,0,DiscountType.Rate);
		Campaign campaign2= new Campaign(technologyCategory,1,0,DiscountType.Rate);
		Campaign campaign3= new Campaign(educationCategory,1.0,0,DiscountType.Rate);
		
		campaignList.add(campaign1);
		campaignList.add(campaign2);
		campaignList.add(campaign3);

		cart.applyDiscount(campaignList);
		
		double actual=cart.getTotalAmountAfterDiscounts();
		double expected=72.5;
		
		assertEquals(expected, actual);

	}
	
	@Test
	void testApplyDiscountWithMultipleCampaign3() {
		setup();
		
		setup();
		
		Campaign campaign1= new Campaign(foodCategory,50.0,0,DiscountType.Rate);
		Campaign campaign2= new Campaign(technologyCategory,1,0,DiscountType.Rate);
		Campaign campaign3= new Campaign(educationCategory,1.0,0,DiscountType.Rate);
		
		campaignList.add(campaign1);
		campaignList.add(campaign2);
		campaignList.add(campaign3);

		cart.applyDiscount(campaignList);
		
		double actual=cart.getCampaignDiscount();
		double expected= 5.0;
		
		assertEquals(expected, actual);

	}
	
	@Test
	void testApplyDiscountWithOneCampaign() {
		setup();
		
		Campaign campaign= new Campaign(educationCategory,20.0,1,DiscountType.Amount);
		campaignList.add(campaign);
		cart.applyDiscount(campaignList);
		
		double actual=cart.getTotalAmountAfterDiscounts();
		double expected= 45;
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testGetDeliveryCostWithmEmptyCart() {
		ShoppingCart shoppingCart = new ShoppingCart(4,5,6);
		
		double actual=shoppingCart.getDeliveryCost();
		double expected= 6.0;
		
		assertEquals(expected, actual);
	}

	@Test
	void testGetDeliveryCostWithmEmptyCart2() {
		ShoppingCart shoppingCart = new ShoppingCart(4,8);
		
		double actual=shoppingCart.getDeliveryCost();
		double expected= 2.99;
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testGetDeliveryCost() {
		setup();
		double actual=cart.getDeliveryCost();
		double expected= 52.99;
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testGetTotalAmountAfterDiscountsWithCampaignAndCoupon() {
		setup();
		
		Campaign campaign1= new Campaign(foodCategory,50.0,0,DiscountType.Rate);
		Campaign campaign2= new Campaign(technologyCategory,1,0,DiscountType.Rate);
		Campaign campaign3= new Campaign(educationCategory,1.0,0,DiscountType.Rate);
		
		campaignList.add(campaign1);
		campaignList.add(campaign2);
		campaignList.add(campaign3);

		cart.applyDiscount(campaignList);
		
		
		Coupon coupon= new Coupon(80,10.0,DiscountType.Amount);
		cart.applyCoupon(coupon);
		
		
		double actual=cart.getTotalAmountAfterDiscounts();
		double expected= 72.5;
		
		assertEquals(expected, actual);

	}

	
	@Test
	void testGetTotalAmountAfterDiscountsWithOnlyCoupon() {
		setup();
		
		Coupon coupon= new Coupon(80,10.0,DiscountType.Amount);
		cart.applyCoupon(coupon);
		
		
		double actual=cart.getTotalAmountAfterDiscounts();
		double expected= 75.0;
		
		assertEquals(expected, actual);

	}
	
	@Test
	void testGetTotalAmountAfterDiscountsWithOnlyCoupon2() {
		setup();
		
		Coupon coupon= new Coupon(10,11.0,DiscountType.Amount);
		cart.applyCoupon(coupon);
		
		
		double actual=cart.getTotalAmountAfterDiscounts();
		double expected= 64.0;
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testGetTotalAmountAfterDiscountsWithCouponandCampaign() {
		setup();
		
		Coupon coupon= new Coupon(10,11.0,DiscountType.Amount);
		cart.applyCoupon(coupon);
		
		
		Campaign campaign1= new Campaign(foodCategory,50.0,0,DiscountType.Rate);
		Campaign campaign2= new Campaign(technologyCategory,1,0,DiscountType.Rate);
		Campaign campaign3= new Campaign(educationCategory,1.0,0,DiscountType.Rate);
		
		campaignList.add(campaign1);
		campaignList.add(campaign2);
		campaignList.add(campaign3);
		
		cart.applyDiscount(campaignList);
		
		double actual=cart.getTotalAmountAfterDiscounts();
		double expected = 64.0;
		
		assertEquals(expected, actual);
	}
	
	
	@Test
	void testGetTotalAmountAfterDiscountsWithOnlyCampaign() {
		setup();
		
		Campaign campaign1= new Campaign(foodCategory,50.0,0,DiscountType.Rate);
		Campaign campaign2= new Campaign(technologyCategory,1,0,DiscountType.Rate);
		Campaign campaign3= new Campaign(educationCategory,1.0,0,DiscountType.Rate);
		
		campaignList.add(campaign1);
		campaignList.add(campaign2);
		campaignList.add(campaign3);
		
		cart.applyDiscount(campaignList);
		
		double actual=cart.getTotalAmountAfterDiscounts();
		double expected= 72.5;
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testGetCouponDiscountWithoutCouponShould0Discount() {
		setup();
		Campaign campaign1= new Campaign(foodCategory,50.0,0,DiscountType.Rate);
		Campaign campaign2= new Campaign(technologyCategory,1,0,DiscountType.Rate);
		Campaign campaign3= new Campaign(educationCategory,1.0,0,DiscountType.Rate);
		
		campaignList.add(campaign1);
		campaignList.add(campaign2);
		campaignList.add(campaign3);
		
		cart.applyDiscount(campaignList);
		
		double actual=cart.getCouponDiscount();
		double expected= 0.0;
		
		assertEquals(expected, actual);
		
	}

	@Test
	void testGetCouponDiscountWithAmountCoupon() {
		setup();
		
		
		Coupon coupon= new Coupon(10,11.0,DiscountType.Amount);
		cart.applyCoupon(coupon);
				
		double actual=cart.getCouponDiscount();
		double expected= 11.0;
		
		assertEquals(expected, actual);
		
	}

	@Test
	void testGetCouponDiscountWithAmountCoupon2() {
		setup();
		
		
		Coupon coupon= new Coupon(10,100.0,DiscountType.Amount);
		cart.applyCoupon(coupon);
				
		double actual=cart.getCouponDiscount();
		double expected= 75.0;
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	void testGetCouponDiscountNotSatisfyTheRuleShould0Discount() {
		setup();
		
		Coupon coupon= new Coupon(100,2.0,DiscountType.Amount);
		cart.applyCoupon(coupon);
				
		double actual=cart.getCouponDiscount();
		double expected= 0.0;
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testgetCampaignDiscount() {
		setup();
		Campaign campaign1= new Campaign(foodCategory,50.0,0,DiscountType.Rate);
		Campaign campaign2= new Campaign(technologyCategory,1,0,DiscountType.Rate);
		Campaign campaign3= new Campaign(educationCategory,1.0,0,DiscountType.Rate);
		
		campaignList.add(campaign1);
		campaignList.add(campaign2);
		campaignList.add(campaign3);
		
		cart.applyDiscount(campaignList);
		
		double actual=cart.getCampaignDiscount();
		double expected= 2.5;
		
		assertEquals(expected, actual);
	}
	
	
	@Test
	void testgetCampaignDiscountWithEmptyCampaignList() {
		setup();
		
		cart.applyDiscount(campaignList);
		
		double actual=cart.getCampaignDiscount();
		double expected= 0.0;
		
		assertEquals(expected, actual);
	}

	
	
	
	
	@Test
	void testGetTotalPriceTestWithoutDiscounts() {
		setup();
				
		double actual=cart.getTotalPrice();
		double expected= 127.99;
		
		assertEquals(expected, actual);
	}

	@Test
	void testGetTotalPriceWithCampaignAndCoupon() {
		setup();
		
		Campaign campaign1= new Campaign(foodCategory,50.0,0,DiscountType.Rate);
		Campaign campaign2= new Campaign(technologyCategory,1,0,DiscountType.Rate);
		Campaign campaign3= new Campaign(educationCategory,1.0,0,DiscountType.Rate);
		
		campaignList.add(campaign1);
		campaignList.add(campaign2);
		campaignList.add(campaign3);

		cart.applyDiscount(campaignList);
		
		
		Coupon coupon= new Coupon(20,10.0,DiscountType.Amount);
		cart.applyCoupon(coupon);
		
		double actual=cart.getTotalPrice();
		double expected= 115.49;
		
		assertEquals(expected, actual);
	}


	
	@Test
	void testGetTotalDiscountWithhoutDiscounts() {
		setup();
				
		double actual=cart.getTotalDiscount();
		double expected= 0.0;
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testGetTotalDiscountWithCampaignAndCoupon() {
		setup();
				
		
		Campaign campaign1= new Campaign(foodCategory,50.0,0,DiscountType.Rate);
		Campaign campaign2= new Campaign(technologyCategory,1,0,DiscountType.Rate);
		Campaign campaign3= new Campaign(educationCategory,1.0,0,DiscountType.Rate);
		
		campaignList.add(campaign1);
		campaignList.add(campaign2);
		campaignList.add(campaign3);

		cart.applyDiscount(campaignList);
		
		
		Coupon coupon= new Coupon(20,10.0,DiscountType.Amount);
		cart.applyCoupon(coupon);
		
		double actual=cart.getTotalDiscount();
		double expected= 12.5;
		
		assertEquals(expected, actual);
	}
	
	
	@Test
	void testGetTotalAmountBeforeDiscount() {
		setup();
		
		Campaign campaign1= new Campaign(foodCategory,50.0,0,DiscountType.Rate);
		Campaign campaign2= new Campaign(technologyCategory,1,0,DiscountType.Rate);
		Campaign campaign3= new Campaign(educationCategory,1.0,0,DiscountType.Rate);
		
		campaignList.add(campaign1);
		campaignList.add(campaign2);
		campaignList.add(campaign3);

		cart.applyDiscount(campaignList);
		
		
		Coupon coupon= new Coupon(20,10.0,DiscountType.Amount);
		cart.applyCoupon(coupon);
		
		double actual=cart.getTotalAmountBeforeDiscount();
		double expected= 75.0;
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testGetTotalPriceBeforeDiscount() {
		setup();
		
		
		Campaign campaign1= new Campaign(foodCategory,50.0,0,DiscountType.Rate);
		Campaign campaign2= new Campaign(technologyCategory,1,0,DiscountType.Rate);
		Campaign campaign3= new Campaign(educationCategory,1.0,0,DiscountType.Rate);
		
		campaignList.add(campaign1);
		campaignList.add(campaign2);
		campaignList.add(campaign3);
		
		cart.applyDiscount(campaignList);
		
		
		Coupon coupon= new Coupon(20,10.0,DiscountType.Amount);
		cart.applyCoupon(coupon);
		
		double actual=cart.getTotalPriceBeforeDiscount();
		double expected= 127.99;
		
		assertEquals(expected, actual);
		
		
	}	
	

}
