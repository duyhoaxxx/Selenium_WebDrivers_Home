package webDriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_TextBox_DropDown {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		SetBrowser();
	}

	@Test
	public void TC_01_TextBox() {
		driver.get("http://demo.guru99.com/v4");

		driver.findElement(By.xpath("//input[@name='uid']")).sendKeys("mngr372649");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("AvEjype");
		driver.findElement(By.xpath("//input[@name='btnLogin']")).click();

		Assert.assertEquals(driver.findElement(By.xpath("//marquee[@class='heading3']")).getText(),
				"Welcome To Manager's Page of Guru99 Bank");
		sleepInSecond(1);

		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		sleepInSecond(1);
		// data
		String Name = "Selenium Online";
		String Gender = "male";
		String DoB = "2021-12-08";
		String Address = "180 Yen Hoa\nViet Nam";
		String AddressDisplay = "180 Yen Hoa Viet Nam";
		String City = "Ha Noi";
		String State = "Cau Giay";
		String Pin = "100000";
		String Mobile = "0123456789";
		String Pass = "123456abc";
		Random rand = new Random();
		String Email = "AutoTest" + String.valueOf((rand.nextInt(9999))) + "@gmail.com";
		
		String CustomerID;
		//
		driver.findElement(By.xpath("//input[@name='name']")).sendKeys(Name);
		driver.findElement(By.xpath("//input[@value='m']")).click();
		driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys(Address);
		driver.findElement(By.name("city")).sendKeys(City);
		driver.findElement(By.name("state")).sendKeys(State);
		driver.findElement(By.name("pinno")).sendKeys(Pin);
		driver.findElement(By.name("telephoneno")).sendKeys(Mobile);
		driver.findElement(By.name("emailid")).sendKeys(Email);
		driver.findElement(By.name("password")).sendKeys(Pass);

		WebElement dob = driver.findElement(By.name("dob"));
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('type')", dob);
		dob.sendKeys(DoB);
		driver.findElement(By.name("sub")).click();
		sleepInSecond(2);
		//
		CustomerID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), Name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(), Gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), DoB);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), AddressDisplay);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), City);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), State);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), Pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), Mobile);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), Email);
		//
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		sleepInSecond(1);
		driver.findElement(By.name("cusid")).sendKeys(CustomerID);
		driver.findElement(By.name("AccSubmit")).click();
		sleepInSecond(2);
		//Step8
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='name']")).getAttribute("value"), Name);
		Assert.assertEquals(driver.findElement(By.xpath("//textarea[@name='addr']")).getText(), Address);
		//Step9
		driver.findElement(By.xpath("//textarea[@name='addr']")).sendKeys("Edit");
		driver.findElement(By.name("city")).sendKeys("Edit");
		driver.findElement(By.name("state")).sendKeys("Edit");
		driver.findElement(By.name("pinno")).clear();
		driver.findElement(By.name("pinno")).sendKeys("888888");
		driver.findElement(By.name("telephoneno")).sendKeys("Edit");
		driver.findElement(By.name("emailid")).sendKeys("Edit");
		
		driver.findElement(By.name("sub")).click();
		sleepInSecond(2);
		//
		AddressDisplay = "180 Yen Hoa Viet NamEdit";
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), AddressDisplay);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), City+"Edit");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), State+"Edit");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), "888888");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), Mobile+"Edit");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), Email+"Edit");

	}

	@Test
	public void TC_02_DropDown_List1() {

	}

	@Test
	public void TC_03_DropDown_List2() {

	}
	
	@Test
	public void TC_04_Customer_DropDown_List() {

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