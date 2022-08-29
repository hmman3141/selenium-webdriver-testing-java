package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdown {
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
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	private void selectValueFromCustomDropdown(String parentCss, String childCss, String value) {
		// Click custom dropdown
		driver.findElement(By.cssSelector(parentCss)).click();

		// Wait until the condition is met (at least 1 element is located)
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(childCss)));

		// If the element is found, click it
		List<WebElement> listOfElements = driver.findElements(By.cssSelector(childCss));
		for (WebElement element : listOfElements) {
			if (element.getText().equals(value)) {
				element.click();
				break;
			}
		}
	}

	@Test
	public void TC_01_JQueryUI() {
		// Open website
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");

		// Declaration
		String speed = "Fast";
		String file = "ui.jQuery.js";
		String number = "19";
		String salutation = "Mrs.";

		// Select value from speed dropdown
		selectValueFromCustomDropdown("span#speed-button", "ul#speed-menu div", speed);
		
		// Select value from files dropdown
		selectValueFromCustomDropdown("span#files-button", "ul#files-menu div", file);
		
		// Select value from number dropdown
		selectValueFromCustomDropdown("span#number-button", "ul#number-menu div", number);
		
		// Select title from salutation dropdown
		selectValueFromCustomDropdown("span#salutation-button", "ul#salutation-menu div", salutation);

		// Verification
		Assert.assertEquals(driver.findElement(By.cssSelector("span#speed-button span.ui-selectmenu-text")).getText(),
				speed);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#files-button span.ui-selectmenu-text")).getText(),
				file);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#number-button span.ui-selectmenu-text")).getText(),
				number);
		Assert.assertEquals(driver.findElement(By.cssSelector("span#salutation-button span.ui-selectmenu-text")).getText(),
				salutation);
	}

	@AfterClass
	public void afterClass() {
		// Close browser (All websites)
		driver.quit();
	}
}
