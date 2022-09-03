package webdriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Alert {
	WebDriver driver;
	Alert alert;
	String projectPath = System.getProperty("user.dir");
	String firefoxAuthen, chromeAuthen;
	String osName = System.getProperty("os.name");

	@BeforeClass
	public void beforeClass() {
		if (osName.contains("Mac")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
			firefoxAuthen = projectPath + "/autoITScript/authen_firefox.exe";
			chromeAuthen = projectPath + "/autoITScript/authen_chrome.exe";
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			firefoxAuthen = projectPath + "\\autoITScript\\authen_firefox.exe";
			chromeAuthen = projectPath + "\\autoITScript\\authen_chrome.exe";
		}

		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	private void waitForSecond(int milisecond) {
		try {
			Thread.sleep(milisecond * 1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void TC_01_Accept_Alert() {
		// Open website
		driver.get("https://automationfc.github.io/basic-form/");

		// Click button to show accept alert
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

		// Declaration
		alert = driver.switchTo().alert();

		// Verify alert title
		Assert.assertEquals(alert.getText(), "I am a JS Alert");

		// Accept alert
		alert.accept();

		// Verify result text
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked an alert successfully");
	}

	@Test
	public void TC_02_Confirm_Alert() {
		// Open website
		driver.get("https://automationfc.github.io/basic-form/");

		// Click button to show confirm alert
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

		// Declaration
		alert = driver.switchTo().alert();

		// Verify alert title
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");

		// Dismiss alert
		alert.dismiss();

		// Verify result text
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Cancel");

		// Click button to show confirm alert
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

		// Dismiss alert
		alert.accept();

		// Verify result text
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You clicked: Ok");
	}

	@Test
	public void TC_03_Prompt_Alert() {
		// Open website
		driver.get("https://automationfc.github.io/basic-form/");

		// Click button to show prompt alert
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

		// Declaration
		alert = driver.switchTo().alert();
		String key = "Something";

		// Verify alert title
		Assert.assertEquals(alert.getText(), "I am a JS prompt");

		// Accept alert
		alert.sendKeys(key);
		alert.accept();

		// Verify result text
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: " + key);

		// Click button to show prompt alert
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

		// Dismiss alert
		alert.dismiss();

		// Verify result text
		Assert.assertEquals(driver.findElement(By.id("result")).getText(), "You entered: null");
	}

	@Test
	public void TC_04_Authentication_Alert() {
		// Open website
		driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");

		// Verify content
		Assert.assertTrue(driver.findElement(By.cssSelector("div.example>p")).getText()
				.contains("Congratulations! You must have the proper credentials."));
	}

	@Test
	public void TC_05_Authentication_Alert_AutoIT() throws IOException {
		// Run autoIT
		Runtime.getRuntime().exec(new String[] { firefoxAuthen, "admin", "admin" });
		
		// Open website
		driver.get("https://the-internet.herokuapp.com/basic_auth");

		// Wait for 5 seconds
		waitForSecond(5);

		// Verify content
		Assert.assertTrue(driver.findElement(By.cssSelector("div.example>p")).getText()
				.contains("Congratulations! You must have the proper credentials."));
	}

	@AfterClass
	public void afterClass() {
		// Close browser (All websites)
		driver.quit();
	}
}
