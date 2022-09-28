package testng_framework;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_05_Parameter_Browser {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@Test
	public void f() {
	}

	@Parameters("browser")
	@BeforeClass(alwaysRun = true)
	public void beforeClass(String browser) {
		switch (browser) {
		case "Firefox":
			if (osName.contains("Mac")) {
				System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver.exe");
			} else {
				System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			}
			driver = new FirefoxDriver();
			break;

		case "Chrome":
			if (osName.contains("Mac")) {
				System.setProperty("webdriver.chrome.driver", projectPath + "/browserDrivers/chromedriver.exe");
			} else {
				System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			}
			driver = new ChromeDriver();
			break;

		default:
			break;
		}
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.quit();
	}

}
