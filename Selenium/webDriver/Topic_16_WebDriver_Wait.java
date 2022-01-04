package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_WebDriver_Wait {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		SetBrowser();
		explicitWait = new WebDriverWait(driver, 15);
	}

	@Test
	public void TC_01_ElementStatus() {
		driver.get("https://www.facebook.com/");
		//Display UI & DOM
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
		//use Presence
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='email']")));
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='email']")).isDisplayed());
		//
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Create New Account']")));
		driver.findElement(By.xpath("//a[text()='Create New Account']")).click();
		//ko cos UI nhung co trong DOM
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		//use Presence
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		
		
		var stalenessTest = driver.findElement(By.xpath("//form[@id='reg']")); 
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")));
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		//ko co Ui k co DOM
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//form[@id='reg']")));
		explicitWait.until(ExpectedConditions.stalenessOf(stalenessTest));
	
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
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}