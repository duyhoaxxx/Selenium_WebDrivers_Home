package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Web_Browser_Command {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		int Set_Browser = 0;
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
	public void TC_01_Browser_Veryfy_Url() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();

		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");

		driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
	}

	@Test
	public void TC_02_Browser_Veryfy_Title() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Assert.assertEquals(driver.getTitle(), "Customer Login");

		driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}

	@Test
	public void TC_03_Browser_Navigation() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		driver.navigate().back();
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		driver.navigate().forward();
		Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
	}

	@Test
	public void TC_04_Browser_Get_Page_Resource() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		String pageResource = driver.getPageSource();
		Assert.assertTrue(pageResource.contains("Login or Create an Account"));

		driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
	}

	@Test
	public void TC_01_Element_IsDisplay() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		WebElement TestEmail = driver.findElement(By.xpath("//input[@name='user_email']"));
		if (TestEmail.isDisplayed()) {
			TestEmail.clear();
			TestEmail.sendKeys("Automation Testing");
			System.out.println("Email is displayed");
		} else
			System.out.println("Email is not displayed");

		WebElement TestAge = driver.findElement(By.xpath("//input[@id='under_18']"));
		if (TestAge.isDisplayed()) {
			TestAge.click();
			System.out.println("Age is displayed");
		} else
			System.out.println("Age is not displayed");

		WebElement TestEdu = driver.findElement(By.xpath("//textarea[@name='user_edu']"));
		if (TestEdu.isDisplayed()) {
			TestEdu.sendKeys("Automation Testing");
			System.out.println("Education is displayed");
		} else
			System.out.println("Education is not displayed");

		WebElement TestDisplay = driver.findElement(By.xpath("//h5[contains(.,'User5')]"));
		if (TestDisplay.isDisplayed()) {
			System.out.println("Element is displayed");
		} else
			System.out.println("Element is not displayed");
	}

	@Test
	public void TC_02_Element_Check_Enable() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		if (driver.findElement(By.xpath("//input[@name='user_email']")).isEnabled()) {
			System.out.println("Email is Enabled");
		} else
			System.out.println("Email is not Enabled");

		if (driver.findElement(By.xpath("//input[@id='under_18']")).isEnabled()) {
			System.out.println("Age is Enabled");
		} else
			System.out.println("Age is not Enabled");

		if (driver.findElement(By.xpath("//textarea[@name='user_edu']")).isEnabled()) {
			System.out.println("Education is Enabled");
		} else
			System.out.println("Education is not Enabled");

		if (driver.findElement(By.xpath("//select[@id='job1']")).isEnabled()) {
			System.out.println("Job1 is Enabled");
		} else
			System.out.println("Job1 is not Enabled");

		if (driver.findElement(By.xpath("//select[@id='job2']")).isEnabled()) {
			System.out.println("Job2 is Enabled");
		} else
			System.out.println("Job2 is not Enabled");

		if (driver.findElement(By.xpath("//input[@id='development']")).isEnabled()) {
			System.out.println("Interests is Enabled");
		} else
			System.out.println("Interests is not Enabled");

		if (driver.findElement(By.xpath("//input[@id='slider-1']")).isEnabled()) {
			System.out.println("Slider is Enabled");
		} else
			System.out.println("Slider is not Enabled");

		////
		if (driver.findElement(By.xpath("//input[@name='user_pass']")).isEnabled()) {
			System.out.println("PASS is Enabled");
		} else
			System.out.println("PASS is not Enabled");

		if (driver.findElement(By.xpath("//input[@id='radio-disabled']")).isEnabled()) {
			System.out.println("Age is Enabled");
		} else
			System.out.println("Age is not Enabled");

		if (driver.findElement(By.xpath("//textarea[@name='user_bio']")).isEnabled()) {
			System.out.println("Education is Enabled");
		} else
			System.out.println("Education is not Enabled");

		if (driver.findElement(By.xpath("//select[@id='job3']")).isEnabled()) {
			System.out.println("Job3 is Enabled");
		} else
			System.out.println("Job3 is not Enabled");

		if (driver.findElement(By.xpath("//input[@id='check-disbaled']")).isEnabled()) {
			System.out.println("Interests is Enabled");
		} else
			System.out.println("Interests is not Enabled");

		if (driver.findElement(By.xpath("//input[@id='slider-2']")).isEnabled()) {
			System.out.println("Slider is Enabled");
		} else
			System.out.println("Slider is not Enabled");
	}

	@Test
	public void TC_03_Element_IsSelected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		WebElement TestAge = driver.findElement(By.xpath("//input[@id='under_18']"));
		WebElement TestLanguage = driver.findElement(By.xpath("//input[@id='java']"));
		//
		TestAge.click();
		TestLanguage.click();
		//
		if (TestAge.isSelected())
			System.out.println("Age is Selected");
		else
			System.out.println("Age is not Selected");

		if (TestLanguage.isSelected())
			System.out.println("Age is Selected");
		else
			System.out.println("Age is not Selected");
		//
		TestLanguage.click();
		//
		if (TestLanguage.isSelected())
			System.out.println("Age is Selected");
		else
			System.out.println("Age is not Selected");
	}

	@Test
	public void TC_04_Element_Register_Function() {
		driver.get("https://login.mailchimp.com/signup/");

		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("Autotest@gmail.com");
		driver.findElement(By.xpath("//input[@id='new_username']")).sendKeys("AutoTest");
		WebElement pass = driver.findElement(By.xpath("//input[@id='new_password']"));
		WebElement button = driver.findElement(By.xpath("//button[@id='create-account']"));

		pass.clear();
		pass.sendKeys("123");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed']")).isDisplayed());
		Assert.assertFalse(button.isEnabled());
		sleepInSecond(1);

		pass.clear();
		pass.sendKeys("abc");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='lowercase-char completed']")).isDisplayed());
		Assert.assertFalse(button.isEnabled());
		sleepInSecond(1);

		pass.clear();
		pass.sendKeys("ABC");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='uppercase-char completed']")).isDisplayed());
		Assert.assertFalse(button.isEnabled());
		sleepInSecond(1);

		pass.clear();
		pass.sendKeys("@#$&");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='special-char completed']")).isDisplayed());
		Assert.assertFalse(button.isEnabled());
		sleepInSecond(1);

		pass.clear();
		pass.sendKeys("1234567890");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='8-char completed']")).isDisplayed());
		Assert.assertFalse(button.isEnabled());
		sleepInSecond(1);

		pass.clear();
		pass.sendKeys("1aA@12345");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//button[@id='create-account']")).isEnabled());
		sleepInSecond(1);
		
		WebElement checkBox = driver.findElement(By.xpath("//input[@name='marketing_newsletter']"));
		checkBox.click();
		sleepInSecond(1);
		Assert.assertTrue(checkBox.isSelected());
		sleepInSecond(1);
		
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