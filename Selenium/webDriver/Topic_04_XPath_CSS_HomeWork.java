package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_XPath_CSS_HomeWork {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_RegisterWithEmptyData() {
		//step1
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		// click button "dang ky"
		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();

		// Check message error
		driver.findElement(By.xpath("//label[@id='txtFirstname-error']")).getText().compareTo("Vui lòng nhập họ tên");
		driver.findElement(By.xpath("//label[contains(text(),'nhập họ tên')]")).getText().compareTo("Vui lòng nhập họ tên");
		
		driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText().compareTo("Vui lòng nhập email");
		driver.findElement(By.xpath("//label[contains(@id,'txtEmail')]")).getText().compareTo("Vui lòng nhập email");
		
		driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText().compareTo("Vui lòng nhập lại địa chỉ email");
		
		driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText().compareTo("Vui lòng nhập mật khẩu");
		
		driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText().compareTo("Vui lòng nhập lại mật khẩu");
		
		driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText().compareTo("Vui lòng nhập số điện thoại. ");
		
		
		
	}

	@Test
	public void TC_02_RegisterWithinvalidEmail() {
		//step1
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		// fill data 
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Kane");
		
		driver.findElement(By.xpath("//input[@name='txtEmail']")).sendKeys("@gmail@yahoo@");
		
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("@gmail@yahoo@");
		
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
		
		driver.findElement(By.xpath("//input[@name='txtCPassword']")).sendKeys("123456");
		
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0123456789");
		
		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
		
		sleepInSecond(2);
		
		// Check message Error
		driver.findElement(By.xpath("//label[contains(@id,'txtEmail')]")).getText().compareTo("Vui lòng nhập email hợp lệ");
		
		driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText().compareTo("Email nhập lại không đúng");
	}

	@Test
	public void TC_03_RegisterWithIncorrectConfirmEmail() {
		//step1
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		// fill data 
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Kane");
		
		driver.findElement(By.xpath("//input[@name='txtEmail']")).sendKeys("kane@gmail.com");
		
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("kan@gmail.com");
		
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
		
		driver.findElement(By.xpath("//input[@name='txtCPassword']")).sendKeys("123456");
		
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0123456789");
		
		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
		
		sleepInSecond(2);
		
		// Check message Error
		//driver.findElement(By.xpath("//label[contains(@id,'txtEmail')]")).getText().compareTo("Vui lòng nhập email hợp lệ");
		
		driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText().compareTo("Email nhập lại không đúng");
		
	}
	
	@Test
	public void TC_04_RegisterWithPassWordLess6Characters() {
		//step1
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		// fill data 
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Kane");
		
		driver.findElement(By.xpath("//input[@name='txtEmail']")).sendKeys("kane@gmail.com");
		
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("kane@gmail.com");
		
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("12345");
		
		driver.findElement(By.xpath("//input[@name='txtCPassword']")).sendKeys("12345");
		
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0123456789");
		
		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
		
		sleepInSecond(2);
		
		// Check message Error
		//driver.findElement(By.xpath("//label[contains(@id,'txtEmail')]")).getText().compareTo("Vui lòng nhập email hợp lệ");
		
		driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText().compareTo("Mật khẩu phải có ít nhất 6 ký tự");
		
		driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText().compareTo("Mật khẩu phải có ít nhất 6 ký tự");
		
	}
	
	@Test
	public void TC_05_RegisterWithIncorrectConfirmPassWord() {
		//step1
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		// fill data 
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Kane");
		
		driver.findElement(By.xpath("//input[@name='txtEmail']")).sendKeys("kane@gmail.com");
		
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("kane@gmail.com");
		
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
		
		driver.findElement(By.xpath("//input[@name='txtCPassword']")).sendKeys("123457");
		
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0123456789");
		
		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
		
		sleepInSecond(2);
		
		// Check message Error
		//driver.findElement(By.xpath("//label[contains(@id,'txtEmail')]")).getText().compareTo("Vui lòng nhập email hợp lệ");
		
		driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText().compareTo("Mật khẩu bạn nhập không khớp");
		
	}
	
	@Test
	public void TC_06_RegisterWithInvalidPhoneNumber() {
		//step1
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		
		// fill data 
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Kane");
		
		driver.findElement(By.xpath("//input[@name='txtEmail']")).sendKeys("kane@gmail.com");
		
		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("kane@gmail.com");
		
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");
		
		driver.findElement(By.xpath("//input[@name='txtCPassword']")).sendKeys("123456");
		
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("012345");
		
		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
		
		sleepInSecond(2);
		
		// Check message Error 
		//driver.findElement(By.xpath("//label[contains(@id,'txtEmail')]")).getText().compareTo("Vui lòng nhập email hợp lệ");
		
		driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText().compareTo("Số điện thoại phải từ 10-11 số. ");
		
		driver.findElement(By.xpath("//input[@id='txtPhone']")).clear();
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0523452569");
		
		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
		
		sleepInSecond(2);
		
		driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText().compareTo("Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}