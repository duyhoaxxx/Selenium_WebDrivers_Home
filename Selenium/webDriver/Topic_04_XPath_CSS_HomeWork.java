package webDriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_XPath_CSS_HomeWork {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		Random random = new Random();
		int Set_Browser = random.nextInt(100);
		if (Set_Browser % 3 == 0) {
			// Chorme
			System.setProperty("webdriver.chrome.driver", projectPath + "\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (Set_Browser % 3 == 1) {
			// Firefox
			System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else {
			// EDGE
			System.setProperty("webdriver.edge.driver", projectPath + "\\browserDrivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		}

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_RegisterWithEmptyData() {
		// step1
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		// click button "dang ky"
		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
		
		// Check message error
		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtFirstname-error']")).getText(),
				"Vui lòng nhập họ tên");
		Assert.assertEquals(driver.findElement(By.xpath("//label[contains(text(),'nhập họ tên')]")).getText(),
				"Vui lòng nhập họ tên");

		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).getText(),
				"Vui lòng nhập email");
		Assert.assertEquals(driver.findElement(By.xpath("//label[contains(@id,'txtEmail')]")).getText(),
				"Vui lòng nhập email");

		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).getText(),
				"Vui lòng nhập lại địa chỉ email");

		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).getText(),
				"Vui lòng nhập mật khẩu");

		Assert.assertTrue(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).isDisplayed());

		Assert.assertTrue(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).isDisplayed());
	}

	@Test
	public void TC_02_RegisterWithinvalidEmail() {
		// step1
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// fill data
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Kane");

		driver.findElement(By.xpath("//input[@name='txtEmail']")).sendKeys("@gmail@yahoo@");

		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("@gmail@yahoo@");

		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");

		driver.findElement(By.xpath("//input[@name='txtCPassword']")).sendKeys("123456");

		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0123456789");

		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
		

		// Check message Error
		Assert.assertEquals(driver.findElement(By.xpath("//label[contains(@id,'txtEmail')]")).getText(),
				"Vui lòng nhập email hợp lệ");
		Assert.assertEquals(driver.findElement(By.xpath("//label[contains(@id,'txtCEmail')]")).getText(),
				"Email nhập lại không đúng");
	}

	@Test
	public void TC_03_RegisterWithIncorrectConfirmEmail() {
		// step1
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// fill data
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Kane");

		driver.findElement(By.xpath("//input[@name='txtEmail']")).sendKeys("kane@gmail.com");

		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("kan@gmail.com");

		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");

		driver.findElement(By.xpath("//input[@name='txtCPassword']")).sendKeys("123456");

		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0123456789");

		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
		

		// Check message Error
		Assert.assertTrue(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).isDisplayed());
	}

	@Test
	public void TC_04_RegisterWithPassWordLess6Characters() {
		// step1
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// fill data
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Kane");

		driver.findElement(By.xpath("//input[@name='txtEmail']")).sendKeys("kane@gmail.com");

		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("kane@gmail.com");

		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("12345");

		driver.findElement(By.xpath("//input[@name='txtCPassword']")).sendKeys("12345");

		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0123456789");

		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
		

		// Check message Error
		Assert.assertTrue(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).isDisplayed());
	}

	@Test
	public void TC_05_RegisterWithIncorrectConfirmPassWord() {
		// step1
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// fill data
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Kane");

		driver.findElement(By.xpath("//input[@name='txtEmail']")).sendKeys("kane@gmail.com");

		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("kane@gmail.com");

		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");

		driver.findElement(By.xpath("//input[@name='txtCPassword']")).sendKeys("123457");

		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0123456789");

		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
		

		// Check message Error

		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).getText(),
				"Mật khẩu bạn nhập không khớp");
	}

	@Test
	public void TC_06_RegisterWithInvalidPhoneNumber() {
		// step1
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");

		// fill data
		driver.findElement(By.xpath("//input[@id='txtFirstname']")).sendKeys("Kane");

		driver.findElement(By.xpath("//input[@name='txtEmail']")).sendKeys("kane@gmail.com");

		driver.findElement(By.xpath("//input[@id='txtCEmail']")).sendKeys("kane@gmail.com");

		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("123456");

		driver.findElement(By.xpath("//input[@name='txtCPassword']")).sendKeys("123456");

		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("012345");

		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
		

		// Check message Error
		Assert.assertTrue(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).isDisplayed());

		driver.findElement(By.xpath("//input[@id='txtPhone']")).clear();
		driver.findElement(By.xpath("//input[@id='txtPhone']")).sendKeys("0523452569");
		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
		

		Assert.assertEquals(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).getText(),
				"Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019");

	}

	@Test
	public void TC_01_LoginWithEmptyData() {
		// step1
		driver.get("http://live.guru99.com/");
		// step2
		driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();
		driver.findElement(By.xpath(
				"//div[@id='header-account']//li[@class='first']//a[@title='My Account' and text()='My Account']"))
				.click();
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		

		// Check message error
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText(),
				"This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(),
				"This is a required field.");
	}

	@Test
	public void TC_02_LoginWithInvalidEmail() {
		// step1
		driver.get("http://live.guru99.com/");
		// step2
		driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();
		driver.findElement(By.xpath(
				"//div[@id='header-account']//li[@class='first']//a[@title='My Account' and text()='My Account']"))
				.click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("12345@123.123");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		

		// Check message error
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).isDisplayed());
	}

	@Test
	public void TC_03_LoginWithPasswordLess6Characters() {
		// step1
		driver.get("http://live.guru99.com/");
		// step2
		driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();
		driver.findElement(By.xpath(
				"//div[@id='header-account']//li[@class='first']//a[@title='My Account' and text()='My Account']"))
				.click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		

		// Check message error
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).isDisplayed());
	}

	@Test
	public void TC_04_LoginWithIncorrect() {
		// step1
		driver.get("http://live.guru99.com/");
		// step2
		driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();
		driver.findElement(By.xpath(
				"//div[@id='header-account']//li[@class='first']//a[@title='My Account' and text()='My Account']"))
				.click();
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("12356789");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		

		// Check message error
		Assert.assertTrue(
				driver.findElement(By.xpath("//span[contains(text(),'Invalid login or password.')]")).isDisplayed());
	}

	public String RandomString() {
		int leftLimit = 97; // letter 'a'
		int rightLimit = 122; // letter 'z'
		int len = 5;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		String generatedString = buffer.toString();
		return generatedString;
	}

	@Test
	public void TC_05_LoginWithIncorrect() {
		// step1
		driver.get("http://live.guru99.com/");
		// step2
		driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();
		driver.findElement(By.xpath(
				"//div[@id='header-account']//li[@class='first']//a[@title='My Account' and text()='My Account']"))
				.click();
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		// random email
		String generatedString = RandomString() + "@gmail.com";
		//
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys("Kanee");
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys("Pham");
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(generatedString);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys("123456");
		driver.findElement(By.xpath("//input[@id='is_subscribed']")).click();
		driver.findElement(By.xpath("//button[@title='Register']")).click();

		

		// Check message error
		Assert.assertTrue(driver
				.findElement(By.xpath("//span[contains(text(),'Thank you for registering with Main Website Store.')]"))
				.isDisplayed());

		// Log out
		driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
	}

	@Test
	public void TC_06_LoginWithValidAccount() {
		// step1
		driver.get("http://live.guru99.com/");
		// step2
		driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();
		driver.findElement(By.xpath(
				"//div[@id='header-account']//li[@class='first']//a[@title='My Account' and text()='My Account']"))
				.click();
		driver.findElement(By.xpath("//input[@id='email' and @type='email']")).sendKeys("kane.pham@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		// Check message error
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='hello']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-content']")).isDisplayed());
		
		// Log out
				driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();
				driver.findElement(By.xpath("//a[@title='Log Out']")).click();
	}

	@Test
	public void TC_07_AddToCartItem() {
		// step1
		driver.get("http://live.guru99.com/");
		// step2
		driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();
		driver.findElement(By.xpath(
				"//div[@id='header-account']//li[@class='first']//a[@title='My Account' and text()='My Account']"))
				.click();
		driver.findElement(By.xpath("//input[@id='email' and @type='email']")).sendKeys("kane.pham@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		driver.findElement(By.xpath("//a[@class='level0 ' and contains(text(),'Mobile')]")).click();
		driver.findElement(By.xpath("//a[@title='IPhone']/parent::h2/following-sibling::div[@class='actions']//button"))
				.click();

		

		// Check message error
		Assert.assertTrue(
				driver.findElement(By.xpath("//span[contains(text(),'added to your shopping cart')]")).isDisplayed());
		
		// Log out
				driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();
				driver.findElement(By.xpath("//a[@title='Log Out']")).click();
	}
	
	@Test
	public void TC_08_EmptyCartItem() {
		// step1
		driver.get("http://live.guru99.com/");
		// step2
		driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();
		driver.findElement(By.xpath(
				"//div[@id='header-account']//li[@class='first']//a[@title='My Account' and text()='My Account']"))
				.click();
		driver.findElement(By.xpath("//input[@id='email' and @type='email']")).sendKeys("kane.pham@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123456");
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		driver.findElement(By.xpath("//a[@class='level0 ' and contains(text(),'Mobile')]")).click();
		driver.findElement(By.xpath("//a[@title='IPhone']/parent::h2/following-sibling::div[@class='actions']//button"))
				.click();
		driver.findElement(By.xpath("//span[contains(text(),'Empty')]")).click();

		

		// Check message error
		Assert.assertTrue(
				driver.findElement(By.cssSelector(".page-title")).isDisplayed());
		
		// Log out
				driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();
				driver.findElement(By.xpath("//a[@title='Log Out']")).click();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}