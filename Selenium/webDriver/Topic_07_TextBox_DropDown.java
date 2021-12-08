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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_TextBox_DropDown {
	WebDriver driver;
	WebDriverWait explicitWait;
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

		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), Name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Gender']/following-sibling::td")).getText(),
				Gender);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(),
				DoB);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
				AddressDisplay);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), City);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),
				State);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), Pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),
				Mobile);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),
				Email);
		//
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		sleepInSecond(1);
		driver.findElement(By.name("cusid")).sendKeys(CustomerID);
		driver.findElement(By.name("AccSubmit")).click();
		sleepInSecond(2);
		// Step8
		Assert.assertEquals(driver.findElement(By.xpath("//input[@name='name']")).getAttribute("value"), Name);
		Assert.assertEquals(driver.findElement(By.xpath("//textarea[@name='addr']")).getText(), Address);
		// Step9
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
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(),
				AddressDisplay);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(),
				City + "Edit");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(),
				State + "Edit");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(),
				"888888");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(),
				Mobile + "Edit");
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(),
				Email + "Edit");

	}

	@Test
	public void TC_02_DropDown_List1() {
		driver.get("https://www.rode.com/wheretobuy");

		Select country = new Select(driver.findElement(By.xpath("//select[@id='where_country']")));
		Assert.assertFalse(country.isMultiple());
		country.selectByValue("Vietnam");
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.xpath("//select[@id='where_country']")).getAttribute("value"),
				"Vietnam");
		Assert.assertEquals(country.getFirstSelectedOption().getText(), "Vietnam");

		driver.findElement(By.id("search_loc_submit")).click();
		sleepInSecond(1);

		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result_count']/span")).getText(),
				driver.findElement(By.xpath("//div[@id='search_results']//div[@class='result_item'][last()]"))
						.getAttribute("data-id"));

		int count = driver.findElements(By.xpath("//div[@id='search_results']//div[@class='result_item']")).size();
		for (int i = 1; i <= count; i++) {
			System.out.print(i + ":");
			System.out.println(driver.findElement(By.xpath("//div[@class='result_item'][" + i
					+ "]//div[@class='map_details_right']//div[@class='store_name']")).getText());
		}

		sleepInSecond(1);
	}

	@Test
	public void TC_03_DropDown_List2() {
		driver.get("https://demo.nopcommerce.com");

		driver.findElement(By.xpath("//a[@class='ico-register']")).click();

		driver.findElement(By.id("gender-male")).click();
		driver.findElement(By.id("FirstName")).sendKeys("Automation");
		driver.findElement(By.id("LastName")).sendKeys("Test");

		Select day = new Select(driver.findElement(By.name("DateOfBirthDay")));
		Select month = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Select year = new Select(driver.findElement(By.name("DateOfBirthYear")));

		Assert.assertEquals(day.getOptions().size(), 32);
		day.selectByValue("10");
		Assert.assertEquals(month.getOptions().size(), 13);
		month.selectByValue("10");
		Assert.assertEquals(year.getOptions().size(), 112);
		year.selectByValue("2010");

		Random rad = new Random();
		String Email = "AutoTest" + Integer.toString(rad.nextInt(9999)) + "@gmail.com";
		driver.findElement(By.id("Email")).sendKeys(Email);
		driver.findElement(By.id("Password")).sendKeys("123abc");
		driver.findElement(By.id("ConfirmPassword")).sendKeys("123abc");
		driver.findElement(By.id("register-button")).click();
		sleepInSecond(1);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),
				"Your registration completed");

		driver.findElement(By.xpath("//a[@class='ico-account']")).click();
		sleepInSecond(1);

		Select monthCheck = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		Assert.assertEquals(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")).getAttribute("value"),
				"10");
		Assert.assertEquals(monthCheck.getFirstSelectedOption().getText(), "October");
		Assert.assertEquals(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")).getAttribute("value"),
				"2010");

		sleepInSecond(1);
	}

	@Test
	public void TC_04_Customer_DropDown_List() {

		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		
		SelectItemInCustomDropDown("//span[@id='number-button']", "//ul[@id='number-menu']/li/div", "19");
		Assert.assertEquals(driver
				.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(),
				"19");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void SelectItemInCustomDropDown(String ButtonXpath, String LoadXpath, String Expected) {
		driver.findElement(By.xpath(ButtonXpath)).click();
		sleepInSecond(2);
		/// Wait items load success
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(LoadXpath)));
		// Get all items
		var Number = driver.findElements(By.xpath(LoadXpath));

		for (WebElement webElement : Number) {
			if (webElement.getText().equals(Expected)) {
				webElement.click();
				sleepInSecond(2);
				break;
			}
		}
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

		explicitWait = new WebDriverWait(driver, 30);
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