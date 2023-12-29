package org.pojo_flipkart;

import java.util.List;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BaseClass{
	
	public CartPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//span[text()='Place Order']")
	public WebElement placeOrderButton;
	
	@FindBy(xpath = "//div[text()='Remove']")
	public List<WebElement> allRemoveButtons;
	
	@FindBy(xpath = "//div[text()='Remove Item']//..//div[text()='Remove']")
	public WebElement removeConfirmButton;
	
	@FindBy(xpath = "//div[contains(text(),'Successfully removed')]")
	public WebElement cartProductRemovedMessage;
	
	

}
