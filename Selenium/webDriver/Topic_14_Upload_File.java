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

public class Topic_14_Upload_File {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		SetBrowser();
	}

	@Test
	public void TC_01_Upload_File_By_Senkeys() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(projectPath + "\\Media\\20221.png" + "\n"
				+ projectPath + "\\Media\\20222.png" + "\n" + projectPath + "\\Media\\20223.png");
		// driver.findElement(By.xpath("//input[@type='file']")).sendKeys(projectPath +
		// "\\Media\\20222.png");
		// driver.findElement(By.xpath("//input[@type='file']")).sendKeys(projectPath +
		// "\\Media\\20223.png");
		sleepInSecond(2);
		Assert.assertEquals(driver.findElements(By.xpath("//tr[@class='template-upload fade image in']")).size(), 3);

		var upload = driver
				.findElements(By.xpath("//tr[@class='template-upload fade image in']//span[text()='Start']"));
		for (WebElement webElement : upload) {
			webElement.click();
		}

		// Assert.assertTrue(driver.findElement(By.xpath("//tr[@class='template-download
		// fade image in']//span[text()='Delete']")).isDisplayed());
		sleepInSecond(5);
		Assert.assertEquals(driver.findElements(By.xpath("//tr[@class='template-download fade image in']")).size(), 3);

		sleepInSecond(2);
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

	public void sleepInSecond(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}