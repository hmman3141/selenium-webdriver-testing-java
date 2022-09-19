package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_21_Implicit_Wait {
	WebDriver driver;
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

		// Set implicitly wait
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		// Click "Start" button
		driver.findElement(By.cssSelector("div#start>button")).click();

		// Wait for text
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), text);
	}

	@Test
	public void TC_02_Plenty_Of_Time() {
		// Open website
		driver.get("https://automationfc.github.io/dynamic-loading/");
		
		// Declaration
		String text = "Hello World!";
		
		// Set implicitly wait
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// Click "Start" button
		driver.findElement(By.cssSelector("div#start>button")).click();
		
		// Wait for text
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), text);
	}

	@Test
	public void TC_03_Not_Enough_Time() {
		// Open website
		driver.get("https://automationfc.github.io/dynamic-loading/");

		// Declaration
		String text = "Hello World!";

		// Set implicitly wait
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);

		// Click "Start" button
		driver.findElement(By.cssSelector("div#start>button")).click();

		// Wait for text
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), text);
	}

	@AfterClass
	public void afterClass() {
		// Close browser (All websites)
		driver.quit();
	}
}
