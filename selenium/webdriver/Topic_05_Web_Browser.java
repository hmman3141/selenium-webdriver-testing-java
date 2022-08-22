package webdriver;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Browser {
	WebDriver driver;
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
	public void TC_01_RegisterWithEmptyData() {
		WebElement Element = driver.findElement(By.cssSelector(""));
		
		driver.get("");
		
		String url = driver.getCurrentUrl();
		List<WebElement> list = driver.findElements(By.cssSelector(""));
		Set<String> listLogs = driver.manage().logs().getAvailableLogTypes();
		driver.manage().deleteAllCookies();
		
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().to("");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
		
		driver.switchTo().alert();
		driver.switchTo().frame(0);
		driver.switchTo().activeElement();
		
		driver.close();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
