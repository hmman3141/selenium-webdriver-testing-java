package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_P2 {
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

		// Open website
		driver.get("http://live.techpanda.org/");
	}

	@Test
	public void TC_01_Url_Verification() {
		// Click MY ACCOUNT link in footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// Get and verify URL
		String currentUrl = driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, "http://live.techpanda.org/index.php/customer/account/login/");

		// Click CREATE AN ACCOUNT button
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		// Get and verify URL
		currentUrl = driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, "http://live.techpanda.org/index.php/customer/account/create/");
	}

	@Test
	public void TC_02_Title_Verification() {
		// Go back to main page
		driver.navigate().to("http://live.techpanda.org/");

		// Click MY ACCOUNT link in footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// Get and verify title
		String currentTitle = driver.getTitle();
		Assert.assertEquals(currentTitle, "Customer Login");

		// Click CREATE AN ACCOUNT button
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		// Get and verify title
		currentTitle = driver.getTitle();
		Assert.assertEquals(currentTitle, "Create New Customer Account");
	}

	@Test
	public void TC_03_Navigation() {
		// Go back to main page
		driver.navigate().to("http://live.techpanda.org/");

		// Click MY ACCOUNT link in footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		// Click CREATE AN ACCOUNT button
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		// Get and verify URL
		String currentUrl = driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, "http://live.techpanda.org/index.php/customer/account/create/");

		// Go back to login page
		driver.navigate().back();

		// Get and verify URL
		currentUrl = driver.getCurrentUrl();
		Assert.assertEquals(currentUrl, "http://live.techpanda.org/index.php/customer/account/login/");

		// Forward to register page
		driver.navigate().forward();

		// Get and verify title
		String currentTitle = driver.getTitle();
		Assert.assertEquals(currentTitle, "Create New Customer Account");
	}

	@Test
	public void TC_04_Get_Source_Code() {
		// Go back to main page
		driver.navigate().to("http://live.techpanda.org/");

		// Click MY ACCOUNT link in footer
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		// Verify if page's source code contains String
		Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));

		// Click CREATE AN ACCOUNT button
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		// Verify if page's source code contains String
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
	}

	@AfterClass
	public void afterClass() {
		// Close browser (All websites)
		driver.quit();
	}
}
