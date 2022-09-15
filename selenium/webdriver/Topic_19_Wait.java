package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Wait {
	WebDriver driver;
	WebDriverWait explicitWait;
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
		explicitWait = new WebDriverWait(driver, 10);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Visibility() {
		// Open website
		driver.get("https://www.facebook.com/");

		// Declaration
		String email = "email@gmail.com";

		// Click create new account button
		driver.findElement(By.xpath("//a[text()='Create New Account']")).click();

		// Wait for create form to be displayed
		explicitWait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.registration_redesign")));

		// Send keys
		driver.findElement(By.cssSelector("input[name='reg_email__']")).sendKeys(email);
	}

	@Test
	public void TC_01_Invisibility() {
		// Open website
		driver.get("https://www.facebook.com/");
		
		// Click create new account button
		driver.findElement(By.xpath("//a[text()='Create New Account']")).click();
		
		// Wait for create form to not be displayed
		explicitWait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
	}
	
	@Test
	public void TC_01_Presence() {
		// Open website
		driver.get("https://www.facebook.com/");

		// Click create new account button
		driver.findElement(By.xpath("//a[text()='Create New Account']")).click();

		// Wait for create form to be displayed in HTML (not necessary to be visible)
		explicitWait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[name='reg_email_confirmation__']")));
	}
	
	@Test
	public void TC_01_Staleness() {
		// Open website
		driver.get("https://www.facebook.com/");
		
		// Click create new account button
		driver.findElement(By.xpath("//a[text()='Create New Account']")).click();
		
		// Run javascript command to close create new account form after 5 seconds
		jsExecutor.executeScript("setTimeout(() => arguments[0].click(),5000)", driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")));
		
		// Wait for create form disappear
		explicitWait.until(
				ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector("input[name='reg_email_confirmation__']"))));
	}

	@AfterClass
	public void afterClass() {
		// Close browser (All websites)
		driver.quit();
	}
}
