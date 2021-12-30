package webDriver;

import java.io.IOException;
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
	String image1 = projectPath + "\\Media\\20221.png";
	String image2 = projectPath + "\\Media\\20222.png";
	String image3 = projectPath + "\\Media\\20223.png";
	String firefoxUploadFileOnTime = projectPath + "\\AutoIT\\firefoxUploadOneTime.exe";
	String firefoxUploadFileMulti = projectPath + "\\AutoIT\\firefoxUploadMultiple.exe";
	String chormeUploadFileOnTime = projectPath + "\\AutoIT\\chromeUploadOneTime.exe";
	String chormeUploadFileMulti = projectPath + "\\AutoIT\\chromeUploadMultiple.exe";

	@BeforeClass
	public void beforeClass() {
		SetBrowser();
	}

	@Test
	public void TC_01_Upload_File_By_Senkeys() {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(image1 + "\n" + image2 + "\n" + image3);
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
	public void TC_02_Upload_File_By_AutoIT() throws IOException {
		driver.get("https://blueimp.github.io/jQuery-File-Upload/");
		driver.findElement(By.xpath("//span[@class='btn btn-success fileinput-button']")).click();

		Runtime.getRuntime().exec(new String[] { chormeUploadFileOnTime, image1 });

		sleepInSecond(5);
		Assert.assertEquals(driver.findElements(By.xpath("//tr[@class='template-upload fade image in']")).size(), 1);

		driver.findElement(By.xpath("//span[@class='btn btn-success fileinput-button']")).click();

		Runtime.getRuntime().exec(new String[] { chormeUploadFileMulti, image2, image3 });

		sleepInSecond(5);
		Assert.assertEquals(driver.findElements(By.xpath("//tr[@class='template-upload fade image in']")).size(), 3);

		sleepInSecond(2);
	}

	@Test
	public void TC_04_Upload_File() {
		driver.get("https://gofile.io/?t=uploadFiles");
		Assert.assertFalse(driver.findElement(By.xpath("//h5")).isDisplayed());
		driver.findElement(By.xpath("//input[@type='file']")).sendKeys(image1 + "\n" + image2);
		sleepInSecond(5);
		Assert.assertTrue(driver.findElement(By.xpath("//h5")).isDisplayed());

		sleepInSecond(2);
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