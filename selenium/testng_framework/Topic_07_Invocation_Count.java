package testng_framework;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Invocation_Count {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@Test(invocationCount = 3)
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

		// Click logout button
		driver.findElement(By.xpath("//a[text()='Log out']")).click();
	}

	@BeforeClass(alwaysRun = true)
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

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.quit();
	}
}
