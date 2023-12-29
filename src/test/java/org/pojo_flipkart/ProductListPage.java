package org.pojo_flipkart;

import java.util.List;

import org.base.BaseClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import freemarker.core.ReturnInstruction.Return;

public class ProductListPage extends BaseClass{
	
	public ProductListPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath = "//div[@id='container']//a[contains(@href,'itm')]/following::a[contains(@title,'')]//preceding-sibling::a[string-length(normalize-space(text())) > 0]")
	public  List<WebElement> allProductsinPage;
	
	
	

}
