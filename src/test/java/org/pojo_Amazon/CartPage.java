package org.pojo_Amazon;

import java.util.List;

import org.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BaseClass{
	
	public CartPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//input[contains(@name,'delete')]")
	public List<WebElement> deleteItemButtons;
	
	@FindBy(xpath = "//h1[contains(text(),'Your Amazon Cart is empty')]")
	public WebElement emptyCartMessage;
	
	@FindBy(xpath = "//span[contains(@class,'item-product-title')]//span[contains(@class,'truncate-cut')]")
	public WebElement productNameinCartPage;
	
	public WebElement priceReturnFromCartPage(String productName) {
		String[] split = productName.split(" ");
		String firstWordProdNameString = split[0];
		WebElement element = driver.findElement(By.xpath("(//span[contains(text(),'"+firstWordProdNameString+"')]/following::span[contains(@class,'product-price')])[1]"));
		
		return element;
	}
	
	

}
