package webDriver;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
		// Display UI & DOM
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='email']")));
		// use Presence
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='email']")));
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='email']")).isDisplayed());
		//
		explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Create New Account']")));
		driver.findElement(By.xpath("//a[text()='Create New Account']")).click();
		// ko cos UI nhung co trong DOM
		explicitWait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));
		// use Presence
		explicitWait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@name='reg_email_confirmation__']")));

		var stalenessTest = driver.findElement(By.xpath("//form[@id='reg']"));
		explicitWait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")));
		driver.findElement(By.xpath("//div[text()='Sign Up']/parent::div/preceding-sibling::img")).click();
		// ko co Ui k co DOM
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//form[@id='reg']")));
		explicitWait.until(ExpectedConditions.stalenessOf(stalenessTest));

		var result = driver.findElement(By.xpath("//div[@id='pageFooter']/ul[1]/li[1]"));
		var results = driver.findElements(By.xpath("//div[@id='pageFooter']/ul[1]//li"));
		Assert.assertEquals(result.getText(), "English (UK)");
		assertTrue(results.size() > 2);
	}

	@Test
	public void TC_02_implicitlyWait() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(By.xpath("//button[text()='Start']")).click();

		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		Assert.assertTrue(driver.findElement(By.xpath("//h4")).isDisplayed());
	}

	@Test
	public void TC_03_StaticWait() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(By.xpath("//button[text()='Start']")).click();

		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='loading']")).isDisplayed());
		sleepInSecond(5);
		Assert.assertTrue(driver.findElement(By.xpath("//h4")).isDisplayed());
	}

	@Test
	public void TC_04_ExplicitWait() {
		driver.get("https://automationfc.github.io/dynamic-loading/");

		driver.findElement(By.xpath("//button[text()='Start']")).click();

		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='loading']")));
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h4")));

		// Assert.assertTrue(driver.findElement(By.xpath("//div[@id='loading']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//h4")).isDisplayed());
	}

	@Test
	public void TC_05_ExplicitWait() {
		driver.get(
				"https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		explicitWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='calendarContainer']")));
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='label']")).getText(),
				"No Selected Dates to display.");
		var listDays = driver.findElements(By.xpath("//td"));
		for (WebElement day : listDays) {
			if (day.getAttribute("title").contains("10")) {
				day.click();
				break;
			}
		}
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(
				By.xpath("//div[@id='webinar-banner']/preceding-sibling::div//div[@class='raDiv']")));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[@class='rcSelected']//a")));
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='label']")).getText(),
				"Monday, January 10, 2022");
	}

	@Test
	public void TC_06_ExplicitWait() {
		driver.get("https://gofile.io/?t=uploadFiles");
		explicitWait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//button[text()='Add files']")));
		String ParentTab = driver.getWindowHandle();
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(projectPath + "\\Media\\20222.png");
		explicitWait
				.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//td[text()='Download page']")));
		Assert.assertEquals(driver.findElement(By.xpath("//h5")).getText(),
				"Your files have been successfully uploaded");
		driver.findElement(By.xpath("//td[text()='Download page']/following-sibling::td/a")).click();

		var AllTab = driver.getWindowHandles();
		for (String tab : AllTab) {
			if (!tab.equals(ParentTab))
				driver.switchTo().window(tab);
		}
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='contentName']")));
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='contentName']")).getText(), "20222.png");
	}

	@Test
	public void TC_07_FluentWait() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='javascript_countdown_time']")));
	}

	@Test
	public void TC_08_FluentWait() {

	}

	@Test
	public void TC_09_PageReady() {

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

		// driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}