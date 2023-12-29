package org.pojo_Amazon;

import java.util.List;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BaseClass{

	public CheckoutPage() {
		PageFactory.initElements(driver, this);
	}
	
	
	
	@FindBy(id = "shipToThisAddressButton")
	public WebElement shipToAddressConfirmButton;
	
	@FindBy(xpath = "//div[@id='banner-image']//i")
	public WebElement logoLink;
	
	
	
	@FindBy(xpath = "//a[text()='Add a credit or debit card']")
	public WebElement cardPaymentLink;
	
	@FindBy(xpath = "//button[text()='Cancel']")
	public WebElement cancelButton;
	
	@FindBy(xpath = "(//a[contains(text(),'Return to Cart')])[2]")
	public WebElement returnToCartButton;
	
	@FindBy(xpath = "//h1[contains(text(),'Checkout')]")
	public WebElement checkoutTextHeading;
	
	@FindBy(xpath = "//h4[contains(@id,'popover')]")
	public WebElement cardDetailsPopup;
	
	@FindBy(xpath = "//iframe[contains(@class,'apx-secure-iframe')]")
	public WebElement iFrameInCheckoutPopupPage;
	
	
	
	
	
	
	
	
}
