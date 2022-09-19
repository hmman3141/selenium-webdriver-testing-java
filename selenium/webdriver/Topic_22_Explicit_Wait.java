package webdriver;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_22_Explicit_Wait {
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
	public void TC_01_Invisible() {
		// Open website
		driver.get("https://automationfc.github.io/dynamic-loading/");

		// Declaration
		String text = "Hello World!";

		// Set explicitly wait
		explicitWait = new WebDriverWait(driver, 5);

		// Click "Start" button
		driver.findElement(By.cssSelector("div#start>button")).click();

		// Wait for text
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("div#loading")));

		// Verify text
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), text);
	}

	@Test
	public void TC_02_Visible() {
		// Open website
		driver.get("https://automationfc.github.io/dynamic-loading/");

		// Declaration
		String text = "Hello World!";

		// Set explicitly wait
		explicitWait = new WebDriverWait(driver, 10);

		// Click "Start" button
		driver.findElement(By.cssSelector("div#start>button")).click();

		// Wait for text
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#finish>h4")));

		// Verify text
		Assert.assertEquals(driver.findElement(By.cssSelector("div#finish>h4")).getText(), text);
	}

	@Test
	public void TC_03_Telerik() {
		// Open website
		driver.get(
				"https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

		// Declaration
		String day = "19";

		// Set explicitly wait
		explicitWait = new WebDriverWait(driver, 10);

		// Wait for table
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.calendarContainer")));
		explicitWait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//table[contains(@id,'RadCalendar')]//a[text()='19']")));

		// Click "19" button
		driver.findElement(By.xpath("//table[contains(@id,'RadCalendar')]//a[text()='19']")).click();

		// Wait for waiting icon disappear
		explicitWait.until(
				ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector("div[id*='RadCalendar'] div.raDiv"))));

		// Verify text
		Assert.assertTrue(driver.findElement(By.cssSelector("div.datesContainer span[id*='ContentPlaceholder']"))
				.getText().contains(day));
	}

	@Test
	public void TC_04_GoFile() {
		// Open website
		driver.get("https://gofile.io/uploadFiles");

		// Declaration
		String imgFolderPath = projectPath + File.separator + "img" + File.separator;
		List<String> files = Arrays.asList(imgFolderPath + "img1.jpg", imgFolderPath + "img2.jpg",
				imgFolderPath + "img3.jpg");
		String filePath = "";

		// Set explicitly wait
		explicitWait = new WebDriverWait(driver, 10);

		// Wait for button to be visible
		explicitWait.until(ExpectedConditions
				.visibilityOfElementLocated(By.cssSelector("div#rowUploadButton button[class*='uploadButton']")));

		// Send files
		filePath = String.join("\n", files);
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(filePath);

		// Wait for "Show files" appear
		explicitWait.until(
				ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button#rowUploadSuccess-showFiles")));

		// Click "Show files" button
		driver.findElement(By.cssSelector("button#rowUploadSuccess-showFiles")).click();

		// Wait for all files to appear
		explicitWait.until(ExpectedConditions
				.visibilityOfAllElements(driver.findElements(By.cssSelector("div#rowFolder-tableContent>div"))));

		// Verify number of files
		Assert.assertEquals(driver.findElements(By.cssSelector("div#rowFolder-tableContent>div")).size(), 3);
	}

	@AfterClass
	public void afterClass() {
		// Close browser (All websites)
		driver.quit();
	}
}
