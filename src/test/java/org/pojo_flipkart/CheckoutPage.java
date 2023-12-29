package org.pojo_flipkart;

import java.util.List;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BaseClass{

	public CheckoutPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[@type='text']")
	public WebElement loginEmailField;
	
	@FindBy(xpath = "//*[text()='CONTINUE']")
	public WebElement continueButton;
	
	@FindBy(xpath = "//span[text()='Login']")
	public WebElement loginButton;
	
	@FindBy(xpath = "//span[text()='Enter OTP']//preceding::input[@type='text'][1]")
	public WebElement optField;
	
	@FindBy(xpath = "//input[@name='address' and @type='radio']")
	public List<WebElement> addressRadioButton;
	
	@FindBy(xpath = "//button[text()='Deliver Here']")
	public WebElement deliverButton;
	
	@FindBy(id = "COD")
	public WebElement codRadioButton;
	
	@FindBy(xpath = "//img[@title='Flipkart']")
	public WebElement logoLink;
	
	@FindBy(xpath = "//button[text()='Accept & Continue']")
	public WebElement warningAcceptButton;
	
	
	
	
	
	
	
	
	
}
