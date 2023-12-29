package org.pojo_Amazon;

import java.util.List;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BaseClass{
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id = "twotabsearchtextbox")
	public WebElement searchField;
	
	@FindBy(id = "nav-search-submit-button")
	public WebElement searchButton;
	
	@FindBy(xpath = "//img[contains(@src,'header_cart')]")
	public WebElement cartLogoButton;
	
	@FindBy(id = "nav-link-accountList")
	public WebElement signInLink;
	
	@FindBy(xpath = "//span[text()='Sign in']")
	public WebElement signInButtonElement;
	
	
	
	
	
	
	
	
	

}
