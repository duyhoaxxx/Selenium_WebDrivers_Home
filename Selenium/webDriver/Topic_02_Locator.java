package webDriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Locator {
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");

	@BeforeClass
	public void beforeClass() {
		SetBrowser();
	}

	@Test
	public void TC_01_Test_VND() {
		driver.get("https://banggia.vndirect.com.vn/chung-khoan/danh-muc");
		String CodeCP = "ctg".toUpperCase();
		String IDCP = CodeCP + "matchP";
		String PercentCP = CodeCP + "percent";

		while (true) {
			var ResultPrice = driver.findElement(By.xpath("//tbody[@id='banggia-khop-lenh-body']//tr[@id='" + CodeCP
					+ "']//td[@class='has-tooltip price-tooltip tooltip-bottom cell-highlight']//span[@id='" + IDCP
					+ "']")).getText();
			var ResultPercent = driver
					.findElement(By.xpath("//tbody[@id='banggia-khop-lenh-body']//tr[@id='" + CodeCP
							+ "']//td[@class='cell-highlight']//span[@id='" + PercentCP + "']"))
					.getAttribute("innerText");
			System.out.print(ResultPrice + " ~~~~~~~~~~");
			System.out.println(ResultPercent);
			sleepInSecond(30);
		}
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