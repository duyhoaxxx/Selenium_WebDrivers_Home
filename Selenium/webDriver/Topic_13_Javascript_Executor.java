package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class Topic_13_Javascript_Executor {
	WebDriver driver;
	JavascriptExecutor jsExecutor;
	WebDriverWait explixitWait;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		SetBrowser();
		jsExecutor = (JavascriptExecutor) driver;
		explixitWait = new WebDriverWait(driver, 10);
	}

	@Test
	public void TC_01_Javascript_Executor() {
		navigateToUrlByJS("http://live.techpanda.org/");
		String domainTest = (String) executeForBrowser("return document.domain;");
		System.out.println(domainTest);
		Assert.assertEquals(domainTest, "live.techpanda.org");

		String urlTest = (String) executeForBrowser("return document.URL;");
		System.out.println(urlTest);
		Assert.assertEquals(urlTest, "http://live.techpanda.org/");

		clickToElementByJS("//a[text()='Mobile']");
		clickToElementByJS(
				"//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//span[text()='Add to Cart']");
		Assert.assertTrue(areExpectedTextInInnerText("Samsung Galaxy was added to your shopping cart."));
		clickToElementByJS("//a[text()='Customer Service']");
		Assert.assertEquals((String) executeForBrowser("return document.title;"), "Customer Service");
		scrollToBottomPage();
		sendkeyToElementByJS("//input[@id='newsletter']", "test.auto@gmail.com");
		clickToElementByJS("//span[text()='Subscribe']");
		Assert.assertTrue(areExpectedTextInInnerText("Thank you for your subscription."));
		navigateToUrlByJS("http://demo.guru99.com/v4/");
		// sleepInSecond(2);
		explixitWait.until(ExpectedConditions.urlToBe("http://demo.guru99.com/v4/"));
		Assert.assertEquals((String) executeForBrowser("return document.domain;"), "demo.guru99.com");

		sleepInSecond(2);
	}

	@Test
	public void TC_02_HTML5_validation_message() {
		navigateToUrlByJS("https://automationfc.github.io/html5/index.html");
		clickToElementByJS("//input[@class='btn']");
		Assert.assertEquals(getElementValidationMessage("//input[@id='fname']"), "Please fill out this field.");
		//
		sendkeyToElementByJS("//input[@id='fname']", "Auto Test");
		clickToElementByJS("//input[@class='btn']");
		Assert.assertEquals(getElementValidationMessage("//input[@id='pass']"), "Please fill out this field.");
		//
		sendkeyToElementByJS("//input[@id='pass']", "123456");
		clickToElementByJS("//input[@class='btn']");
		Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please fill out this field.");
		//
		sendkeyToElementByJS("//input[@id='em']", "123@!#$");
		clickToElementByJS("//input[@class='btn']");
		Assert.assertEquals(getElementValidationMessage("//input[@id='em']"),
				"A part following '@' should not contain the symbol '!'.");
		// System.out.println(getElementValidationMessage("//input[@id='em']"));
		//
		sendkeyToElementByJS("//input[@id='em']", "123@456");
		clickToElementByJS("//input[@class='btn']");
		Assert.assertEquals(getElementValidationMessage("//input[@id='em']"), "Please match the requested format.");
		// System.out.println(getElementValidationMessage("//input[@id='em']"));
		//
		sendkeyToElementByJS("//input[@id='em']", "autotest@gmail.com");
		clickToElementByJS("//input[@class='btn']");
		Assert.assertEquals(getElementValidationMessage("//select"), "Please select an item in the list.");
		sleepInSecond(2);
	}

	@Test
	public void TC_03_HTML5_validation_message() {
		navigateToUrlByJS("https://sieuthimaymocthietbi.com/account/register");

		sleepInSecond(2);
	}

	@Test
	public void TC_04_Remove_Attribute() {
		navigateToUrlByJS("http://demo.guru99.com/v4");
		sendkeyToElementByJS("//input[@name='uid']", "mngr377365");
		sendkeyToElementByJS("//input[@name='password']", "Abedemu");
		clickToElementByJS("//input[@name='btnLogin']");
		clickToElementByJS("//a[text()='New Customer']");

		sendkeyToElementByJS("//input[@name='name']", "Kane");
		removeAttributeInDOM("//input[@name='dob']", "type");
		sendkeyToElementByJS("//input[@name='dob']", "04/26/1990");
		sendkeyToElementByJS("//textarea[@name='addr']", "Viet Nammmm");
		//getElement("//textarea[@name='addr']").sendKeys("Viet Nammmm");
		sendkeyToElementByJS("//input[@name='city']", "Ha Noi");
		sendkeyToElementByJS("//input[@name='state']", "Cau Giay");
		sendkeyToElementByJS("//input[@name='pinno']", "100000");
		sendkeyToElementByJS("//input[@name='telephoneno']", "0868686886");
		sendkeyToElementByJS("//input[@name='emailid']", "kane1123@gmail.com");
		sendkeyToElementByJS("//input[@name='password']", "123456");

		clickToElementByJS("//input[@name='sub']");
		sleepInSecond(120);
		Assert.assertTrue(areExpectedTextInInnerText("Customer Registered Successfully!!!"));

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

	//////////////////////////////////////////////////

	public Object executeForBrowser(String javaScript) {
		return jsExecutor.executeScript(javaScript);
	}

	public String getInnerText() {
		return (String) jsExecutor.executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public void hightlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				"border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void scrollToElementOnTop(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void scrollToElementOnDown(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(false);", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public boolean isImageLoaded(String locator) {
		boolean status = (boolean) jsExecutor.executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getElement(locator));
		if (status) {
			return true;
		}
		return false;
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}

}