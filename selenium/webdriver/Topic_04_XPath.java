package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_XPath {
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

	private void ClearFormData() {
		// Clear data after filling the blank

		driver.findElement(By.cssSelector("input[id='txtFirstname']")).clear();
		driver.findElement(By.cssSelector("input[id='txtEmail']")).clear();
		driver.findElement(By.cssSelector("input[id='txtCEmail']")).clear();
		driver.findElement(By.cssSelector("input[id='txtPassword']")).clear();
		driver.findElement(By.cssSelector("input[id='txtCPassword']")).clear();
		driver.findElement(By.cssSelector("input[id='txtPhone']")).clear();
	}

	@Test
	public void TC_01_RegisterWithEmptyData() {
		// Register with Empty data

		driver.findElement(By.xpath("//form[@id='frmLogin']//div[@class='field_btn']//button")).click();
		String error = "";

		// 1 - Check first name is invalid
		error = driver.findElement(By.id("txtFirstname-error")).getText();
		Assert.assertEquals(error, "Vui lòng nhập họ tên");

		// 2 - Check email is invalid
		error = driver.findElement(By.id("txtEmail-error")).getText();
		Assert.assertEquals(error, "Vui lòng nhập email");

		// 3 - Check confirm email is invalid
		error = driver.findElement(By.id("txtCEmail-error")).getText();
		Assert.assertEquals(error, "Vui lòng nhập lại địa chỉ email");

		// 4 - Check password is invalid
		error = driver.findElement(By.id("txtPassword-error")).getText();
		Assert.assertEquals(error, "Vui lòng nhập mật khẩu");

		// 5 - Check confirm password is invalid
		error = driver.findElement(By.id("txtCPassword-error")).getText();
		Assert.assertEquals(error, "Vui lòng nhập lại mật khẩu");

		// 6 - Check phone is invalid
		error = driver.findElement(By.id("txtPhone-error")).getText();
		Assert.assertEquals(error, "Vui lòng nhập số điện thoại.");
	}

	@Test
	public void TC_02_RegisterWithInvalidEmail() {
		// Register with Invalid email

		driver.findElement(By.cssSelector("input[id='txtFirstname']")).sendKeys("Hứa Minh Mẫn");
		driver.findElement(By.cssSelector("input[id='txtEmail']")).sendKeys("123@456@789");
		driver.findElement(By.cssSelector("input[id='txtCEmail']")).sendKeys("123@456@789");
		driver.findElement(By.cssSelector("input[id='txtPassword']")).sendKeys("123456789");
		driver.findElement(By.cssSelector("input[id='txtCPassword']")).sendKeys("123456789");
		driver.findElement(By.cssSelector("input[id='txtPhone']")).sendKeys("0901234567");
		driver.findElement(By.xpath("//form[@id='frmLogin']//div[@class='field_btn']//button")).click();
		String error = "";

		// 1 - Check first name is valid
		// label is ""
		error = driver.findElement(By.id("txtFirstname-error")).getText();
		Assert.assertEquals(error, "");
		// label display is none
		error = driver.findElement(By.id("txtFirstname-error")).getAttribute("style");
		Assert.assertEquals(error, "display: none;");

		// 2 - Check email is invalid
		error = driver.findElement(By.id("txtEmail-error")).getText();
		Assert.assertEquals(error, "Vui lòng nhập email hợp lệ");

		// 3 - Check confirm email is invalid
		error = driver.findElement(By.id("txtCEmail-error")).getText();
		Assert.assertEquals(error, "Email nhập lại không đúng");

		// 4 - Check password is valid
		// label is ""
		error = driver.findElement(By.id("txtPassword-error")).getText();
		Assert.assertEquals(error, "");
		// label display is none
		error = driver.findElement(By.id("txtPassword-error")).getAttribute("style");
		Assert.assertEquals(error, "display: none;");

		// 5 - Check confirm password is valid
		// label is ""
		error = driver.findElement(By.id("txtCPassword-error")).getText();
		Assert.assertEquals(error, "");
		// label display is none
		error = driver.findElement(By.id("txtCPassword-error")).getAttribute("style");
		Assert.assertEquals(error, "display: none;");

		// 6 - Check phone is valid
		// label is ""
		error = driver.findElement(By.id("txtPhone-error")).getText();
		Assert.assertEquals(error, "");
		// label display is none
		error = driver.findElement(By.id("txtPhone-error")).getAttribute("style");
		Assert.assertEquals(error, "display: none;");

		// Clear data
		ClearFormData();
	}

	@Test
	public void TC_03_RegisterWithInvalidConfirmEmail() {
		// Register with Invalid confirm email

		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Hứa Minh Mẫn");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("johnwick@gmail.net");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("johnwick@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456789");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456789");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0901234567");
		driver.findElement(By.xpath("//form[@id='frmLogin']//div[@class='field_btn']//button")).click();
		String error = "";

		// 1 - Check first name is valid
		// label is ""
		error = driver.findElement(By.id("txtFirstname-error")).getText();
		Assert.assertEquals(error, "");
		// label display is none
		error = driver.findElement(By.id("txtFirstname-error")).getAttribute("style");
		Assert.assertEquals(error, "display: none;");

		// 2 - Check email is valid
		// label is ""
		error = driver.findElement(By.id("txtEmail-error")).getText();
		Assert.assertEquals(error, "");
		// label display is none
		error = driver.findElement(By.id("txtEmail-error")).getAttribute("style");
		Assert.assertEquals(error, "display: none;");

		// 3 - Check confirm email is invalid
		error = driver.findElement(By.id("txtCEmail-error")).getText();
		Assert.assertEquals(error, "Email nhập lại không đúng");

		// 4 - Check password is valid
		// label is ""
		error = driver.findElement(By.id("txtPassword-error")).getText();
		Assert.assertEquals(error, "");
		// label display is none
		error = driver.findElement(By.id("txtPassword-error")).getAttribute("style");
		Assert.assertEquals(error, "display: none;");

		// 5 - Check confirm password is valid
		// label is ""
		error = driver.findElement(By.id("txtCPassword-error")).getText();
		Assert.assertEquals(error, "");
		// label display is none
		error = driver.findElement(By.id("txtCPassword-error")).getAttribute("style");
		Assert.assertEquals(error, "display: none;");

		// 6 - Check phone is valid
		// label is ""
		error = driver.findElement(By.id("txtPhone-error")).getText();
		Assert.assertEquals(error, "");
		// label display is none
		error = driver.findElement(By.id("txtPhone-error")).getAttribute("style");
		Assert.assertEquals(error, "display: none;");

		// Clear data
		ClearFormData();
	}

	@Test
	public void TC_04_RegisterWithPasswordLessThanSixCharacters() {
		// Register with Password less than six characters

		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Hứa Minh Mẫn");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("johnwick@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("johnwick@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("12345");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0901234567");
		driver.findElement(By.xpath("//form[@id='frmLogin']//div[@class='field_btn']//button")).click();
		String error = "";

		// 1 - Check first name is valid
		// label is ""
		error = driver.findElement(By.id("txtFirstname-error")).getText();
		Assert.assertEquals(error, "");
		// label display is none
		error = driver.findElement(By.id("txtFirstname-error")).getAttribute("style");
		Assert.assertEquals(error, "display: none;");

		// 2 - Check email is valid
		// label is ""
		error = driver.findElement(By.id("txtEmail-error")).getText();
		Assert.assertEquals(error, "");
		// label display is none
		error = driver.findElement(By.id("txtEmail-error")).getAttribute("style");
		Assert.assertEquals(error, "display: none;");

		// 3 - Check confirm email is valid
		// label is ""
		error = driver.findElement(By.id("txtCEmail-error")).getText();
		Assert.assertEquals(error, "");
		// label display is none
		error = driver.findElement(By.id("txtCEmail-error")).getAttribute("style");
		Assert.assertEquals(error, "display: none;");

		// 4 - Check password is invalid
		error = driver.findElement(By.id("txtPassword-error")).getText();
		Assert.assertEquals(error, "Mật khẩu phải có ít nhất 6 ký tự");

		// 5 - Check confirm password is invalid
		error = driver.findElement(By.id("txtCPassword-error")).getText();
		Assert.assertEquals(error, "Mật khẩu phải có ít nhất 6 ký tự");

		// 6 - Check phone is valid
		// label is ""
		error = driver.findElement(By.id("txtPhone-error")).getText();
		Assert.assertEquals(error, "");
		// label display is none
		error = driver.findElement(By.id("txtPhone-error")).getAttribute("style");
		Assert.assertEquals(error, "display: none;");

		// Clear data
		ClearFormData();
	}

	@Test
	public void TC_05_RegisterWithIncorrectConfirmPassword() {
		// Register with incorrect Confirm password

		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Hứa Minh Mẫn");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("johnwick@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("johnwick@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456789");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0901234567");
		driver.findElement(By.xpath("//form[@id='frmLogin']//div[@class='field_btn']//button")).click();
		String error = "";

		// 1 - Check first name is valid
		// label is ""
		error = driver.findElement(By.id("txtFirstname-error")).getText();
		Assert.assertEquals(error, "");
		// label display is none
		error = driver.findElement(By.id("txtFirstname-error")).getAttribute("style");
		Assert.assertEquals(error, "display: none;");

		// 2 - Check email is valid
		// label is ""
		error = driver.findElement(By.id("txtEmail-error")).getText();
		Assert.assertEquals(error, "");
		// label display is none
		error = driver.findElement(By.id("txtEmail-error")).getAttribute("style");
		Assert.assertEquals(error, "display: none;");

		// 3 - Check confirm email is valid
		// label is ""
		error = driver.findElement(By.id("txtCEmail-error")).getText();
		Assert.assertEquals(error, "");
		// label display is none
		error = driver.findElement(By.id("txtCEmail-error")).getAttribute("style");
		Assert.assertEquals(error, "display: none;");

		// 4 - Check password is valid
		// label is ""
		error = driver.findElement(By.id("txtPassword-error")).getText();
		Assert.assertEquals(error, "");
		// label display is none
		error = driver.findElement(By.id("txtPassword-error")).getAttribute("style");
		Assert.assertEquals(error, "display: none;");

		// 5 - Check confirm password is invalid
		error = driver.findElement(By.id("txtCPassword-error")).getText();
		Assert.assertEquals(error, "Mật khẩu bạn nhập không khớp");

		// 6 - Check phone is valid
		// label is ""
		error = driver.findElement(By.id("txtPhone-error")).getText();
		Assert.assertEquals(error, "");
		// label display is none
		error = driver.findElement(By.id("txtPhone-error")).getAttribute("style");
		Assert.assertEquals(error, "display: none;");

		// Clear data
		ClearFormData();
	}

	@Test
	public void TC_06_RegisterWithInvalidPhoneNumber() {
		// Register with incorrect Phone number

		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Hứa Minh Mẫn");
		driver.findElement(By.xpath("//input[@id='txtEmail']")).sendKeys("johnwick@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("johnwick@gmail.com");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456789");
		driver.findElement(By.xpath("//input[@id='txtCPassword']")).sendKeys("123456789");
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("123456");
		driver.findElement(By.xpath("//form[@id='frmLogin']//div[@class='field_btn']//button")).click();
		String error = "";

		// 1 - Check first name is valid
		// label is ""
		error = driver.findElement(By.id("txtFirstname-error")).getText();
		Assert.assertEquals(error, "");
		// label display is none
		error = driver.findElement(By.id("txtFirstname-error")).getAttribute("style");
		Assert.assertEquals(error, "display: none;");

		// 2 - Check email is valid
		// label is ""
		error = driver.findElement(By.id("txtEmail-error")).getText();
		Assert.assertEquals(error, "");
		// label display is none
		error = driver.findElement(By.id("txtEmail-error")).getAttribute("style");
		Assert.assertEquals(error, "display: none;");

		// 3 - Check confirm email is valid
		// label is ""
		error = driver.findElement(By.id("txtCEmail-error")).getText();
		Assert.assertEquals(error, "");
		// label display is none
		error = driver.findElement(By.id("txtCEmail-error")).getAttribute("style");
		Assert.assertEquals(error, "display: none;");

		// 4 - Check password is valid
		// label is ""
		error = driver.findElement(By.id("txtPassword-error")).getText();
		Assert.assertEquals(error, "");
		// label display is none
		error = driver.findElement(By.id("txtPassword-error")).getAttribute("style");
		Assert.assertEquals(error, "display: none;");

		// 5 - Check confirm password is valid
		// label is ""
		error = driver.findElement(By.id("txtCPassword-error")).getText();
		Assert.assertEquals(error, "");
		// label display is none
		error = driver.findElement(By.id("txtCPassword-error")).getAttribute("style");
		Assert.assertEquals(error, "display: none;");

		// 6 - Check phone is invalid
		error = driver.findElement(By.id("txtPhone-error")).getText();
		Assert.assertEquals(error, "Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");

		// Clear data
		ClearFormData();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
