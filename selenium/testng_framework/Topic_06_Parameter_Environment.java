package testng_framework;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Topic_06_Parameter_Environment {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");

	@Parameters("environment")
	@Test
	public void TC_01(String environment) {
		switch (environment) {
		case "dev":
			driver.get("http://dev.techpanda.org/");
			break;

		case "test":
			driver.get("http://test.techpanda.org/");
			break;
			
		case "live":
			driver.get("http://live.techpanda.org/");
			break;

		default:
			break;
		}
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
