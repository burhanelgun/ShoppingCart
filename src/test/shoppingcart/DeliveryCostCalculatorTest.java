package test.shoppingcart;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.shoppingcart.Category;
import com.shoppingcart.DeliveryCostCalculator;
import com.shoppingcart.Product;
import com.shoppingcart.ShoppingCart;

class DeliveryCostCalculatorTest {
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
	void testCalculateForNotEmptyCart() {
		setup();
		DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator();
		
		double actual = deliveryCostCalculator.calculateFor(cart);
		double expected = 52.99;
		
		assertEquals(expected, actual);
	}
	
	@Test
	void testCalculateForEmptyCart() {
		ShoppingCart cart = new ShoppingCart();
		
		DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator();
		
		double actual = deliveryCostCalculator.calculateFor(cart);
		double expected = deliveryCostCalculator.getFixedCost();
		
		assertEquals(expected, actual);
	}

	@Test
	void testCalculateForNotEmptyCartWithThreeParameter() {
		setup();
		double costPerDelivery=2;
		double costPerProduct=5;
		double fixedCost = 4;
		
		DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator(costPerDelivery,costPerProduct,fixedCost);
		
		double actual = deliveryCostCalculator.calculateFor(cart);
		double expected = 30;
		
		assertEquals(expected, actual);
	}


}
