package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Locator {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		// Khoi tao browser
		driver = new FirefoxDriver();
		
		// Set time wait to find element 
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		// Open web
		driver.get("https://www.youtube.com/");
	}

	@Test
	public void TC_01() {
		// Selenium Locator
		driver.findElement(By.cssSelector("input[id='search']")).sendKeys("Thay long Remix");
		sleepInSecond(3);
	}

	@Test
	public void TC_02() {
		
	}

	@Test
	public void TC_03() {
		
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