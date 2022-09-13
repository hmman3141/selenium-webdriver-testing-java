package webdriver;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_18_Upload_File {
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

	@Test
	public void TC_01_1_File_Per_Time() {
		// Open website
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		// Declaration
		String imgFolderPath = projectPath + File.separator + "img" + File.separator;
		List<String> imgArray = Arrays.asList("img1.jpg", "img2.jpg", "img3.jpg");

		// Upload images
		for (String img : imgArray) {
			driver.findElement(By.cssSelector("input[type='file']")).sendKeys(imgFolderPath + img);
		}

		// Press 'start' button
		List<WebElement> startedButtons = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement startedButton : startedButtons) {
			startedButton.click();
		}
		waitForSecond(4);

		// Verify of file link
		List<WebElement> fileLinks = driver.findElements(By.cssSelector("p.name a"));
		for (WebElement fileLink : fileLinks) {
			Assert.assertTrue(imgArray.indexOf(fileLink.getText()) != -1);
		}
	}

	@Test
	public void TC_01_Multiple_Files_Per_Time() {
		// Open website
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		// Declaration
		String imgFolderPath = projectPath + File.separator + "img" + File.separator;
		List<String> imgArray = Arrays.asList("img1.jpg", "img2.jpg", "img3.jpg");
		List<String> imgPathArray = Arrays.asList(imgFolderPath + "img1.jpg", imgFolderPath + "img2.jpg",
				imgFolderPath + "img3.jpg");

		// Upload images
		driver.findElement(By.cssSelector("input[type='file']")).sendKeys(String.join("\n", imgPathArray));

		// Press 'start' button
		List<WebElement> startedButtons = driver.findElements(By.cssSelector("table button.start"));
		for (WebElement startedButton : startedButtons) {
			startedButton.click();
		}
		waitForSecond(4);

		// Verify of file link
		List<WebElement> fileLinks = driver.findElements(By.cssSelector("p.name a"));
		for (WebElement fileLink : fileLinks) {
			Assert.assertTrue(imgArray.indexOf(fileLink.getText()) != -1);
		}
	}

	@AfterClass
	public void afterClass() {
		// Close browser (All websites)
		driver.quit();
	}
}
