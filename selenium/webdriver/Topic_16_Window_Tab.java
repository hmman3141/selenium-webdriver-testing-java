package webdriver;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Window_Tab {
	WebDriver driver;
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

	private void switchToWindow(String title) {
		Set<String> listWindows = driver.getWindowHandles();
		for (String windowID : listWindows) {
			driver.switchTo().window(windowID);
			if (driver.getTitle().equals(title))
				break;
		}
	}

	private void closeWindowExcept(String title) {
		Set<String> listWindows = driver.getWindowHandles();
		for (String windowID : listWindows) {
			driver.switchTo().window(windowID);
			if (!driver.getTitle().equals(title))
				driver.close();
		}
	}

	@Test
	public void TC_01_AutomationFC() {
		// Open website
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Declaration
		String parentTitle = driver.getTitle();

		// Click google link
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		waitForSecond(3);

		// Switch to google tab
		String title = "Google";
		switchToWindow(title);

		// Verify page title
		Assert.assertEquals(driver.getTitle(), title);

		// Switch to parent window
		switchToWindow(parentTitle);

		// Click facebook link
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		waitForSecond(3);

		// Switch to google tab
		title = "Facebook – log in or sign up";
		switchToWindow(title);

		// Verify page title
		Assert.assertEquals(driver.getTitle(), title);

		// Switch to parent window
		switchToWindow(parentTitle);

		// Click tiki link
		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		waitForSecond(3);

		// Switch to google tab
		title = "Tiki - Mua hàng online giá tốt, hàng chuẩn, ship nhanh";
		switchToWindow(title);

		// Verify page title
		Assert.assertEquals(driver.getTitle(), title);

		// Close all tabs except parent tab
		closeWindowExcept(parentTitle);

		// Switch to parent window
		switchToWindow(parentTitle);

		// Assert number of tabs and parent title
		Assert.assertEquals(driver.getWindowHandles().size(), 1);
		Assert.assertEquals(driver.getTitle(), parentTitle);
	}

	@Test
	public void TC_02_Kyna() {
		// Open website
		driver.get("https://kyna.vn/");

		// Declaration
		String parentTitle = driver.getTitle();

		// Scroll to footer (at the bottom of page)
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");

		// Click 'Danh sách khóa học' link after pressing ctrl to create new tab
		action.keyDown(Keys.CONTROL).click(driver.findElement(By.xpath("//a[text()='Danh sách khóa học']"))).perform();
		waitForSecond(3);

		// Switch to 'Danh sách khóa học' tab
		String title = "Tổng hợp Tất Cả Khóa Học Online mới nhất tại Kyna";
		switchToWindow(title);

		// Verify page title
		Assert.assertEquals(driver.getTitle(), title);

		// Switch to parent window
		switchToWindow(parentTitle);

		// Click 'Câu hỏi thường gặp' link after pressing ctrl to create new tab
		action.keyDown(Keys.CONTROL).click(driver.findElement(By.xpath("//a[text()='Câu hỏi thường gặp']"))).perform();
		waitForSecond(3);

		// Switch to 'Câu hỏi thường gặp' tab
		title = "Câu hỏi thường gặp - Kyna.vn";
		switchToWindow(title);

		// Verify page title
		Assert.assertEquals(driver.getTitle(), title);

		// Close all tabs except parent tab
		closeWindowExcept(parentTitle);

		// Switch to parent window
		switchToWindow(parentTitle);

		// Assert number of tabs and parent title
		Assert.assertEquals(driver.getWindowHandles().size(), 1);
		Assert.assertEquals(driver.getTitle(), parentTitle);
	}

	@Test
	public void TC_03_TechPanda() {
		// Open website
		driver.get("http://live.techpanda.org/");

		// Declaration
		String parentTitle = driver.getTitle();
		String successfulMessage = "The comparison list was cleared.";
		List<String> products = Arrays.asList("Samsung Galaxy", "Sony Xperia");

		// Click mobile link
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();

		// Click Add to compare button to add to compare list
		for (String product : products) {
			// //a[text()='product_name']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add
			// to Compare']
			String xpath = "//a[text()='" + product
					+ "']/parent::h2/following-sibling::div[@class='actions']//a[text()='Add to Compare']";
			driver.findElement(By.xpath(xpath)).click();
		}

		// Click compare button
		driver.findElement(By.cssSelector("button[title='Compare']")).click();
		waitForSecond(3);

		// Switch to compare window
		String title = "Products Comparison List - Magento Commerce";
		switchToWindow(title);

		// Verify title page
		Assert.assertEquals(driver.getTitle(), title);

		// Click 'Close Window' button
		driver.findElement(By.cssSelector("button[title='Close Window']")).click();

		// Switch to parent window
		switchToWindow(parentTitle);

		// Click 'Clear All' button
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();

		// Switch to alert
		alert = driver.switchTo().alert();

		// Accept alert
		alert.accept();
		waitForSecond(3);

		// Assert successful message
		Assert.assertEquals(driver.findElement(By.cssSelector("li.success-msg span")).getText(), successfulMessage);
	}

	@Test
	public void TC_04_Cambridge() {
		// Open website
		driver.get("https://dictionary.cambridge.org/vi/");

		// Declaration
		String error = "This field is required";

		// Click login button
		driver.findElement(By.xpath("//span[text()='Đăng nhập' and @class='tb']")).click();

		// Switch to login window
		switchToWindow("Login");

		// Click login button
		driver.findElement(By.cssSelector("input[type='submit'][value='Log in']")).click();

		// Verify error message
		Assert.assertEquals(driver.findElement(By.cssSelector("input[name='username']+span[role='alert']")).getText(),
				error);
		Assert.assertEquals(driver.findElement(By.cssSelector("input[name='password']+span[role='alert']")).getText(),
				error);
	}

	@AfterClass
	public void afterClass() {
		// Close browser (All websites)
		driver.quit();
	}
}
