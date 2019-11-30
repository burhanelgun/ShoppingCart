package test.shoppingcart;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.shoppingcart.Campaign;
import com.shoppingcart.Category;
import com.shoppingcart.Coupon;
import com.shoppingcart.DiscountType;
import com.shoppingcart.Product;
import com.shoppingcart.ShoppingCart;

class CouponTest {
	
	ShoppingCart cart = new ShoppingCart();
	Category foodCategory=new Category("Food");
	Category educationCategory=new Category("Education");
	Category technologyCategory=new Category("Technology");
	Product appleProduct = new Product("Apple", 3.0, foodCategory);
	Product lemonProduct = new Product("Lemon", 2.0, foodCategory);
	Product bookProduct = new Product("Book", 15.0, educationCategory);
	Product mouseProduct = new Product("Mouse", 40.0, technologyCategory);
	
	public void setup() {
		cart.addItem(appleProduct, 1);
		cart.addItem(lemonProduct, 1);
		cart.addItem(bookProduct, 2);
		cart.addItem(mouseProduct, 1);
	}

	
	@Test
	void testCouponShouldIllegalArgumentExceptionForNegativeMinPurchaseAmount() {
	    assertThrows(IllegalArgumentException.class,
            ()->{
        		Coupon coupon= new Coupon(-6,5,DiscountType.Amount);
            });
	}
	
	@Test
	void testCouponShouldIllegalArgumentExceptionForNegativeDiscount() {
	    assertThrows(IllegalArgumentException.class,
            ()->{
            	Coupon coupon= new Coupon(30,-5,DiscountType.Amount);
            });
	}
	
	
	@Test
	void testCalculateTotalDiscountForAmountCoupon() {
		setup();	
		Coupon coupon= new Coupon(30,10.0,DiscountType.Amount);
		double actual=coupon.calculateTotalDiscountFor(cart);
		double expected=10;
		assertEquals(expected, actual);
	}
	
	@Test
	void testCalculateTotalDiscountForRateCoupon() {
		setup();	
		Coupon coupon= new Coupon(30,10.0,DiscountType.Rate);
		double actual=coupon.calculateTotalDiscountFor(cart);
		double expected=7.5;
		assertEquals(expected, actual);
	}
	
	@Test
	void testCalculateTotalDiscountForAmountCoupon2() {
		setup();	
		Coupon coupon= new Coupon(30,5,DiscountType.Amount);
		double actual=coupon.calculateTotalDiscountFor(cart);
		double expected=5.0;
		assertEquals(expected, actual);
	}
	
	@Test
	void testCalculateTotalDiscountForAmountCoupon3() {
		setup();	
		Coupon coupon= new Coupon(10,100,DiscountType.Amount);
		double actual=coupon.calculateTotalDiscountFor(cart);
		double expected=75.0;
		assertEquals(expected, actual);
	}
	
	@Test
	void testCalculateTotalDiscountForAmountCouponShould0Discount() {
		setup();	
		Coupon coupon= new Coupon(100,10,DiscountType.Amount);
		double actual=coupon.calculateTotalDiscountFor(cart);
		double expected=0.0;
		assertEquals(expected, actual);
	}
	
	@Test
	void testIsValidForAmountCouponShouldFalse() {
		setup();
		Coupon coupon= new Coupon(100,10,DiscountType.Amount);
		boolean actual=coupon.isValidFor(cart);
		boolean expected=false;
		assertEquals(expected, actual);
	}
	
	@Test
	void testIsValidForAmountCouponShouldTrue() {
		setup();
		Coupon coupon= new Coupon(10,100,DiscountType.Amount);
		boolean actual=coupon.isValidFor(cart);
		boolean expected=true;
		assertEquals(expected, actual);
	}
	
	@Test
	void testIsValidForRateCouponShouldTrue() {
		setup();
		Coupon coupon= new Coupon(10,100,DiscountType.Rate);
		boolean actual=coupon.isValidFor(cart);
		boolean expected=true;
		assertEquals(expected, actual);
	}
	
	@Test
	void testIsValidForRateCouponShouldFalse() {
		setup();
		Coupon coupon= new Coupon(100,10,DiscountType.Rate);
		boolean actual=coupon.isValidFor(cart);
		boolean expected=false;
		assertEquals(expected, actual);
	}

	@Test
	void testGetDiscountAmountForRateCoupon() {
		setup();
		Coupon coupon= new Coupon(30,10.0,DiscountType.Rate);
		double actual=coupon.getDiscountAmountFor(cart);
		double expected=7.5;
		assertEquals(expected, actual);
	}
	
	@Test
	void testGetDiscountAmountForAmountCoupon() {
		setup();
		Coupon coupon= new Coupon(30,10.0,DiscountType.Amount);
		double actual=coupon.getDiscountAmountFor(cart);
		double expected=10.0;
		assertEquals(expected, actual);
		
	}
	
	@Test
	void testGetDiscountAmountForRateCoupon2() {
		setup();
		Coupon coupon= new Coupon(300,10.0,DiscountType.Rate);
		double actual=coupon.getDiscountAmountFor(cart);
		double expected=0.0;
		assertEquals(expected, actual);
		
	}

}
