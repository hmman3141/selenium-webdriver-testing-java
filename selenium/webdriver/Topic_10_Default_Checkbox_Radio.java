package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Default_Checkbox_Radio {
	WebDriver driver;
	WebDriverWait explicitWait;
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
		explicitWait = new WebDriverWait(driver, 30);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	private void selectValueFromCheckbox_Radio(WebElement element) {
		if (!element.isSelected())
			element.click();
	}

	private void deselectValueFromCheckbox(WebElement element) {
		if (element.isSelected())
			element.click();
	}

	private void selectAllValuesFromCheckbox(List<WebElement> listElement) {
		for (WebElement webElement : listElement) {
			if (!webElement.isSelected() && webElement.isEnabled()) {
				webElement.click();
			}
		}
	}

	private void deselectAllValuesFromCheckbox(List<WebElement> listElement) {
		for (WebElement webElement : listElement) {
			if (webElement.isSelected() && webElement.isEnabled()) {
				webElement.click();
			}
		}
	}
	
	private void scrollToElement(WebElement element) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	@Test
	public void TC_01_JotForm() {
		// Open website
		driver.get("https://automationfc.github.io/multiple-fields/");

		// Declaration
		WebElement cancerCheckbox = driver.findElement(By.xpath("//input[@value='Cancer']"));
		WebElement fivePlusRadio = driver.findElement(By.xpath("//input[@value='5+ days']"));
		WebElement threeToFourRadio = driver.findElement(By.xpath("//input[@value='3-4 days']"));

		// Select checkbox
		selectValueFromCheckbox_Radio(cancerCheckbox);
		// Verify checkbox is checked
		Assert.assertTrue(cancerCheckbox.isSelected());
		// Deselect checkbox
		deselectValueFromCheckbox(cancerCheckbox);
		// Verify checkbox isn't checked
		Assert.assertFalse(cancerCheckbox.isSelected());

		// Select 5+ radio
		selectValueFromCheckbox_Radio(fivePlusRadio);
		// Verify 5+ radio is checked
		Assert.assertTrue(fivePlusRadio.isSelected());
		// Select 3-4 radio
		selectValueFromCheckbox_Radio(threeToFourRadio);
		// Verify 5+ radio isn't checked
		Assert.assertFalse(fivePlusRadio.isSelected());
	}

	@Test
	public void TC_02_JotForm_SelectAll_DeselectAll() {
		// Open website
		driver.get("https://automationfc.github.io/multiple-fields/");

		// Declaration
		List<WebElement> checkboxList = driver.findElements(By.cssSelector("span.form-checkbox-item>input"));

		// Select all value from checkbox
		selectAllValuesFromCheckbox(checkboxList);

		// Deselect all value from checkbox
		deselectAllValuesFromCheckbox(checkboxList);
	}

	@Test
	public void TC_03_KendoUI() {
		// Open website
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");

		// Wait until locators is display
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("ul.fieldlist>li>input")));
		
		// Scroll into view
		scrollToElement(driver.findElement(By.cssSelector("div.tabstrip")));
		
		// Declaration
		List<WebElement> checkboxList = driver.findElements(By.cssSelector("ul.fieldlist>li>input"));

		// Select all value from checkbox
		selectAllValuesFromCheckbox(checkboxList);

		// Deselect all value from checkbox
		deselectAllValuesFromCheckbox(checkboxList);
	}

	@AfterClass
	public void afterClass() {
		// Close browser (All websites)
		driver.quit();
	}
}
