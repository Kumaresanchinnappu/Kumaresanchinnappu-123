package org.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.By;
import java.util.concurrent.TimeUnit;

public class SampleTest {

	

	public static void main(String[] args) {
	        WebDriverManager.chromedriver().setup();
	        WebDriver driver = new ChromeDriver();
	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	        try {
	            driver.get("https://mail.google.com/");

	            // Login to Gmail (replace with your credentials or automation)
	            driver.findElement(By.id("identifierId")).sendKeys("salman.connect1@gmail.com");
	            driver.findElement(By.id("identifierNext")).click();
	            driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Sallu@0747");
	            driver.findElement(By.id("identifierNext")).click();
	            // Enter password and click "Next" (or use password manager)

	            // Open the OTP email (adjust XPath if needed)
	            driver.findElement(By.xpath("//span[text()='OTP Email Subject']")).click();

	            // Extract the OTP value (adjust XPath if needed)
	            String otpText = driver.findElement(By.xpath("//div[@class='a3s']/span[text()='Your OTP is:']/following-sibling::span")).getText();
	            String otp = otpText.substring(otpText.indexOf(" ") + 1);

	            System.out.println("Extracted OTP: " + otp);

	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            driver.quit();
	        }
	    }
}
