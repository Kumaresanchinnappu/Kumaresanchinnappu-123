package org.pojo_flipkart;

import java.util.List;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BaseClass{
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[contains(@title,'Search')]")
	public WebElement searchField;
	
	@FindBy(xpath = "//button[@type='submit']")
	public WebElement searchButton;
	
	@FindBy(xpath = "//img[contains(@src,'header_cart')]")
	public WebElement cartLogoButton;
	
	
	
	
	
	

}
