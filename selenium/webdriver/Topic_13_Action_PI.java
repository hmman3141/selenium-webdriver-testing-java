package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Action_PI {
	WebDriver driver;
	Actions action;
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
		action = new Actions(driver);
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
	public void TC_01_Hover() {
		// Open website
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		
		// Move pointer to element
		action.moveToElement(driver.findElement(By.xpath("//a[text()='Tooltips']"))).perform();
		waitForSecond(1);
		
		// Verify content
		Assert.assertTrue(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText().contains("That's what this widget is"));
		
		// Move pointer to element
		action.moveToElement(driver.findElement(By.xpath("//a[text()='ThemeRoller']"))).perform();
		waitForSecond(1);
		
		// Verify content
		Assert.assertTrue(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText().contains("ThemeRoller: jQuery UI's theme builder application"));
		
		// Move pointer to element
		action.moveToElement(driver.findElement(By.id("age"))).perform();
		waitForSecond(1);
		
		// Verify content
		Assert.assertTrue(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText().contains("We ask for your age only for statistical purposes."));
	}

	@AfterClass
	public void afterClass() {
		// Close browser (All websites)
		driver.quit();
	}
}
