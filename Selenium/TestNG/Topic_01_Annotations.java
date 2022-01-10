package TestNG;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

public class Topic_01_Annotations {
	@Test(dataProvider = "user_pass")
	public void f(Integer n, String s) {
	}

	@Test
	public void TC_01() {
		System.out.println("Hello TC _01");

	}

	@Test(groups = "user")
	public void TC_02() {
		System.out.println("Hello TC _02");

	}

	@Test(groups = { "user", "admin" })
	public void TC_03() {
		System.out.println("Hello TC _03");

	}

	@BeforeMethod
	public void beforeMethod() {
	}

	@AfterMethod
	public void afterMethod() {
	}

	@DataProvider
	public Object[][] user_pass() {
		return new Object[][] { 
			{ "autoTest", "123456" }, 
			{ "Kane", "123!@#" } };
	}

	@BeforeClass(alwaysRun = true)
	public void beforeClass() {
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
	}

	@BeforeTest
	public void beforeTest() {
	}

	@AfterTest
	public void afterTest() {
	}

	@BeforeSuite
	public void beforeSuite() {
	}

	@AfterSuite
	public void afterSuite() {
	}

}
