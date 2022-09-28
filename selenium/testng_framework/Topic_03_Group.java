package testng_framework;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_03_Group {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@Test(groups = { "admin", "product" })
	public void TC_01() {

	}

	@Test(groups = { "product" })
	public void TC_02() {

	}

	@Test(groups = { "admin", "product" })
	public void TC_03() {

	}

	@Test(groups = { "product" })
	public void TC_04() {

	}

	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
		if (osName.contains("Mac")) {
			System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		}
		driver = new FirefoxDriver();
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.quit();
	}
}
