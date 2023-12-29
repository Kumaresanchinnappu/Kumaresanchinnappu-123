package org.pojo_Amazon;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseClass{
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "ap_email")
	public WebElement emailField;
	
	@FindBy(xpath = "//input[@id='continue']")
	public WebElement continueButton;
	
	@FindBy(id = "ap_password")
	public WebElement passwordField;
	
	@FindBy(id = "signInSubmit")
	public WebElement signInButton;
	
	@FindBy(xpath = "//a[contains(@id,'fixup')]")
	public WebElement ignoreWarning;
	
	

}
