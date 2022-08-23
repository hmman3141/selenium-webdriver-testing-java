package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser_P1 {
	WebDriver driver;
	WebElement element;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();

		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}

	@Test
	public void TC_01_Browser() {
		// Open website
		driver.get("url");
		
		// Get the current url
		String url = driver.getCurrentUrl();
		
		// Get list of elements
		List<WebElement> list = driver.findElements(By.cssSelector(""));
		
		// Get 1 element
		WebElement ele = driver.findElement(By.cssSelector(""));
		
		// Get logs
		Set<String> listLogs = driver.manage().logs().getAvailableLogTypes();
		
		// Actions that relate to the cookies
		driver.manage().deleteAllCookies();
		Set<Cookie> cookies = driver.manage().getCookies();
		
		// Actions that change url and can track the history of url
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().to("");
		
		// Actions that relate to window size and timeouts
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
		
		// Actions ...
		driver.switchTo().alert();
		driver.switchTo().frame(0);
		driver.switchTo().activeElement();
		
		// Close 1 website
		driver.close();
	}
	
	@Test
	public void TC_01_Element() {
		// Delete element's text
		element.clear();
		
		// For element that can be clicked (button, link, ...) 
		element.click();
		
		// Get attribute of element
		String eleAtt = element.getAttribute("");
		
		// Get location of element
		Point elePoint = element.getLocation();
		
		// Get rectangle of element
		Rectangle eleRect = element.getRect();
		
		// Get dimension of element
		Dimension eleDim = element.getSize();
		
		// Get tag name of element | Ex: input[id='123'] -> tag name = input
		String eleTagName = element.getTagName();
		
		// Get text of element | Ex: <h1>abc</h1> -> text = abc
		String eleText = element.getText();
		
		// Screenshot and attach to HTML report
		element.getScreenshotAs(OutputType.FILE);
		
		// Check if element is displayed in the website (user can see it from the screen)
		element.isDisplayed();
		
		// Check if element can be interacted
		// disable or not
		element.isEnabled();
		
		// Check if element is on | Ex: user select a check box, ...
		element.isSelected();
	}

	@AfterClass
	public void afterClass() {
		// Close browser (All websites)
		driver.quit();
	}
}
