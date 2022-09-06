package webdriver;

import java.util.List;
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

public class Topic_14_Fixed_Popup {
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
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
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
	public void TC_01_Fixed_In_DOM_NgoaiNgu24h() {
		// Open website
		driver.get("https://ngoaingu24h.vn/");

		// Declaration
		WebElement loginPopUp = driver.findElement(By.cssSelector("div#modal-login-v1"));
		String account = "automationfc";
		String password = "automationfc";
		String error = "Tài khoản không tồn tại!";

		// Verify login popup isn't displayed
		Assert.assertFalse(loginPopUp.isDisplayed());

		// Click login button
		driver.findElement(By.xpath("//div[@id='button-login-dialog']/button[text()='Đăng nhập']")).click();
		waitForSecond(1);

		// Verify login popup is displayed
		Assert.assertTrue(loginPopUp.isDisplayed());

		// Send key
		driver.findElement(By.cssSelector("input#account-input")).sendKeys(account);
		driver.findElement(By.cssSelector("input#password-input")).sendKeys(password);
		driver.findElement(By.xpath("//div[@class='modal fade in']//button[contains(@class,'btn-login-v1')]")).click();

		// Verify error
		Assert.assertEquals(
				driver.findElement(By.xpath("//div[@class='modal fade in']//div[contains(@class,'error-login-panel')]"))
						.getText(),
				error);
	}

	@Test
	public void TC_02_Fixed_In_DOM_Kyna() {
		// Open website
		driver.get("https://kyna.vn/");

		// Declaration
		WebElement loginPopUp = driver.findElement(By.cssSelector("div#k-popup-account-login"));
		String account = "automationfc@gmail.com";
		String password = "automationfc";
		String error = "Sai tên đăng nhập hoặc mật khẩu";

		// Click login button
		driver.findElement(By.cssSelector("a.login-btn")).click();
		waitForSecond(1);

		// Verify login popup is displayed
		Assert.assertTrue(loginPopUp.isDisplayed());

		// Send key
		driver.findElement(By.cssSelector("input#user-login")).sendKeys(account);
		driver.findElement(By.cssSelector("input#user-password")).sendKeys(password);
		driver.findElement(By.cssSelector("button#btn-submit-login")).click();
		waitForSecond(1);

		// Verify error
		Assert.assertEquals(driver.findElement(By.cssSelector("div#password-form-login-message")).getText(), error);

		// Close login popup
		driver.findElement(By.cssSelector("button.k-popup-account-close.close")).click();
		waitForSecond(1);

		// Verify login popup isn't displayed
		Assert.assertFalse(loginPopUp.isDisplayed());
	}

	@Test
	public void TC_03_Random_In_DOM_KMPlayer() {
		// Open website
		driver.get("https://www.kmplayer.com/home");

		if (driver.findElement(By.cssSelector("div#layer2")).isDisplayed()) {
			jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.cssSelector("area#btn-r")));
			waitForSecond(2);
		}

		// Verify popup isn't displayed
		Assert.assertFalse(driver.findElement(By.cssSelector("div#layer2")).isDisplayed());

		// Click donate button
		driver.findElement(By.cssSelector("p.donate-coffee")).click();

		// Verify current url
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.buymeacoffee.com/kmplayer?ref=hp&kind=top");
	}

	@Test
	public void TC_04_Random_Not_In_DOM_KMPlayer() {
		// Open website
		driver.get("https://dehieu.vn/");

		// Declaration
		List<WebElement> listOfElement = driver.findElements(By.id("popup"));

		if (listOfElement.size() != 0) {
			jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.cssSelector("button#close-popup")));
			waitForSecond(2);
		}
		
		// Verify popup is not displayed
		listOfElement = driver.findElements(By.id("popup"));
		Assert.assertEquals(listOfElement.size(), 0);
		
		// Click button
		driver.findElement(By.xpath("//a[text()='Tất cả khóa học']")).click();
		
		// Verify current url
		Assert.assertEquals(driver.getCurrentUrl(), "https://dehieu.vn/khoa-hoc");
	}

	@AfterClass
	public void afterClass() {
		// Close browser (All websites)
		driver.quit();
	}
}
