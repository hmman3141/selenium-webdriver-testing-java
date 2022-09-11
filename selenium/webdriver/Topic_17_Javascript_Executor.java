package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_Javascript_Executor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}

		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	private String getDomainName() {
		return (String) jsExecutor.executeScript("return document.domain");
	}

	private String getURL() {
		return (String) jsExecutor.executeScript("return document.URL");
	}

	private void click(WebElement element) {
		jsExecutor.executeScript("arguments[0].click()", element);
	}

	private void scrollToElement(WebElement element) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)", element);
	}

	private void waitForSecond(int milisecond) {
		try {
			Thread.sleep(milisecond * 1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void inputSendKey(WebElement element, String content) {
		jsExecutor.executeScript("arguments[0].value='" + content + "'", element);
	}

	private String getText(WebElement element) {
		return (String) jsExecutor.executeScript("return arguments[0].innerText", element);
	}

	private void navigate(String URL) {
		jsExecutor.executeScript("window.location = '" + URL + "'");
	}

	private String getValidationMessage(WebElement element) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage", element);
	}

	@Test
	public void TC_01_TechPanda() {
		// Open website
		driver.get("http://live.techpanda.org/");

		// Declaration
		String domain = "live.techpanda.org";
		String guruDomain = "demo.guru99.com";
		String URL = "http://live.techpanda.org/";
		String guruURL = "http://demo.guru99.com/v4/";
		String message = "Samsung Galaxy was added to your shopping cart.";
		String newsletter = "hihi123@gmail.com";

		// Verify domain name
		Assert.assertEquals(getDomainName(), domain);

		// Verify URL
		Assert.assertEquals(getURL(), URL);

		// Click Mobile page
		click(driver.findElement(By.xpath("//a[text()='Mobile']")));

		// Click add to cart button
		click(driver.findElement(By.xpath(
				"//a[@title='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/button[@title='Add to Cart']")));
		waitForSecond(2);

		// Verify message
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), message);

		// Click customer service link
		click(driver.findElement(By.xpath("//a[text()='Customer Service']")));
		waitForSecond(2);

		// Scroll into element
		scrollToElement(
				driver.findElement(By.xpath("//div[contains(@class,'block-subscribe')]//span[text()='Newsletter']")));
		waitForSecond(2);

		// Send key to input
		inputSendKey(driver.findElement(By.cssSelector("input#newsletter")), newsletter);
		click(driver.findElement(By.cssSelector("button[title='Subscribe']")));
		waitForSecond(2);

		// Verify message
		message = "Thank you for your subscription.";
		Assert.assertEquals(getText(driver.findElement(By.cssSelector("li.success-msg span"))), message);

		// Navigate to "http://demo.guru99.com/v4/"
		navigate(guruURL);
		waitForSecond(2);

		// Verify domain name
		Assert.assertEquals(getDomainName(), guruDomain);
	}

	@Test
	public void TC_02_WarrantyRode() {
		// Open website
		driver.get("https://warranty.rode.com/");

		// Declaration
		String firstName = "abc";
		String surName = "abc";
		String email = "abc@gmail.com";
		String filloutMessage = "Please fill out this field.";

		// Verify first name validation message
		click(driver.findElement(By.xpath("//button[contains(text(),'Register')]")));
		Assert.assertEquals(getValidationMessage(driver.findElement(By.cssSelector("input#firstname"))),
				filloutMessage);
		waitForSecond(1);

		// Verify sur name validation message
		inputSendKey(driver.findElement(By.cssSelector("input#firstname")), firstName);
		click(driver.findElement(By.xpath("//button[contains(text(),'Register')]")));
		Assert.assertEquals(getValidationMessage(driver.findElement(By.cssSelector("input#surname"))),
				filloutMessage);
		waitForSecond(1);

		// Verify email validation message
		inputSendKey(driver.findElement(By.cssSelector("input#surname")), surName);
		click(driver.findElement(By.xpath("//button[contains(text(),'Register')]")));
		Assert.assertEquals(getValidationMessage(driver.findElement(By.cssSelector("input#email"))),
				filloutMessage);
		waitForSecond(1);

		// Verify password validation message
		inputSendKey(driver.findElement(By.xpath("//div[contains(text(),'Register')]//following-sibling::div[@class='card-body']//input[@id='email']")), email);
		click(driver.findElement(By.xpath("//button[contains(text(),'Register')]")));
		Assert.assertEquals(getValidationMessage(driver.findElement(By.xpath("//div[contains(text(),'Register')]//following-sibling::div[@class='card-body']//input[@id='password']"))),
				filloutMessage);
		waitForSecond(1);
	}

	@AfterClass
	public void afterClass() {
		// Close browser (All websites)
		driver.quit();
	}
}
