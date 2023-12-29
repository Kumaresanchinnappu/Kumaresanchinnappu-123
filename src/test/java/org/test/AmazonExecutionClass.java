package org.test;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.base.BaseClass;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.FluentWait;
import org.pojo_Amazon.CartPage;
import org.pojo_Amazon.CheckoutPage;
import org.pojo_Amazon.HomePage;
import org.pojo_Amazon.LoginPage;
import org.pojo_Amazon.ProductListPage;
import org.pojo_Amazon.ProductPage;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
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

public class AmazonExecutionClass extends BaseClass {

	ExtentReports extent;
	static ExtentTest test;

	HomePage homePage;
	ProductListPage productListPage;
	ProductPage productPage;
	CartPage cartPage;
	CheckoutPage checkoutPage;
	LoginPage loginPage;

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
	public void tc1() throws InterruptedException, IOException, AWTException, ParseException {
		// Start the test
		test = extent.createTest("Test Case 1 - Launch the URL");
		browserMax();

		homePage = new HomePage();
		productListPage = new ProductListPage();
		productPage = new ProductPage();
		cartPage = new CartPage();
		checkoutPage = new CheckoutPage();
		loginPage = new LoginPage();

// 1. Redirect to https://www.amazon.com/

		Properties properties = propertiesFileAccess();

		String url = properties.getProperty("amazonUrl");

		urlLaunch(url);
		impliCitWait();
		test.log(Status.PASS, "Navigated to the home page");
	

// 2. Login to the Amazon Account with valid credentials

		actionsClassClickDifEle(homePage.signInLink, homePage.signInButtonElement);

		fillText(loginPage.emailField, properties.getProperty("phone_number"));
		buttonClick(loginPage.continueButton);

		fillText(loginPage.passwordField, properties.getProperty("password"));
		buttonClick(loginPage.signInButton);

//				try {
//				if (loginPage.ignoreWarning.isDisplayed()) {
//					buttonClick(loginPage.ignoreWarning);
//				}
//				}catch (Exception e) {
//					e.printStackTrace();
//				}

		
		
		test.log(Status.PASS, "User logged in Successfully");

	

// 3. Enter product keyword and search product

		fillText(homePage.searchField, "BELT");
		buttonClick(homePage.searchButton);

		test.log(Status.PASS, "Performed product search");

	
	
	String returnProductNameToSelect = "";
	String returnProductPriceToSelect = "";

	

// 4. Select any one product product from the product search list

		returnProductNameToSelect = returnData(productListPage.allProductsinPage.get(0));

		buttonClick(productListPage.allProductsinPage.get(0));
		
		Thread.sleep(5000);
		returnProductPriceToSelect = returnData(productPage.productPrice);
		
	

// 5. Add the product to the cart

		actionsclassclick(productPage.addToCartButton);
		
		
		test.log(Status.PASS, "Product added to cart page");

	

// 6. Proceed to checkout page

		buttonClick(productPage.proceedToCheckoutButton);

		Assert.assertTrue(checkoutPage.checkoutTextHeading.isDisplayed());

	

// 7. User select the payment mode as card payment

		buttonClick(checkoutPage.cardPaymentLink);

		//Thread.sleep(10000);

		switchToFrameWithEle(checkoutPage.iFrameInCheckoutPopupPage);

		Assert.assertTrue(checkoutPage.cardDetailsPopup.isDisplayed());

		test.log(Status.PASS, "Card details field popup is displayed");

	

// 8. User cancels payment and moves to cart page and remove product

		buttonClick(checkoutPage.cancelButton);

		exitiFrame();

		buttonClick(checkoutPage.logoLink);
		buttonClick(checkoutPage.returnToCartButton);
		
		String productNameInCartPage = returnData(cartPage.productNameinCartPage);
		
		String productName = productNameInCartPage.substring(0, productNameInCartPage.indexOf(' '));
		
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(returnProductNameToSelect.contains(productName));
		
		String returnedProductPrice = returnData(cartPage.priceReturnFromCartPage(productName));

	
		Assert.assertTrue(returnedProductPrice.contains(returnProductPriceToSelect));
		
		Thread.sleep(5000);
		List<WebElement> deleteItemButtons = cartPage.deleteItemButtons;
		for (WebElement eachDeleteButton : deleteItemButtons) {
			
			eachDeleteButton.click();
			Thread.sleep(10000);
		}

		Assert.assertTrue(cartPage.emptyCartMessage.isDisplayed());

		test.log(Status.PASS, "Product removed from the cart page");

	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			test.log(Status.FAIL, MarkupHelper.createLabel("Test Case Failed: " + result.getName(), ExtentColor.RED));
			// Capture and attach a screenshot
			String screenshotPath = captureScreenshot(result.getName());
			test.addScreenCaptureFromPath(screenshotPath);
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.log(Status.PASS, MarkupHelper.createLabel("Test Case Passed: " + result.getName(), ExtentColor.GREEN));
		}

		driver.quit();
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
