package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Textbox_Textarea {
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

	public void clearWebField(WebElement element) {
		while (!element.getAttribute("value").equals("")) {
			element.sendKeys(Keys.BACK_SPACE);
		}
	}

	@Test
	public void TC_02_Textbox_Textarea() {
		// Declaration
		String firstName = "firstName";
		String lastName = "lastName";
		String firstNameEdit = "abc";
		String lastNameEdit = "def";
		String passportNumber = "774703475";
		String comment = "79 Madeira Way\\nMadeira Beach\\nFL 33708 USA";

		// Open website
		driver.get("https://opensource-demo.orangehrmlive.com/");

		// Login page
		driver.findElement(By.xpath("//input[contains(@class,'oxd-input') and @name='username']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[contains(@class,'oxd-input') and @name='password']")).sendKeys("admin123");
		driver.findElement(By.xpath("//button[contains(@class,'oxd-button')]")).click();

		// Wait for 3 seconds
		waitForSecond(3);

		// Go to Add employee page
		driver.findElement(By.xpath("//div[contains(@class,'orangehrm-header-container')]/button")).click();

		// Wait for 3 seconds
		waitForSecond(3);

		// Add 1 employee
		driver.findElement(By.xpath("//input[contains(@class,'oxd-input--active') and @name='firstName']"))
				.sendKeys(firstName);
		driver.findElement(By.xpath("//input[contains(@class,'oxd-input--active') and @name='lastName']"))
				.sendKeys(lastName);
		String employeeID = driver
				.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"))
				.getAttribute("value");
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		// Wait for 7 seconds
		waitForSecond(7);

		// Check employee information
		String actualFirstName = driver
				.findElement(By.xpath("//input[contains(@class,'oxd-input--active') and @name='firstName']"))
				.getAttribute("value");
		Assert.assertEquals(actualFirstName, firstName);

		String actualLastName = driver
				.findElement(By.xpath("//input[contains(@class,'oxd-input--active') and @name='lastName']"))
				.getAttribute("value");
		Assert.assertEquals(actualLastName, lastName);

		String actualEmployeeID = driver
				.findElement(By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input"))
				.getAttribute("value");
		Assert.assertEquals(actualEmployeeID, employeeID);

		// Edit employee information
		// firstName
		WebElement firstNameInput = driver
				.findElement(By.xpath("//input[contains(@class,'oxd-input--active') and @name='firstName']"));
		clearWebField(firstNameInput);
		firstNameInput.sendKeys(firstNameEdit);

		// lastName
		WebElement lastNameInput = driver
				.findElement(By.xpath("//input[contains(@class,'oxd-input--active') and @name='lastName']"));
		clearWebField(lastNameInput);
		lastNameInput.sendKeys(lastNameEdit);
		driver.findElement(By.xpath("//h6[text()='Personal Details']/following-sibling::form//button")).click();

		// Wait for 7 seconds
		waitForSecond(7);

		// Check employee information
		actualFirstName = driver
				.findElement(By.xpath("//input[contains(@class,'oxd-input--active') and @name='firstName']"))
				.getAttribute("value");
		Assert.assertEquals(actualFirstName, firstNameEdit);

		actualLastName = driver
				.findElement(By.xpath("//input[contains(@class,'oxd-input--active') and @name='lastName']"))
				.getAttribute("value");
		Assert.assertEquals(actualLastName, lastNameEdit);

		// Immigration tab
		driver.findElement(By.xpath("//a[text()='Immigration']")).click();

		// Wait for 5 seconds
		waitForSecond(5);

		// Click add button (Assigned Immigration Records)
		driver.findElement(By.xpath("//h6[text()='Assigned Immigration Records']/following-sibling::button")).click();

		// Wait for 1 seconds
		waitForSecond(1);

		// Add Immigration page
		driver.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input"))
				.sendKeys(passportNumber);
		driver.findElement(By.xpath("//textarea[@placeholder='Type Comments here']")).sendKeys(comment);
		driver.findElement(By.xpath("//button[@type='submit']")).click();

		// Wait for 5 seconds
		waitForSecond(5);

		// Edit first record
		driver.findElement(By.xpath(
				"(//div[@class='oxd-table-row oxd-table-row--with-border']//i[@class='oxd-icon bi-pencil-fill']/parent::button)[1]"))
				.click();

		// Wait for 3 seconds
		waitForSecond(3);

		// Verification
		// passport
		String actualPassport = driver
				.findElement(By.xpath("//label[text()='Number']/parent::div/following-sibling::div/input"))
				.getAttribute("value");
		Assert.assertEquals(actualPassport, passportNumber);
		// comment
		String actualComment = driver.findElement(By.xpath("//textarea[@placeholder='Type Comments here']")).getAttribute("value");
		Assert.assertEquals(actualComment, comment);
	}

	@AfterClass
	public void afterClass() {
		// Close browser (All websites)
		driver.quit();
	}
}
