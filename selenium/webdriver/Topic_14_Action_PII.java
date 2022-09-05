package webdriver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Action_PII {
	WebDriver driver;
	Actions action;
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
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	private String getContentFile(String filePath) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(filePath);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}
	
	private void waitForSecond(int milisecond) {
		try {
			Thread.sleep(milisecond * 1000);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Test
	public void TC_01_Drag_And_Drop_HTML4() {
		// Open website
		driver.get("https://automationfc.github.io/kendo-drag-drop/");

		// Declaration
		String color = "#03A9F4";

		// Drag and drop
		action.dragAndDrop(driver.findElement(By.id("draggable")), driver.findElement(By.id("droptarget"))).perform();

		// Verify
		Assert.assertEquals(driver.findElement(By.id("droptarget")).getText(), "You did great!");
		Assert.assertEquals(Color.fromString(driver.findElement(By.id("droptarget")).getCssValue("background-color"))
				.asHex().toUpperCase(), color);
	}

	@Test
	public void TC_02_Drag_And_Drop_HTML5() throws IOException {
		driver.get("http://the-internet.herokuapp.com/drag_and_drop");

		// Declaration
		String sourceCss = "#column-a";
		String targetCss = "#column-b";
		String javascriptPath = projectPath;
		if (osName.contains("Mac")) {
			javascriptPath += "/dragAndDrop/dragAndDrop.js";
		} else {
			javascriptPath += "\\dragAndDrop\\dragAndDrop.js";
		}
		
		String java_script = getContentFile(javascriptPath);

		// Inject Jquery lib to site
		// String jqueryscript = readFile(jqueryPath);
		// javascriptExecutor.executeScript(jqueryscript);

		// A to B
		java_script += "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
		jsExecutor.executeScript(java_script);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());
		waitForSecond(1);

		// B to A
		jsExecutor.executeScript(java_script);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='B']")).isDisplayed());
		waitForSecond(1);
	}

	@AfterClass
	public void afterClass() {
		// Close browser (All websites)
		driver.quit();
	}
}
