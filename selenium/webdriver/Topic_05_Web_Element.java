package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Web_Element {
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

		// Open website
		driver.get("https://automationfc.github.io/basic-form/index.html");
	}

	@Test
	public void TC_01_Check_Is_Displayed() {
		// Declaration
		WebElement emailInput = driver.findElement(By.id("mail"));
		WebElement under18Radio = driver.findElement(By.id("under_18"));
		WebElement eduTextArea = driver.findElement(By.id("edu"));
		WebElement user5Text = driver.findElement(By.xpath("//h5[text()='Name: User2']"));

		// isDisplayed verification
		Assert.assertTrue(emailInput.isDisplayed());
		Assert.assertTrue(under18Radio.isDisplayed());
		Assert.assertTrue(eduTextArea.isDisplayed());

		// isNotDisplayed verification
		Assert.assertTrue(!user5Text.isDisplayed());

		// Fill in the blank
		emailInput.sendKeys("Hua Minh Man");
		eduTextArea.sendKeys("Hua Minh Man");
		under18Radio.click();

		// check isDisplayed
		if (emailInput.isDisplayed()) {
			System.out.println("Email is displayed");
		} else {
			System.out.println("Email is not displayed");
		}

		if (under18Radio.isDisplayed()) {
			System.out.println("Under18 is displayed");
		} else {
			System.out.println("Under18 is not displayed");
		}

		if (eduTextArea.isDisplayed()) {
			System.out.println("Education is displayed");
		} else {
			System.out.println("Education is not displayed");
		}

		if (user5Text.isDisplayed()) {
			System.out.println("User5 is displayed");
		} else {
			System.out.println("User5 is not displayed");
		}

	}

	@Test
	public void TC_02_Check_Is_Enabled() {
		// Declaration
		// enable
		WebElement emailInput = driver.findElement(By.id("mail"));
		WebElement under18Radio = driver.findElement(By.id("under_18"));
		WebElement eduTextArea = driver.findElement(By.id("edu"));
		WebElement job1Dropdown = driver.findElement(By.id("job1"));
		WebElement job2Dropdown = driver.findElement(By.id("job2"));
		// disable
		WebElement passwordDisable = driver.findElement(By.id("disable_password"));
		WebElement ageRadioDisable = driver.findElement(By.id("radio-disabled"));
		WebElement bioDisable = driver.findElement(By.id("bio"));
		WebElement job3Disable = driver.findElement(By.id("job3"));
		WebElement checkDisable = driver.findElement(By.id("check-disbaled"));
		WebElement sliderDisable = driver.findElement(By.id("slider-2"));

		// isEnabled verification
		Assert.assertTrue(emailInput.isEnabled());
		Assert.assertTrue(under18Radio.isEnabled());
		Assert.assertTrue(eduTextArea.isEnabled());
		Assert.assertTrue(job1Dropdown.isEnabled());
		Assert.assertTrue(job2Dropdown.isEnabled());

		// isDisabled verification
		Assert.assertTrue(!passwordDisable.isEnabled());
		Assert.assertTrue(!ageRadioDisable.isEnabled());
		Assert.assertTrue(!bioDisable.isEnabled());
		Assert.assertTrue(!job3Disable.isEnabled());
		Assert.assertTrue(!checkDisable.isEnabled());
		Assert.assertTrue(!sliderDisable.isEnabled());

		// check isEnabled
		System.out.println("-- Check isEnabled");
		System.out.println("Email: " + emailInput.isDisplayed());
		System.out.println("Under18: " + under18Radio.isDisplayed());
		System.out.println("Education: " + eduTextArea.isDisplayed());
		System.out.println("Job1: " + job1Dropdown.isDisplayed());
		System.out.println("Job2: " + job2Dropdown.isDisplayed());
		System.out.println("Password: " + passwordDisable.isDisplayed());
		System.out.println("Age: " + ageRadioDisable.isDisplayed());
		System.out.println("Bio: " + bioDisable.isDisplayed());
		System.out.println("Job3: " + job3Disable.isDisplayed());
		System.out.println("Check: " + checkDisable.isDisplayed());
		System.out.println("Slider: " + sliderDisable.isDisplayed());
	}

	@Test
	public void TC_03_Check_Is_Selected() {
		// Declaration
		WebElement under18Radio = driver.findElement(By.id("under_18"));
		WebElement javaCheckbox = driver.findElement(By.id("java"));

		// Click elements
		under18Radio.click();
		javaCheckbox.click();

		// isSelected verification
		Assert.assertTrue(under18Radio.isSelected());
		Assert.assertTrue(javaCheckbox.isSelected());

		// Unclick elements
		javaCheckbox.click();

		// isDeSelected verification
		Assert.assertTrue(!javaCheckbox.isSelected());

		// check isSelected
		if (under18Radio.isSelected()) {
			System.out.println("Under18 is selected");
		} else {
			System.out.println("Under18 is deselected");
		}

		if (javaCheckbox.isSelected()) {
			System.out.println("Java CheckBox is selected");
		} else {
			System.out.println("Java CheckBox is deselected");
		}
	}

	@Test
	public void TC_04_MailChimp_SignUp() {
		// Open website
		driver.get("https://login.mailchimp.com/signup/");

		// Declaration
		WebElement email = driver.findElement(By.id("email"));
		WebElement password = driver.findElement(By.id("new_password"));
		WebElement lowercase = driver.findElement(By.xpath("//li[contains(@class,'lowercase-char')]"));
		WebElement uppercase = driver.findElement(By.xpath("//li[contains(@class,'uppercase-char')]"));
		WebElement number = driver.findElement(By.xpath("//li[contains(@class,'number-char')]"));
		WebElement specialCharacter = driver.findElement(By.xpath("//li[contains(@class,'special-char')]"));
		WebElement char_8 = driver.findElement(By.xpath("//li[contains(@class,'8-char')]"));

		// Send email key
		email.sendKeys("hmm@gmail.com");

		// Send lowercase password
		password.sendKeys("aaa");
		// verify password
		Assert.assertTrue(lowercase.getAttribute("class").contains("lowercase-char completed"));
		Assert.assertTrue(uppercase.getAttribute("class").contains("uppercase-char not-completed"));
		Assert.assertTrue(number.getAttribute("class").contains("number-char not-completed"));
		Assert.assertTrue(specialCharacter.getAttribute("class").contains("special-char not-completed"));
		Assert.assertTrue(char_8.getAttribute("class").contains("8-char not-completed"));

		// Send uppercase password
		password.clear();
		password.sendKeys("AAA");
		// verify password
		Assert.assertTrue(lowercase.getAttribute("class").contains("lowercase-char not-completed"));
		Assert.assertTrue(uppercase.getAttribute("class").contains("uppercase-char completed"));
		Assert.assertTrue(number.getAttribute("class").contains("number-char not-completed"));
		Assert.assertTrue(specialCharacter.getAttribute("class").contains("special-char not-completed"));
		Assert.assertTrue(char_8.getAttribute("class").contains("8-char not-completed"));

		// Send number password
		password.clear();
		password.sendKeys("1");
		// verify password
		Assert.assertTrue(lowercase.getAttribute("class").contains("lowercase-char not-completed"));
		Assert.assertTrue(uppercase.getAttribute("class").contains("uppercase-char not-completed"));
		Assert.assertTrue(number.getAttribute("class").contains("number-char completed"));
		Assert.assertTrue(specialCharacter.getAttribute("class").contains("special-char not-completed"));
		Assert.assertTrue(char_8.getAttribute("class").contains("8-char not-completed"));

		// Send special character password
		password.clear();
		password.sendKeys("!@#$%");
		// verify password
		Assert.assertTrue(lowercase.getAttribute("class").contains("lowercase-char not-completed"));
		Assert.assertTrue(uppercase.getAttribute("class").contains("uppercase-char not-completed"));
		Assert.assertTrue(number.getAttribute("class").contains("number-char not-completed"));
		Assert.assertTrue(specialCharacter.getAttribute("class").contains("special-char completed"));
		Assert.assertTrue(char_8.getAttribute("class").contains("8-char not-completed"));

		// Send enough 8 characters password
		password.clear();
		password.sendKeys("12345678");
		// verify password
		Assert.assertTrue(lowercase.getAttribute("class").contains("lowercase-char not-completed"));
		Assert.assertTrue(uppercase.getAttribute("class").contains("uppercase-char not-completed"));
		Assert.assertTrue(number.getAttribute("class").contains("number-char completed"));
		Assert.assertTrue(specialCharacter.getAttribute("class").contains("special-char not-completed"));
		Assert.assertTrue(char_8.getAttribute("class").contains("8-char completed"));
	}

	@AfterClass
	public void afterClass() {
		// Close browser (All websites)
		driver.quit();
	}
}
