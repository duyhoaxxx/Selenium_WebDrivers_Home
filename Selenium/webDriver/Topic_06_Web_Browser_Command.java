package webDriver;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
		driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();

		driver.findElement(By.xpath(
				"//div[@id='header-account']//li[@class='first']//a[@title='My Account' and text()='My Account']"))
				.click();
		assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");

		driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();
		assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
	}

	@Test
	public void TC_02_Browser_Veryfy_Title() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();

		driver.findElement(By.xpath(
				"//div[@id='header-account']//li[@class='first']//a[@title='My Account' and text()='My Account']"))
				.click();
		assertEquals(driver.getTitle(), "Customer Login");

		driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();
		assertEquals(driver.getTitle(), "Create New Customer Account");
	}

	@Test
	public void TC_03_Browser_Navigation() {
		driver.get("http://live.techpanda.org/");
		driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();

		driver.findElement(By.xpath(
				"//div[@id='header-account']//li[@class='first']//a[@title='My Account' and text()='My Account']"))
				.click();
		driver.findElement(By.xpath("//span[contains(text(),'Create an Account')]")).click();
		assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/create/");
		driver.navigate().back();
		assertEquals(driver.getCurrentUrl(), "http://live.techpanda.org/index.php/customer/account/login/");
		driver.navigate().forward();
		assertEquals(driver.getTitle(), "Create New Customer Account");
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