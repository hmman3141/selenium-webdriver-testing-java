package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Button {
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

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Fahasa() {
		// Open website
		driver.get("https://www.fahasa.com/customer/account/create");

		// Declaration
		String username = "a@yahoo.com";
		String password = "123456";
		String hexCode = "#c92127";

		// Go to login page
		driver.findElement(By.cssSelector("ul#popup-login-tab_list>li.popup-login-tab-login>a")).click();

		// Verify disable login button
		Assert.assertFalse(driver.findElement(By.xpath("//button[@class='fhs-btn-login']")).isEnabled());

		// Send value
		driver.findElement(By.id("login_username")).sendKeys(username);
		driver.findElement(By.id("login_password")).sendKeys(password);

		// Verify enable login button
		Assert.assertTrue(driver.findElement(By.cssSelector("button.fhs-btn-login")).isEnabled());
		
		// Verify login button color
		String backgroundColor = driver.findElement(By.cssSelector("button.fhs-btn-login")).getCssValue("background-color");
		String hexCodeActual = Color.fromString(backgroundColor).asHex();
		Assert.assertEquals(hexCodeActual, hexCode);
	}

	@AfterClass
	public void afterClass() {
		// Close browser (All websites)
		driver.quit();
	}
}
