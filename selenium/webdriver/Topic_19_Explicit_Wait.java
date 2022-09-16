package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_19_Explicit_Wait {
	WebDriver driver;
	WebDriverWait explicitWait;
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
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Enough_Time() {
		// Open website
		driver.get("https://automationfc.github.io/dynamic-loading/");

		// Declaration
		String text = "Hello World!";

		// Set explicitly wait
		explicitWait = new WebDriverWait(driver, 5);

		// Click "Start" button
		driver.findElement(By.cssSelector("div#start>button")).click();

		// Wait for text
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#finish>h4")));

		// Verify text
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), text);
	}

	@Test
	public void TC_02_Plenty_Of_Time() {
		// Open website
		driver.get("https://automationfc.github.io/dynamic-loading/");

		// Declaration
		String text = "Hello World!";

		// Set explicitly wait
		explicitWait = new WebDriverWait(driver, 10);

		// Click "Start" button
		driver.findElement(By.cssSelector("div#start>button")).click();

		// Wait for text
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#finish>h4")));

		// Verify text
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), text);
	}

	@Test
	public void TC_03_Not_Enough_Time() {
		// Open website
		driver.get("https://automationfc.github.io/dynamic-loading/");

		// Declaration
		String text = "Hello World!";

		// Set explicitly wait
		explicitWait = new WebDriverWait(driver, 2);

		// Click "Start" button
		driver.findElement(By.cssSelector("div#start>button")).click();

		// Wait for text
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#finish>h4")));

		// Verify text
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), text);
	}

	@AfterClass
	public void afterClass() {
		// Close browser (All websites)
		driver.quit();
	}
}
