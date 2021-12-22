package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_Popup_Iframe_Windows {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		SetBrowser();
		explicitWait = new WebDriverWait(driver, 30);
	}

	@Test
	public void TC_01_Fixed_Popup() {
		driver.get("https://ngoaingu24h.vn/");
		var ByLogin = By.xpath("//div[@id='modal-login-v1']");
		Assert.assertFalse(driver.findElement(ByLogin).isDisplayed());
		driver.findElement(By.xpath("//button[@class='login_ icon-before']")).click();
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(ByLogin).isDisplayed());
		driver.findElement(By.xpath("//input[@id='account-input']")).sendKeys("automation");
		driver.findElement(By.xpath("//input[@id='password-input']")).sendKeys("automation");
		driver.findElement(By.xpath("//button[@data-text='Đăng nhập']")).click();
		sleepInSecond(1);
		Assert.assertEquals(driver
				.findElement(By.xpath("//div[@id='modal-login-v1']//div[@class='row error-login-panel']")).getText(),
				"Tài khoản không tồn tại!");
		driver.findElement(By.xpath("//div[@id='modal-login-v1']//button[@class='close']")).click();
		Assert.assertFalse(driver.findElement(ByLogin).isDisplayed());
	}

	@Test
	public void TC_02_Random_Popup() {
		driver.get("https://blog.testproject.io/");
		sleepInSecond(30);
		if (driver.findElement(By.id("close-mailch")).isDisplayed())
			driver.findElement(By.id("close-mailch")).click();

		driver.findElement(By.xpath("//section[@id='search-2']//input[@type='search']")).sendKeys("Selenium");
		driver.findElement(By.xpath("//section[@id='search-2']//span[@class='glass']")).click();
		sleepInSecond(3);

		var ListResults = driver.findElements(By.xpath("//div[@class='post-on-archive-page']//h3/a"));
		for (WebElement Result : ListResults) {
			Assert.assertTrue(Result.getText().contains("Selenium"));
			System.out.println(Result.getText());
		}
	}

	@Test
	public void TC_03_Random_Popup() {
		driver.get("https://vnk.edu.vn/");
		sleepInSecond(20);
		var ClosePopup = By.xpath(
				"//div[@class='thrv_wrapper thrv_icon tcb-icon-display tve_evt_manager_listen tve_et_click tve_ea_thrive_leads_form_close']");
		if (driver.findElement(ClosePopup).isDisplayed()) {
			Assert.assertTrue(driver.findElement(By.xpath("//div[@class='tcb-flex-col']")).isDisplayed());
			driver.findElement(ClosePopup).click();
		}
		Assert.assertFalse(driver.findElement(By.xpath("//div[@class='tcb-flex-col']")).isDisplayed());
	}

	@Test
	public void TC_04_Random_NotInDOM() {
		driver.get("https://dehieu.vn/");
		sleepInSecond(10);
		var ClosePopup = By.xpath("//section[@id='popup']");
		var IsPopup = driver.findElements(ClosePopup);
		if (IsPopup.size() > 0) {
			driver.findElement(By.id("close-popup")).click();
		}
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//li/a[text()='Trang chủ']")).isDisplayed());
	}

	@Test
	public void TC_05() {

	}

	@Test
	public void TC_06_Iframe() {
		driver.get("https://kyna.vn/");

	}

	@Test
	public void TC_07_Frame() {

	}

	@Test
	public void TC_08() {

	}

	@Test
	public void TC_09() {

	}

	@Test
	public void TC_10() {

	}

	@Test
	public void TC_11() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void SetBrowser() {
		int Set_Browser = 1;
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
		driver.manage().window().maximize();
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}