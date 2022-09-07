package webdriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_IFrame_Frame {
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
	public void TC_01_IFrame_Kyna() {
		// Open website
		driver.get("https://kyna.vn/");
		
		// Declaration
		String like = "166K likes";
		String name= "automationfc";
		String phone = "09876543210";
		String service = "TƯ VẤN TUYỂN SINH";
		String search = "Excel";
		
		// Switch to frame
		driver.switchTo().frame(driver.findElement(By.cssSelector("div.face-content>iframe")));
		
		// Verify facebook like
		Assert.assertEquals(driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText(), like);
		
		// Switch to default frame
		driver.switchTo().defaultContent();
		
		// Switch to customer service frame
		driver.switchTo().frame("cs_chat_iframe");
		
		// Click to open customer service
		driver.findElement(By.xpath("//div[contains(@class,'meshim_widget_components_ChatButton')]/parent::div/parent::div")).click();
		
		// Send key
		driver.findElement(By.cssSelector("input.input_name")).sendKeys(name);
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys(phone);
		new Select(driver.findElement(By.id("serviceSelect"))).selectByVisibleText(service);
		driver.findElement(By.cssSelector("textarea[name='message']")).sendKeys(phone);
		
		// Switch to default frame
		driver.switchTo().defaultContent();
		
		// Send keys and click search button
		driver.findElement(By.cssSelector("input#live-search-bar")).sendKeys(search);
		driver.findElement(By.cssSelector("button.search-button")).click();
		
		// Assert number of returning value
		List<WebElement> listOfElements = driver.findElements(By.cssSelector("div.content>h4"));
		Assert.assertEquals(listOfElements.size(), 9);
		
		// Assert list of items contain 'Excel'
		for (WebElement webElement : listOfElements) {
			Assert.assertTrue(webElement.getText().contains(search));
		}
	}

	@Test
	public void TC_02_HDFCBanking() {
		// Open website
		driver.get("https://netbanking.hdfcbank.com/netbanking/");
		
		// Declaration
		String userID = "12345";
		
		// Switch to frame
		driver.switchTo().frame("login_page");
		
		// Send keys
		driver.findElement(By.cssSelector("input[name='fldLoginUserId']")).sendKeys(userID);
		driver.findElement(By.xpath("//div[contains(@class,'inputfield')]/a[text()='CONTINUE']")).click();
		waitForSecond(2);
		
		// Verify password textbox is displayed
		Assert.assertTrue(driver.findElement(By.cssSelector("input#fldPasswordDispId")).isDisplayed());
	}

	@AfterClass
	public void afterClass() {
		// Close browser (All websites)
		driver.quit();
	}
}
