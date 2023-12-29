package org.pojo_flipkart;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends BaseClass{

	public ProductPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//button[text()='Add to cart']")
	public WebElement addToCartButton;
	
	@FindBy(xpath = "(//div[contains(text(),'₹')])[1]")
	public WebElement productPrice;
	
	
	
}
