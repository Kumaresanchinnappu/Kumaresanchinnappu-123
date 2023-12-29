package org.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	
	public static WebDriver driver;
	public static Actions a;
	
	public static void browserLaunch() {
		WebDriverManager.chromedriver().setup();
		ChromeOptions chromeOptions = new ChromeOptions();
		chromeOptions.addArguments("--disable-notifications");
		
		// Set preferences to disable notification permissions
		chromeOptions.setExperimentalOption("prefs", 
            new HashMap<String, Object>() {{
                put("profile.default_content_setting_values.notifications", 2);
            }}
        );
		driver = new ChromeDriver(chromeOptions);

	}

	public static void urlLaunch(String url) {
		driver.get(url);
	}
	
	public static void impliCitWait() {
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}
	
	public static void browserMax() {
		driver.manage().window().maximize();

	}
	
	public static void fillText(WebElement ele, String text) {
		ele.sendKeys(text);
	}
	
	public static void buttonClick(WebElement ele) {
		ele.click();
	}
	
	public static void actionsclassclick(WebElement target) {
		a = new Actions(driver);
		a.moveToElement(target).click(target).perform();

	}
	
	public static void actionsClassClickDifEle(WebElement move1, WebElement click1) {
		a = new Actions(driver);
		a.moveToElement(move1).click(click1).perform();

	}
	
	public static void javascriptclick(WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", ele);

	}
	
	public static void selectByText(WebElement ele, String text) {
		Select s  = new Select(ele);
		s.selectByVisibleText(text);
		

	}
	
	public static String selectBValue(WebElement ele, String text) {
		Select s  = new Select(ele);
		s.selectByValue(text);
		return text;

	}
	
	public static Properties propertiesFileAccess() {
		Properties properties = new Properties();
		
		try {
			FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"\\config.properties");
			properties.load(fileInputStream);
			fileInputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return properties;
	}
	
	public static String returnData(WebElement ele) {
		String text = ele.getText();
		return text;
	}
	
	public static void switchWindow() {
		String parId = driver.getWindowHandle();
		Set<String> allId = driver.getWindowHandles();
		for (String eachId : allId) {
			if (!parId.equals(eachId)) {
				driver.switchTo().window(eachId);
			}
		}

	}
	
	public static void switchToFrameWithEle(WebElement ele) {
		driver.switchTo().frame(ele);

	}
	
	public static void exitiFrame() {
		driver.switchTo().defaultContent();

	}
	
}
