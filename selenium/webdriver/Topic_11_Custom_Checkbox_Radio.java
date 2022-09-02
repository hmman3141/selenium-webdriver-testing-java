package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Custom_Checkbox_Radio {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
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
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	private void onSelectValue(WebElement element) {
		if (!element.isSelected())
			jsExecutor.executeScript("arguments[0].click();", element);
	}

	private void onDeselectValue(WebElement element) {
		if (element.isSelected())
			jsExecutor.executeScript("arguments[0].click();", element);
	}

	@Test
	public void TC_01_Angular() {
		// Open website
		driver.get("https://material.angular.io/components/radio/examples");

		// Declaration
		WebElement summerRadio = driver
				.findElement(By.xpath("//span[contains(text(),'Summer')]/preceding-sibling::span/input"));

		// Select radio button named 'Summer'
		onSelectValue(summerRadio);

		// Verify radio button
		Assert.assertTrue(summerRadio.isSelected());

		// Open website
		driver.get("https://material.angular.io/components/checkbox/examples");

		// Declaration
		WebElement checkedCheckbox = driver
				.findElement(By.xpath("//span[contains(text(),'Checked')]/preceding-sibling::span/input"));

		// Select checkbox named 'Checked'
		onSelectValue(checkedCheckbox);

		// Verify checkbox
		Assert.assertTrue(checkedCheckbox.isSelected());

		// Deselect checkbox named 'Checked'
		onDeselectValue(checkedCheckbox);

		// Verify checkbox
		Assert.assertFalse(checkedCheckbox.isSelected());
	}

	@Test
	public void TC_02_GoogleForm() {
		// Open website
		driver.get(
				"https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");

		// Declaration
		WebElement canthoRadio = driver
				.findElement(By.xpath("//span[text()='Cần Thơ']/parent::div/parent::div/preceding-sibling::div/div"));

		// Verify button isn't checked
		Assert.assertEquals(canthoRadio.getAttribute("aria-checked"), "false");

		// Click radio button named Can Tho
		canthoRadio.click();

		// Verify button is checked
		Assert.assertEquals(canthoRadio.getAttribute("aria-checked"), "true");
	}

	@AfterClass
	public void afterClass() {
		// Close browser (All websites)
		driver.quit();
	}
}
