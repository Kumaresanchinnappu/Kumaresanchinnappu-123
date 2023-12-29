package org.pojo_Amazon;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends BaseClass{

	public ProductPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "add-to-cart-button")
	public WebElement addToCartButton;
	
	@FindBy(name = "proceedToRetailCheckout")
	public WebElement proceedToCheckoutButton;
	
	@FindBy(xpath = "(//div[@class='a-box-group']//span[contains(@class,'a-price')]//span[@class='a-offscreen'])[1]")
	public WebElement productPrice;
	
	
	
	
	
}
