package webdriver;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Default_Dropdown {
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

	private void waitForSecond(int milisecond) {
		try {
			Thread.sleep(milisecond * 1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void TC_01_NopCommerce() {
		// Open website
		driver.get("https://demo.nopcommerce.com/");

		// Declaration
		String firstName = "firstName";
		String lastName = "lastName";
		String password = "password";
		String day = "1";
		String month = "May";
		String year = "1980";
		String email = firstName + lastName;

		// Click register link
		driver.findElement(By.xpath("//a[text()='Register']")).click();

		// Wait for 3 seconds
		waitForSecond(3);

		// Fill all blank input
		driver.findElement(By.id("gender-male")).click();
		driver.findElement(By.id("FirstName")).sendKeys(firstName);
		driver.findElement(By.id("LastName")).sendKeys(lastName);
		driver.findElement(By.id("Password")).sendKeys(password);
		driver.findElement(By.id("ConfirmPassword")).sendKeys(password);
		// Default dropdown
		Select daySelect = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		daySelect.selectByVisibleText(day);
		Select monthSelect = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		monthSelect.selectByVisibleText(month);
		Select yearSelect = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		yearSelect.selectByVisibleText(year);
		// Random email generation
		Random rand = new Random();
		email += rand.nextInt(100000) + "@gmail.com";
		driver.findElement(By.id("Email")).sendKeys(email);

		// Click register button
		driver.findElement(By.id("register-button")).click();

		// Wait for 3 seconds
		waitForSecond(3);

		// Click my account link
		driver.findElement(By.xpath("//a[text()='My account']")).click();

		// Wait for 3 seconds
		waitForSecond(3);

		// Verification
		Assert.assertTrue(driver.findElement(By.id("gender-male")).isSelected());
		Assert.assertEquals(driver.findElement(By.id("FirstName")).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(By.id("LastName")).getAttribute("value"), lastName);
		Assert.assertEquals(driver.findElement(By.id("Email")).getAttribute("value"), email);
		// Default dropdown
		daySelect = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		Assert.assertEquals(daySelect.getFirstSelectedOption().getText(), day);
		monthSelect = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		Assert.assertEquals(monthSelect.getFirstSelectedOption().getText(), month);
		yearSelect = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		Assert.assertEquals(yearSelect.getFirstSelectedOption().getText(), year);
	}

	@Test
	public void TC_02_WhereToBuy() {
		// Open website
		driver.get("https://rode.com/en/support/where-to-buy");

		// Declaration
		String country = "Vietnam";
		Select countrySelect = new Select(driver.findElement(By.id("country")));

		// Check if dropdown is not multiple select dropdown
		Assert.assertFalse(countrySelect.isMultiple());

		// Choose value
		countrySelect.selectByVisibleText(country);

		// Wait for 3 seconds
		waitForSecond(3);

		// Verification
		Assert.assertEquals(countrySelect.getFirstSelectedOption().getText(), country);
		
		// Console out store value
		List<WebElement> listStore = driver.findElements(By.xpath("//i[contains(@class,'fa-store')]/parent::h4"));
		for(WebElement store: listStore) {
			System.out.println(store.getText());
		}
	}

	@AfterClass
	public void afterClass() {
		// Close browser (All websites)
		driver.quit();
	}
}
