package test.shoppingcart;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.shoppingcart.Campaign;
import com.shoppingcart.Category;
import com.shoppingcart.DiscountType;
import com.shoppingcart.Product;
import com.shoppingcart.ShoppingCart;

class CampaignTest {

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
	void testCampaignShouldIllegalArgumentExceptionForNegativeDiscount() {
	    assertThrows(IllegalArgumentException.class,
            ()->{
        		Campaign campaign= new Campaign(educationCategory,-8,1,DiscountType.Rate);
            });
	}
	
	@Test
	void testCampaignShouldIllegalArgumentExceptionForNegativeMinItemCount() {
	    assertThrows(IllegalArgumentException.class,
            ()->{
        		Campaign campaign= new Campaign(educationCategory,6,-1,DiscountType.Rate);
            });
	}
	
	
	@Test
	void testCalculateTotalDiscountForOneRateCampaign() {

		setup();
		Campaign campaign= new Campaign(educationCategory,20.0,1,DiscountType.Rate);
		double actual=campaign.calculateTotalDiscountFor(cart);
		double expected=6.0;
		assertEquals(expected, actual);

	}

	
	@Test
	void testCalculateTotalDiscountForOneAmountCampaign() {
		setup();
		Campaign campaign= new Campaign(foodCategory,20.0,1,DiscountType.Amount);
		double actual=campaign.calculateTotalDiscountFor(cart);
		double expected=5.0;
		assertEquals(expected, actual);
	}
	
	
	@Test
	void testCalculateTotalDiscountForOneRateCampaign2() {
		setup();
		Campaign campaign= new Campaign(foodCategory,20.0,1,DiscountType.Rate);
		double actual=campaign.calculateTotalDiscountFor(cart);
		double expected=1.0;
		assertEquals(expected, actual);
	}
	
	
	@Test
	void testCalculateTotalDiscountForOneRateCampaignShould0Discount() {
		setup();
		Campaign campaign= new Campaign(technologyCategory,20.0,1,DiscountType.Rate);
		double actual=campaign.calculateTotalDiscountFor(cart);
		double expected=0.0;
		assertEquals(expected, actual);
		
	}
	
	
	@Test
	void testCalculateTotalDiscountForOneAmountCamapaignShould0Discount() {
		setup();
		Campaign campaign= new Campaign(foodCategory,1,4,DiscountType.Amount);
		double actual=campaign.calculateTotalDiscountFor(cart);
		double expected=0.0;
		assertEquals(expected, actual);
	}
	
	@Test
	void testCalculateTotalDiscountForOneAmountCampaign2() {
		setup();
		Campaign campaign= new Campaign(foodCategory,1,0,DiscountType.Amount);
		double actual=campaign.calculateTotalDiscountFor(cart);
		double expected=2.0;
		assertEquals(expected, actual);
	}
	
	@Test
	void testIsValidForCampaignShouldTrue() {
		setup();
		Campaign campaign= new Campaign(foodCategory,1,0,DiscountType.Amount);
		boolean actual=campaign.isValidFor(cart);
		boolean expected=true;
		assertEquals(expected, actual);
	}

	@Test
	void testIsValidForCamapignShouldFalse() {
		setup();
		Campaign campaign= new Campaign(foodCategory,1,2,DiscountType.Amount);
		boolean actual=campaign.isValidFor(cart);
		boolean expected=false;
		assertEquals(expected, actual);
	}
	
	@Test
	void testIsValidForCampaignShouldTrue2() {
		setup();
		Campaign campaign= new Campaign(foodCategory,1,1,DiscountType.Amount);
		boolean actual=campaign.isValidFor(cart);
		boolean expected=true;
		assertEquals(expected, actual);
	}
	
	@Test
	void testGetDiscountAmountForOneRateCampaign() {
		setup();
		Campaign campaign= new Campaign(foodCategory,20.0,1,DiscountType.Rate);
		double actual=campaign.getDiscountAmountFor(appleProduct);
		double expected=0.6;
		assertEquals(expected, actual);
	
	}
	
	@Test
	void testGetDiscountAmountForOneAmaountCampaign() {
		setup();
		Campaign campaign= new Campaign(foodCategory,20.0,1,DiscountType.Amount);
		double actual=campaign.getDiscountAmountFor(mouseProduct);
		double expected=20.0;
		assertEquals(expected, actual);
	
	}
	
	@Test
	void testGetDiscountAmountForOneAmaountCampaign2() {
		setup();
		Campaign campaign= new Campaign(foodCategory,1.0,1,DiscountType.Amount);
		double actual=campaign.getDiscountAmountFor(lemonProduct);
		double expected=1.0;
		assertEquals(expected, actual);
	}
	
}
