package org.test;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.Duration;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.pojo_flipkart.CartPage;
import org.pojo_flipkart.CheckoutPage;
import org.pojo_flipkart.HomePage;
import org.pojo_flipkart.ProductListPage;
import org.pojo_flipkart.ProductPage;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class FlipkartExecutionClass extends BaseClass{
	
	ExtentReports extent;
	static ExtentTest test;
	
	
	HomePage homePage;
	ProductListPage productListPage;
	ProductPage productPage;
	CartPage cartPage;
	CheckoutPage checkoutPage;
	
	
	SoftAssert s;
	FluentWait<WebDriver> fluentWait;

	@BeforeSuite
	public void setup() {
		browserLaunch();
		

		// Extent Report setup
		ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extent-report.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
	}

	@Test
	public void tc1Login() throws InterruptedException, IOException, AWTException, ParseException {
		// Start the test
		test = extent.createTest("Test Case 1 - Launch the URL");
		browserMax();
		
		homePage = new HomePage();
		productListPage = new ProductListPage();
		productPage = new ProductPage();
		cartPage = new CartPage();
		checkoutPage = new CheckoutPage();
		
// 1. Redirect to https://qa-refapp.openmrs.org/openmrs/login.htm
		
		Properties properties = propertiesFileAccess();
		
		String url = properties.getProperty("flipkartUrl");
		
		urlLaunch(url);
		impliCitWait();
		test.log(Status.PASS, "Navigated to the home page");
		
	

		

// 2. Enter Username(Admin) and password(Admin123)
		
		fillText(homePage.searchField, "mens belt");
		buttonClick(homePage.searchButton);
		
		test.log(Status.PASS, "Performed product search");
		
	
		
// 3. Pick any location below and click on Login. 
		
		String returnProductNameToSelect = returnData(productListPage.allProductsinPage.get(0));
		System.out.println(returnProductNameToSelect);
		buttonClick(productListPage.allProductsinPage.get(3));
		
		switchWindow();
		
		Thread.sleep(3000);
		String returnProductPrice = returnData(productPage.productPrice);
		System.out.println(returnProductPrice);
		
		Thread.sleep(3000);
		actionsclassclick(productPage.addToCartButton);
		
		
		
		test.log(Status.PASS, "Out Patient selected and Login Button clicked");
		
// 4. Verify dashboard page is redirected using Assertion. 
		
		buttonClick(cartPage.placeOrderButton);
		fillText(checkoutPage.loginEmailField, "6369130589");
		
		buttonClick(checkoutPage.continueButton);
		
		By otpFieldLocator = By.xpath("//span[text()='Enter OTP']//preceding::input[@type='text'][1]");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60)); // Wait up to 60 seconds
        WebElement otpField = wait.until(ExpectedConditions.presenceOfElementLocated(otpFieldLocator));

        while (true) {
            String otpValue = otpField.getAttribute("value");
            
            if (otpValue != null && !otpValue.trim().isEmpty()) {
                System.out.println("OTP entered: " + otpValue);
                break;
            }

            try {
                Thread.sleep(50000); // Wait for 2 seconds before checking again
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
				
				buttonClick(checkoutPage.loginButton);
				
				
		
		List<WebElement> allAddressRadioButton = checkoutPage.addressRadioButton;
		if (!allAddressRadioButton.get(0).isSelected()) {
			buttonClick(checkoutPage.addressRadioButton.get(0));
		}
		
		//Thread.sleep(4000);
		
		buttonClick(checkoutPage.deliverButton);
		
		buttonClick(checkoutPage.continueButton);
		
		if (checkoutPage.warningAcceptButton.isDisplayed()) {
			buttonClick(checkoutPage.warningAcceptButton);
		}
		
		buttonClick(checkoutPage.codRadioButton);
		
		buttonClick(checkoutPage.logoLink);
		
		buttonClick(homePage.cartLogoButton);
		
		List<WebElement> allRemoveButtons = cartPage.allRemoveButtons;
		for (WebElement eachRemoveButton : allRemoveButtons) {
			buttonClick(eachRemoveButton);
			buttonClick(cartPage.removeConfirmButton);
		}
		
		
		
//		fluentWait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(1)).ignoring(Throwable.class);
//		fluentWait.until(ExpectedConditions.titleContains("Home"));
//		Assert.assertTrue(driver.getTitle().contains("Home"), "Dashboard not found");
//		Assert.assertTrue(driver.getCurrentUrl().contains("home"), "Dashboard not found");
//		Assert.assertTrue(dashboardPage.loggedInTextMsg.getText().contains("Logged in"), "Dashboard not found");
//		test.log(Status.PASS, "Dashboard page is redirected");

		//captureScreenshot("Successful Logged In");
		
	}
	
	

	 @AfterMethod
	 public void tearDown(ITestResult result) throws IOException {
	 if (result.getStatus() == ITestResult.FAILURE) {
	 test.log(Status.FAIL, MarkupHelper.createLabel("Test Case Failed: " +
	 result.getName(), ExtentColor.RED));
	 // Capture and attach a screenshot
	 String screenshotPath = captureScreenshot(result.getName());
	 test.addScreenCaptureFromPath(screenshotPath);
	 } else if (result.getStatus() == ITestResult.SUCCESS) {
	 test.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed: " +
	 result.getName(), ExtentColor.GREEN));
	 }
	
	 //driver.quit();
	 }

	@AfterSuite
	public void tearDownSuite() {
		extent.flush();
	}
	

	// Implement a method to capture screenshots
	private static String captureScreenshot(String screenshotName) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File srcFile = screenshot.getScreenshotAs(OutputType.FILE);

		
		String path = System.getProperty("user.dir");
		String screenshotPath = path + "\\Screenshots\\" + screenshotName + ".png";
		System.out.println(screenshotPath);

		try {
			FileUtils.copyFile(srcFile, new File(screenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}

		test.addScreenCaptureFromPath(screenshotPath);
		return screenshotPath;
	}

}
