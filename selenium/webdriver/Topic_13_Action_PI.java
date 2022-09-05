package webdriver;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_Action_PI {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	Actions action;
	Alert alert;
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

	private void scrollToElement(WebElement element) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	@Test
	public void TC_01_Hover() {
		// Open website
		driver.get("https://automationfc.github.io/jquery-tooltip/");

		// Move pointer to element
		action.moveToElement(driver.findElement(By.xpath("//a[text()='Tooltips']"))).perform();
		waitForSecond(1);

		// Verify content
		Assert.assertTrue(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText()
				.contains("That's what this widget is"));

		// Move pointer to element
		action.moveToElement(driver.findElement(By.xpath("//a[text()='ThemeRoller']"))).perform();
		waitForSecond(1);

		// Verify content
		Assert.assertTrue(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText()
				.contains("ThemeRoller: jQuery UI's theme builder application"));

		// Move pointer to element
		action.moveToElement(driver.findElement(By.id("age"))).perform();
		waitForSecond(1);

		// Verify content
		Assert.assertTrue(driver.findElement(By.cssSelector("div.ui-tooltip-content")).getText()
				.contains("We ask for your age only for statistical purposes."));
	}

	@Test
	public void TC_02_Hover() {
		// Open website
		driver.get("http://www.myntra.com/");

		// Declaration
		String title = "Kids Home Bath";

		// Move pointer to element
		action.moveToElement(
				driver.findElement(By.xpath("//a[text()='Kids']/parent::div/parent::div[@class='desktop-navContent']")))
				.perform();
		waitForSecond(1);

		// Click link to change page url
		driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();

		// Wait until element needs to be verified appear
		explicitWait.until(
				ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.title-container>h1.title-title")));
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("ul.breadcrumbs-list>li>span")));

		// Verify elements
		Assert.assertEquals(driver.findElement(By.cssSelector("div.title-container>h1.title-title")).getText(), title);
		Assert.assertEquals(driver.findElement(By.cssSelector("ul.breadcrumbs-list>li>span")).getText(), title);
	}

	@Test
	public void TC_04_Click_And_Hold() {
		// Open website
		driver.get("https://automationfc.github.io/jquery-selectable/");

		// Move pointer to element 1
		// Click mouse
		// Move pointer to element 4
		// Release mouse
		action.moveToElement(driver.findElement(By.xpath("//li[text()='1']"))).clickAndHold()
				.moveToElement(driver.findElement(By.xpath("//li[text()='4']"))).release().perform();

		// Verify selected element
		List<WebElement> selectedElements = driver.findElements(By.xpath("//li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(selectedElements.size(), 4);
	}

	@Test
	public void TC_05_Click_And_Select() {
		// Open website
		driver.get("https://automationfc.github.io/jquery-selectable/");

		// Declaration
		List<String> elements = Arrays.asList("1", "3", "6", "11");

		// Press ctrl to select multiple element
		action.keyDown(Keys.CONTROL).perform();

		// Click sequentially these elements: 1,3,6,11
		for (String string : elements) {
			String path = "//li[text()='" + string + "']";
			action.moveToElement(driver.findElement(By.xpath(path))).click().perform();
		}

		// Verify selected element
		List<WebElement> selectedElements = driver.findElements(By.xpath("//li[contains(@class,'ui-selected')]"));
		Assert.assertEquals(selectedElements.size(), 4);
	}

	@Test
	public void TC_06_Double_Click() {
		// Open website
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Declaration
		String doubleClickText = "Hello Automation Guys!";
		WebElement button = driver.findElement(By.xpath("//button[text()='Double click me']"));

		// Scroll to element
		scrollToElement(button);

		// Double click button
		action.doubleClick(button).perform();

		// Verify text
		Assert.assertEquals(driver.findElement(By.id("demo")).getText(), doubleClickText);
	}

	@Test
	public void TC_07_Right_Click() {
		// Open website
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

		// Right click button
		action.contextClick(driver.findElement(By.xpath("//span[text()='right click me']"))).perform();

		// Move pointer to element
		action.moveToElement(
				driver.findElement(By.xpath("//li[contains(@class,'context-menu-item')]/span[text()='Quit']")))
				.perform();

		// Verify text
		Assert.assertTrue(
				driver.findElement(By.xpath("//span[text()='Quit']/parent::li[contains(@class,'context-menu-item')]"))
						.getAttribute("class").contains("context-menu-hover"));
		Assert.assertTrue(
				driver.findElement(By.xpath("//span[text()='Quit']/parent::li[contains(@class,'context-menu-item')]"))
						.getAttribute("class").contains("context-menu-visible"));

		// Click
		action.click().perform();

		// Get alert
		alert = driver.switchTo().alert();

		// Verify alert
		Assert.assertEquals(alert.getText(), "clicked: quit");

		// Accept alert
		alert.accept();

		// Verify text
		Assert.assertFalse(
				driver.findElement(By.xpath("//span[text()='Quit']/parent::li[contains(@class,'context-menu-item')]"))
						.getAttribute("class").contains("context-menu-hover"));
		Assert.assertFalse(
				driver.findElement(By.xpath("//span[text()='Quit']/parent::li[contains(@class,'context-menu-item')]"))
						.getAttribute("class").contains("context-menu-visible"));
	}

	@AfterClass
	public void afterClass() {
		// Close browser (All websites)
		driver.quit();
	}
}
