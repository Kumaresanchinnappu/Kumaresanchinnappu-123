package org.pojo_Amazon;

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
	
	@FindBy(xpath = "//div[contains(@class,'title-instructions')]//a//span[contains(@class,'text')]")
	public  List<WebElement> allProductsinPage;
	
	
	

}
