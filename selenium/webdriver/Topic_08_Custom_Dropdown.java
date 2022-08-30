package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Custom_Dropdown {
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

	private void waitForSecond(int milisecond) {
		try {
			Thread.sleep(milisecond * 1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void selectValueFromCustomDropdown(String parentCss, String childCss, String value) {
		// Click custom dropdown
		driver.findElement(By.cssSelector(parentCss)).click();

		// Wait until the condition is met (at least 1 element is located)
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childCss)));

		// If the element is found, click it
		List<WebElement> listOfElements = driver.findElements(By.cssSelector(childCss));
		for (WebElement element : listOfElements) {
			if (element.getText().equals(value)) {
				element.click();
				break;
			}
		}
	}

	private void scrollToElement(String cssLocator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.cssSelector(cssLocator)));
	}

	private void editValueFromCustomDropdown(String parentLocator, String childLocator, String value, String key) {
		// Edit custom dropdown
		driver.findElement(By.cssSelector(parentLocator)).sendKeys(key);

		// Wait until the condition is met (at least 1 element is located)
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));

		// If the element is found, click it
		List<WebElement> listOfElements = driver.findElements(By.cssSelector(childLocator));
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
		Assert.assertEquals(
				driver.findElement(By.cssSelector("span#salutation-button span.ui-selectmenu-text")).getText(),
				salutation);
	}

	@Test
	public void TC_02_Honda() {
		// Open website
		driver.get("https://www.honda.com.vn/o-to/du-toan-chi-phi");

		// Declaration
		String motobike = "CITY L";
		String province = "Cần Thơ";
		String registrationFee = "Khu vực II";
		Select provinceSelect = new Select(driver.findElement(By.id("province")));
		Select registrationFeeSelect = new Select(driver.findElement(By.id("registration_fee")));

		// Scroll to element
		scrollToElement("div.carousel-item.active.home-page-slide-top");

		// Wait for 2 seconds
		waitForSecond(2);

		// Select motobike from motobike custom dropdown
		selectValueFromCustomDropdown("button#selectize-input", "button#selectize-input+div>a", motobike);

		// Scroll to element
		scrollToElement("div.choose-car.d-flex");

		// Wait for 2 seconds
		waitForSecond(2);

		// Select value from default dropdown
		provinceSelect.selectByVisibleText(province);
		registrationFeeSelect.selectByVisibleText(registrationFee);

		// Verification
		Assert.assertEquals(driver.findElement(By.id("selectize-input")).getText(), motobike);
		Assert.assertEquals(provinceSelect.getFirstSelectedOption().getText(), province);
		Assert.assertEquals(registrationFeeSelect.getFirstSelectedOption().getText(), registrationFee);
	}

	@Test
	public void TC_03_React() {
		// Open website
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		// Declaration
		String name = "Justen Kitsune";

		// Select value from custom dropdown
		selectValueFromCustomDropdown("div.selection.dropdown", "div.visible.menu>div>span", name);

		// Verification
		Assert.assertEquals(driver.findElement(By.cssSelector("div.selection.dropdown>div.text")).getText(), name);

		name = "Elliot Fu";

		// Select value from custom dropdown
		selectValueFromCustomDropdown("div.selection.dropdown", "div.visible.menu>div>span", name);

		// Verification
		Assert.assertEquals(driver.findElement(By.cssSelector("div.selection.dropdown>div.text")).getText(), name);
	}

	@Test
	public void TC_04_VueJS() {
		// Open website
		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		// Declaration
		String option = "First Option";

		// Select value from custom dropdown
		selectValueFromCustomDropdown("li.dropdown-toggle", "ul.dropdown-menu>li>a", option);

		// Verification
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), option);

		option = "Second Option";

		// Select value from custom dropdown
		selectValueFromCustomDropdown("li.dropdown-toggle", "ul.dropdown-menu>li>a", option);

		// Verification
		Assert.assertEquals(driver.findElement(By.cssSelector("li.dropdown-toggle")).getText(), option);
	}

	@Test
	public void TC_05_React_Editable() {
		// Open website
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");

		// Declaration
		String country = "Belize";
		String key = "Bel";

		// Select value from custom dropdown
		editValueFromCustomDropdown("input.search", "div.visible.menu>div", country, key);

		// Verification
		Assert.assertEquals(driver.findElement(By.cssSelector("input.search+div.text")).getText(), country);
		
		// Fail scenario
		country = "Aland Islands";
		
		// Select value from custom dropdown
		editValueFromCustomDropdown("input.search", "div.visible.menu>div", country, key);
		
		// Verification
		Assert.assertEquals(driver.findElement(By.cssSelector("input.search+div.text")).getText(), "");
	}

	@AfterClass
	public void afterClass() {
		// Close browser (All websites)
		driver.quit();
	}
}
