package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Button_RadioButton_CheckBox_Alert {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		SetBrowser();
	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create");
		sleepInSecond(2);
		driver.findElement(By.xpath("//a[contains(.,'Đăng nhập')]")).click();
		var ButtonLogin = "//button[@class='fhs-btn-login']";
		// Button disable
		Assert.assertFalse(driver.findElement(By.xpath(ButtonLogin)).isEnabled());

		driver.findElement(By.id("login_username")).sendKeys("autotest123@gmail.com");
		driver.findElement(By.id("login_password")).sendKeys("autotest123");
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath(ButtonLogin)).isEnabled());
		Assert.assertTrue(
				driver.findElement(By.xpath(ButtonLogin)).getCssValue("background").contains("rgb(201, 33, 39)"));
		Assert.assertEquals(Color.fromString(driver.findElement(By.xpath(ButtonLogin)).getCssValue("background-color"))
				.asHex().toUpperCase(), "#C92127");
		// Button enable
		driver.navigate().refresh();
		sleepInSecond(2);
		driver.findElement(By.xpath("//a[contains(.,'Đăng nhập')]")).click();
		jsExecutor.executeScript("arguments[0].removeAttribute('disabled')", driver.findElement(By.xpath(ButtonLogin)));
		driver.findElement(By.xpath(ButtonLogin)).click();
		sleepInSecond(1);
		Assert.assertTrue(driver
				.findElement(By.xpath("//div[@class='fhs-input-box checked-error']//div[@class='fhs-input-alert']"))
				.isDisplayed());
		Assert.assertTrue(driver
				.findElement(By.xpath(
						"//div[@class='fhs-input-box fhs-input-display checked-error']//div[@class='fhs-input-alert']"))
				.isDisplayed());
		sleepInSecond(2);
	}

	@Test
	public void TC_02_Default_CheckBox() {
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		sleepInSecond(2);
		driver.findElement(By.xpath("//input[@id='eq5']")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='eq5']")).isSelected());
		driver.findElement(By.xpath("//input[@id='eq5']")).click();
		sleepInSecond(2);
		Assert.assertFalse(driver.findElement(By.xpath("//input[@id='eq5']")).isSelected());
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		sleepInSecond(2);
		driver.findElement(By.xpath("//input[@id='engine3']")).click();
		sleepInSecond(2);
		if (!driver.findElement(By.xpath("//input[@id='engine3']")).isSelected())
			driver.findElement(By.xpath("//input[@id='engine3']")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='engine3']")).isSelected());
		sleepInSecond(2);
	}

	@Test
	public void TC_03_Custom_CheckBox() {
		driver.get("https://material.angular.io/components/radio/examples");
		sleepInSecond(2);
		driver.findElement(By.xpath("//mat-radio-button[@id='mat-radio-4']")).click();
		sleepInSecond(2);
		if (!driver.findElement(By.xpath("//mat-radio-button[@id='mat-radio-4']")).isSelected())
			driver.findElement(By.xpath("//mat-radio-button[@id='mat-radio-4']")).click();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='mat-radio-4-input']")).isSelected());

		
		jsExecutor.executeScript("arguments[0].click()", driver.findElement(By.xpath("//input[@id='mat-radio-2-input']")));
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='mat-radio-2-input']")).isSelected());
		//
		driver.get("https://material.angular.io/components/checkbox/examples");
		sleepInSecond(2);
		jsExecutor.executeScript("arguments[0].click()", webElement("//input[@id='mat-checkbox-1-input']"));
		jsExecutor.executeScript("arguments[0].click()", webElement("//input[@id='mat-checkbox-2-input']"));
		sleepInSecond(2);
		Assert.assertTrue(webElement("//input[@id='mat-checkbox-1-input']").isSelected());
		Assert.assertTrue(webElement("//input[@id='mat-checkbox-2-input']").isSelected());
		jsExecutor.executeScript("arguments[0].click()", webElement("//input[@id='mat-checkbox-1-input']"));
		sleepInSecond(2);
		Assert.assertFalse(webElement("//input[@id='mat-checkbox-1-input']").isSelected());
		jsExecutor.executeScript("arguments[0].click()", webElement("//input[@id='mat-checkbox-2-input']"));
		sleepInSecond(2);
		Assert.assertFalse(webElement("//input[@id='mat-checkbox-2-input']").isSelected());
	}

	@Test
	public void TC_04_Custom_CheckBox() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		sleepInSecond(2);
		if(webElement("//div[@id='i17']").getAttribute("aria-checked")!="true") {
			webElement("//div[@id='i17']").click();
		}
		sleepInSecond(2);
		Assert.assertEquals(webElement("//div[@id='i17']").getAttribute("aria-checked"),"true");
		sleepInSecond(2);
		////
		var Checkbox = driver.findElements(By.xpath("//div[@role='checkbox']"));
		for (WebElement temp : Checkbox) {
			temp.click();
			sleepInSecond(1);
			Assert.assertEquals(temp.getAttribute("aria-checked"),"true");
		}

	}

	@Test
	public void TC_05() {

	}

	@Test
	public void TC_06() {

	}

	@Test
	public void TC_07() {

	}

	@Test
	public void TC_08() {

	}

	@Test
	public void TC_09() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public WebElement webElement (String by) {
		return driver.findElement(By.xpath(by));
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

		jsExecutor = (JavascriptExecutor) driver;
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