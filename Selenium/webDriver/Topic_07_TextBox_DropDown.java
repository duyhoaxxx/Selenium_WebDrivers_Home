package webDriver;

import java.util.List;
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
	JavascriptExecutor jsExecutor;
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
	public void TC_04_Jquery_Customer_DropDown_List() {

		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");

		SelectItemInCustomDropDown("//span[@id='number-button']", "//ul[@id='number-menu']/li/div", "19");
		Assert.assertEquals(driver
				.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(),
				"19");
		SelectItemInCustomDropDown("//span[@id='number-button']", "//ul[@id='number-menu']/li/div", "4");
		Assert.assertEquals(driver
				.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(),
				"4");
	}

	@Test
	public void TC_04_VueJS_Customer_DropDown_List() {

		driver.get("https://mikerodham.github.io/vue-dropdowns/");

		driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).click();
		sleepInSecond(2);

		explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@class='dropdown-menu']//a")));
		// Get all items
		var listNames = driver.findElements(By.xpath("//ul[@class='dropdown-menu']//a"));
		for (WebElement webElement : listNames) {
			System.out.println(webElement.getText());
			if (webElement.getText().contains("Second Option")) {
				webElement.click();
				break;
			}
		}
		sleepInSecond(2);
		Assert.assertTrue(
				driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText().contains("Second Option"));

		SelectItemInCustomDropDown("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "First Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText().trim(),
				"First Option");

	}

	@Test
	public void TC_04_ReactJS_Customer_DropDown_List() {

		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		driver.findElement(By.xpath("//div[@class='divider default text']")).click();
		sleepInSecond(2);

		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
				By.xpath("//i[@class='dropdown icon']/following-sibling::div//div//span")));
		// Get all items
		var listNames = driver.findElements(By.xpath("//i[@class='dropdown icon']/following-sibling::div//div//span"));
		for (WebElement webElement : listNames) {
			// System.out.println(webElement.getText());
			if (webElement.getText().equals("Matt")) {
				webElement.click();
				break;
			}
		}
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Matt");

		sleepInSecond(2);
		SelectItemInCustomDropDown("//div[@class='divider text']",
				"//i[@class='dropdown icon']/following-sibling::div//div//span", "Christian");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(), "Christian");
	}

	@Test
	public void TC_04_Angula_Customer_DropDown_List() {

		driver.get(
				"https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		String Execu = "document.querySelector('#games input').value;";

		// FireFox run Executor not add "return"
		var Browser = System.getProperties().toString();
		if (!Browser.contains("webdriver.gecko.driver"))
			Execu = "return " + Execu;

		SelectItemInCustomDropDown("//ejs-dropdownlist[@id='games']/span", "//ul[@id='games_options']/li", "Football");
		Assert.assertEquals(
				driver.findElement(By.xpath("//ejs-dropdownlist[@id='games']/span/input")).getAttribute("aria-label"),
				jsExecutor.executeScript(Execu));

		SelectItemInCustomDropDown("//ejs-dropdownlist[@id='games']/span", "//ul[@id='games_options']/li", "Tennis");
		Assert.assertEquals(
				driver.findElement(By.xpath("//ejs-dropdownlist[@id='games']/span/input")).getAttribute("aria-label"),
				jsExecutor.executeScript(Execu));
		sleepInSecond(2);
	}

	@Test
	public void TC_05_Angula_Customer_DropDown_List() {

		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		driver.manage().window().maximize();
		sleepInSecond(2);

		SelectItemInCustomDropDown("//ng-select[@bindvalue='provinceCode']//span[@class='ng-arrow-wrapper']",
				"//div[contains(@class,'ng-option')]", "Thành phố Hà Nội");

		SelectItemInCustomDropDown("//ng-select[@bindvalue='districtCode']//span[@class='ng-arrow-wrapper']",
				"//div[contains(@class,'ng-option')]", "Quận Cầu Giấy");

		SelectItemInCustomDropDown("//ng-select[@bindvalue='wardCode']//span[@class='ng-arrow-wrapper']",
				"//div[contains(@class,'ng-option')]", "Phường Dịch Vọng");
		sleepInSecond(2);

		Assert.assertEquals(driver
				.findElement(By.xpath(
						"//ng-select[@bindvalue='provinceCode']//span[@class='ng-value-label ng-star-inserted']"))
				.getText(), "Thành phố Hà Nội");
		Assert.assertEquals(driver
				.findElement(By.xpath(
						"//ng-select[@bindvalue='districtCode']//span[@class='ng-value-label ng-star-inserted']"))
				.getText(), "Quận Cầu Giấy");
		Assert.assertEquals(driver
				.findElement(
						By.xpath("//ng-select[@bindvalue='wardCode']//span[@class='ng-value-label ng-star-inserted']"))
				.getText(), "Phường Dịch Vọng");
	}

	@Test
	public void TC_05_Editable_Customer_DropDown_List() {
		driver.get("https://tiemchungcovid19.gov.vn/portal/register-person");
		// driver.manage().window().maximize();
		sleepInSecond(2);

		SelectItemInEditDropDown("//ng-select[@bindvalue='provinceCode']//div[@class='ng-input']/input",
				"//div[contains(@class,'ng-option')]", "Thành phố Hà Nội");

		SelectItemInEditDropDown("//ng-select[@bindvalue='districtCode']//div[@class='ng-input']/input",
				"//div[contains(@class,'ng-option')]", "Quận Cầu Giấy");

		SelectItemInEditDropDown("//ng-select[@bindvalue='wardCode']//div[@class='ng-input']/input",
				"//div[contains(@class,'ng-option')]", "Phường Dịch Vọng");
		sleepInSecond(2);

		Assert.assertEquals(driver
				.findElement(By.xpath(
						"//ng-select[@bindvalue='provinceCode']//span[@class='ng-value-label ng-star-inserted']"))
				.getText(), "Thành phố Hà Nội");
		Assert.assertEquals(driver
				.findElement(By.xpath(
						"//ng-select[@bindvalue='districtCode']//span[@class='ng-value-label ng-star-inserted']"))
				.getText(), "Quận Cầu Giấy");
		Assert.assertEquals(driver
				.findElement(
						By.xpath("//ng-select[@bindvalue='wardCode']//span[@class='ng-value-label ng-star-inserted']"))
				.getText(), "Phường Dịch Vọng");
	}

	@Test
	public void TC_05_Editable_Customer_DropDown_List2() {
		driver.get("http://indrimuska.github.io/jquery-editable-select/");
		sleepInSecond(2);

		SelectItemInEditDropDown("//div[@id='default-place']/input",
				"//div[@data-effects='default']//ul//li[contains(@class,'visible')]", "Audi");

		Assert.assertEquals(driver.findElement(By.xpath("//div[@data-effects='default']/input")).getAttribute("value"),
				"Audi");
	}

	@Test
	public void TC_06_Multiple_Customer_DropDown_List() {
		driver.get("http://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
		sleepInSecond(2);

		String[] Months3 = { "May", "June", "July" };
		SelectMultiDropDown("//label[contains(.,'Group Select')]/parent::div/preceding-sibling::div//button",
				"//label[contains(.,'Group Select')]/parent::div/preceding-sibling::div//li//span", Months3);

		String XPath = "//label[contains(.,'Group Select')]/parent::div/preceding-sibling::div//button//span";
		Assert.assertTrue(driver.findElement(By.xpath(XPath)).getText().contains(Months3[0]));
		Assert.assertTrue(driver.findElement(By.xpath(XPath)).getText().contains(Months3[1]));
		Assert.assertTrue(driver.findElement(By.xpath(XPath)).getText().contains(Months3[2]));
		//
		driver.navigate().refresh();
		sleepInSecond(2);
		String[] Months5 = { "May", "June", "July", "December", "September" };
		SelectMultiDropDown("//label[contains(.,'Group Select')]/parent::div/preceding-sibling::div//button",
				"//label[contains(.,'Group Select')]/parent::div/preceding-sibling::div//li//span", Months5);
		Assert.assertTrue(driver.findElement(By.xpath(XPath)).getText().contains("5 of 12 selected"));
		//
		driver.navigate().refresh();
		sleepInSecond(2);
		String[] AllMonths = { "May", "June", "July", "December", "September", "January", "February", "March", "April",
				"August", "October", "November" };
		SelectMultiDropDown("//label[contains(.,'Group Select')]/parent::div/preceding-sibling::div//button",
				"//label[contains(.,'Group Select')]/parent::div/preceding-sibling::div//li//span", AllMonths);
		Assert.assertTrue(driver.findElement(By.xpath(XPath)).getText().contains("All selected"));
		//
		driver.navigate().refresh();
		sleepInSecond(2);
		String[] AllMonthString = { "[Select all]"};
		SelectMultiDropDown("//label[contains(.,'Group Select')]/parent::div/preceding-sibling::div//button",
				"//label[contains(.,'Group Select')]/parent::div/preceding-sibling::div//li//span", AllMonthString);
		Assert.assertTrue(driver.findElement(By.xpath(XPath)).getText().contains("All selected"));

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
		var ListWebElements = driver.findElements(By.xpath(LoadXpath));
		for (WebElement webElement : ListWebElements) {
			if (webElement.getText().trim().equals(Expected)) {
				if (webElement.isDisplayed()) {
					webElement.click();
				} else {
					// Scroll to element
					jsExecutor.executeScript("arguments[0].scrollIntoView(true);", webElement);
					sleepInSecond(1);
					jsExecutor.executeScript("arguments[0].click();", webElement);
				}
				sleepInSecond(1);
				break;
			}
		}
	}

	public void SelectItemInEditDropDown(String ButtonXpath, String LoadXpath, String Expected) {

		driver.findElement(By.xpath(ButtonXpath)).clear();
		sleepInSecond(1);
		driver.findElement(By.xpath(ButtonXpath)).sendKeys(Expected);
		sleepInSecond(2);
		/// Wait items load success
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(LoadXpath)));
		// Get all items
		var ListWebElements = driver.findElements(By.xpath(LoadXpath));

		for (WebElement webElement : ListWebElements) {
			if (webElement.getText().trim().equals(Expected)) {
				webElement.click();
				sleepInSecond(2);
				break;
			}
		}
	}

	public void SelectMultiDropDown(String ButtonXpath, String LoadXpath, String[] Expected) {
		driver.findElement(By.xpath(ButtonXpath)).click();
		sleepInSecond(2);
		/// Wait items load success
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(LoadXpath)));
		// Get all items
		var ListWebElements = driver.findElements(By.xpath(LoadXpath));

		for (WebElement webElement : ListWebElements) {
			for (String Month : Expected) {
				if (webElement.getText().trim().equals(Month)) {
					webElement.click();
					sleepInSecond(2);
				}
			}
		}
		driver.findElement(By.xpath(ButtonXpath)).click();
		sleepInSecond(2);
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