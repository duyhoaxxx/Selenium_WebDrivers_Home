package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_User_Interaction {
	WebDriver driver;
	Actions action;
	Alert alert;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExcutor;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		SetBrowser();
		action = new Actions(driver);
		jsExcutor = (JavascriptExecutor) driver;
		explicitWait = new WebDriverWait(driver, 30);
	}

	@Test
	public void TC_01_Hover_to_Element() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		action.moveToElement(driver.findElement(By.id("age"))).perform();
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(),
				"We ask for your age only for statistical purposes.");
	}

	@Test
	public void TC_02_Hover() {
		driver.get("http://www.myntra.com/");
		action.moveToElement(driver.findElement(By.xpath("//a[@data-group='kids']"))).perform();
		sleepInSecond(1);
		action.click(driver.findElement(By.xpath("//a[@data-reactid='451']"))).perform();
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//h1[@class='title-title']")).isDisplayed());
	}

	@Test
	public void TC_03_Hover() {
		driver.get("https://www.fahasa.com/");
		driver.manage().window().maximize();
		sleepInSecond(2);
		By xpath = By.xpath("//div[@class='header-breadcrumbs  background-menu-homepage  ']//a[@title='Đồ Chơi']");
		By xpathVerify = By
				.xpath("//div[@class='header-breadcrumbs  background-menu-homepage  ']//span[text()='ĐỒ CHƠI KHÁC']");
		action.moveToElement(driver.findElement(xpath)).perform();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(xpathVerify).isDisplayed());

	}

	@Test
	public void TC_04_Click_And_Hold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		var Numbers = driver.findElements(By.xpath("//ol//li"));
		action.clickAndHold(Numbers.get(0)).moveToElement(Numbers.get(7)).release().perform();
		sleepInSecond(2);
		var Results = driver.findElements(By.xpath("//ol//li[contains(@class,'selected')]"));
		Assert.assertEquals(Results.size(), 8);
	}

	@Test
	public void TC_05_Click_And_Hold_Random() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		var Numbers = driver.findElements(By.xpath("//ol//li"));
		action.keyDown(Keys.CONTROL).perform();
		// action.clickAndHold(Numbers.get(0)).moveToElement(Numbers.get(7)).release().perform();
		action.click(Numbers.get(0)).click(Numbers.get(2)).click(Numbers.get(10)).click(Numbers.get(5)).perform();
		action.keyUp(Keys.CONTROL).perform();
		sleepInSecond(2);

		var Results = driver.findElements(By.xpath("//ol//li[contains(@class,'selected')]"));
		Assert.assertEquals(Results.size(), 4);
	}

	@Test
	public void TC_06_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		// Scroll to Element
		jsExcutor.executeScript("arguments[0].scrollIntoView(true);",
				driver.findElement(By.xpath("//button[text()='Double click me']")));
		sleepInSecond(2);
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.id("demo")).isDisplayed());
	}

	@Test
	public void TC_07_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		action.contextClick(driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral']")))
				.perform();
		action.moveToElement(driver.findElement(By.xpath("//span[text()='Quit']/parent::li"))).perform();
		sleepInSecond(1);
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Quit']/parent::li")).getAttribute("class")
				.contains("context-menu-visible"));
		action.click(driver.findElement(By.xpath("//span[text()='Quit']/parent::li"))).perform();
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		sleepInSecond(3);
		Assert.assertEquals(alert.getText(), "clicked: quit");
		alert.accept();
		Assert.assertTrue(
				driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral']")).isDisplayed());
	}

	@Test
	public void TC_08_Drag_And_Drop_Element_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		var smallCircle = driver.findElement(By.id("draggable"));
		var bigCircle = driver.findElement(By.id("droptarget"));
		action.dragAndDrop(smallCircle, bigCircle).perform();
		sleepInSecond(3);
		Assert.assertEquals(bigCircle.getText(), "You did great!");
		String color = bigCircle.getCssValue("background-color");
		color = Color.fromString(color).asHex().toLowerCase();
		Assert.assertEquals(color, "#03a9f4");
	}

	@Test
	public void TC_09_Drag_And_Drop_Element_HTML5() {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
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