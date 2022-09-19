package webdriver;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_23_Fluent_Wait {
	WebDriver driver;
	FluentWait<WebDriver> fluentDriver;
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
		fluentDriver = new FluentWait<WebDriver>(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_AutomationFC_FluentWait() {
		// Open website
		driver.get("https://automationfc.github.io/fluent-wait/");

		// Declaration
		long time = 15;
		long polling = 100;

		// Set fluent wait
		fluentDriver.withTimeout(Duration.ofSeconds(time)).pollingEvery(Duration.ofMillis(polling))
				.ignoring(NoSuchElementException.class);
		fluentDriver.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				WebElement countdown = driver.findElement(By.cssSelector("div#javascript_countdown_time"));
				if (countdown.getText().endsWith("00"))
					return countdown;
				return null;
			}
		});
	}

	@Test
	public void TC_02_AutomationFC_DynamicLoading() {
		// Open website
		driver.get("https://automationfc.github.io/dynamic-loading/");

		// Click "Start" button
		driver.findElement(By.cssSelector("div#start>button")).click();

		// Declaration
		long time = 15;
		long polling = 100;

		// Set fluent wait
		fluentDriver.withTimeout(Duration.ofSeconds(time)).pollingEvery(Duration.ofMillis(polling))
				.ignoring(NoSuchElementException.class);
		fluentDriver.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				WebElement content = driver.findElement(By.cssSelector("div#finish>h4"));
				if (content.getText().contains("Hello World!"))
					return content;
				return null;
			}
		});
	}

	@AfterClass
	public void afterClass() {
		// Close browser (All websites)
		driver.quit();
	}
}
