package Tests;

import java.io.IOException;
import java.util.ArrayList;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import PageObject.Favourites;
import PageObject.HomePage;
import PageObject.SearchResultsPage;
import Resources.Base;
import Resources.ExcelDataDriven;

public class MainPageTest extends Base {

	public WebDriver driver;
	public HomePage hp;
	public SearchResultsPage srp;
	public Favourites fs;
	public Actions act;
	public ExcelDataDriven edd;
	public ArrayList<String> data;

	@BeforeTest
	public void config() throws IOException {
		driver = initializeDriver();
		hp = new HomePage(driver);
		srp = new SearchResultsPage(driver);
		fs = new Favourites(driver);
		edd = new ExcelDataDriven();
		loadHomePage();
	}

	/**
	 * Verification of the Magazine link - Validation that the link redirects the
	 * user to the expected page
	 */
	@Test()
	public void TC1() {
		hp.readMagazineButton().click();
		Assert.assertTrue(hp.magazineHeader().getText().contains("MAGAZINE"));
	}

	/**
	 * Logo veryfication - Checking if the link attached to the logo button
	 * redirects the user to the home page
	 */
	@Test()
	public void TC2() {
		loadHomePage();
		String homePageTitle = driver.getTitle();
		hp.koszykButton().click();
		waitForJStoLoad(driver);
		clickOnLogo();
		Assert.assertTrue(homePageTitle.equals(driver.getTitle()));
	}

	/**
	 * Search results - Validate that the search results will appear properly after
	 * provide item name in "Szukaj produktów" box and press ENTER on the keyboard
	 */
	@Test(dataProvider = "testData")
	public void TC3(String item) {
		hp.searchBox().clear();
		hp.searchBox().sendKeys(item, Keys.ENTER);
		Assert.assertNotNull(srp.countOfItems().getText());
		Assert.assertTrue(driver.getTitle().contains(item));
	}

	/**
	 * Search results - Validation that the search results will appear properly
	 * after provide item name in "Szukaj produktów" box and click on magnifier icon
	 */
	@Test(dataProvider = "testData")
	public void TC4(String item) {
		loadHomePage();
		hp.searchBox().clear();
		hp.searchBox().sendKeys(item);
		waitForJStoLoad(driver);
		clickOnMagnifierIcon();
		Assert.assertNotNull(srp.countOfItems().getText());
		Assert.assertTrue(driver.getTitle().contains(item));
	}

	/**
	 * Search results - Validation that the search results will appear after provide
	 * incorrect input data
	 */
	@Test(dataProvider = "testData1")
	public void TC5(String item, String mismatch) {
		loadHomePage();
		hp.searchBox().clear();
		hp.searchBox().sendKeys(item, Keys.ENTER);
		Assert.assertEquals(srp.searchResult().getText(), mismatch);
	}

	/**
	 * Footer - Validation that the all links in a footer are working properly
	 */
	@Test()
	public void TC6() {
		loadHomePage();
		int childPageCount = 0;
		for (int i = 0; i < hp.footerLinks().size(); i++) {
			WebElement miniDriver = hp.footerLinks().get(i);
			int counter = openAllLinksInSeparateTabs(miniDriver);
			childPageCount = childPageCount + counter;
			iterateAllTabsAndGrabTitle();
		}
		Assert.assertEquals(childPageCount, 17);
	}

	/**
	 * Favourites - Validate that the item marked as favorite will appear in
	 * Favourites tab
	 * 
	 * @throws IOException
	 */
	@Test()
	public void TC7() throws IOException {
		loadHomePage();
		data = edd.getData("General", "TC7");
		String itemName = data.get(1);
		String countOfDesiredItems = data.get(2);
		String pageTitle = data.get(3);
		hp.searchBox().clear();
		hp.searchBox().sendKeys(itemName, Keys.ENTER);
		srp.firstFavoriteIcon().click();
		hp.favouritesTab().click();
		Assert.assertTrue(driver.getTitle().contains(pageTitle));
		Assert.assertTrue(fs.countOfItems().getText().contains(countOfDesiredItems));
	}

	/**
	 * Main menu-Validate that the proper pages are opened when clicking on specific
	 * values
	 */
	@Test()
	public void TC8() {
		loadHomePage();
		act = new Actions(driver);
		act.moveToElement(hp.wyprzedazButton()).build().perform();
		act.moveToElement(hp.lazienkaButton()).click().build().perform();
		Assert.assertTrue(srp.searchResult().getText().contains((hp.lazienkaButton().getText()).toUpperCase()));
	}

	@AfterTest
	public void teardown() {
		driver.quit();
	}

	@DataProvider
	public Object[][] testData() {
		Object[][] data = new Object[3][1];
		data[0][0] = "buty";
		data[1][0] = "top";
		data[2][0] = "kapelusz";
		return data;
	}

	@DataProvider
	public Object[][] testData1() {
		Object[][] data = new Object[3][2];
		data[0][0] = "@@@@";
		data[0][1] = "BRAK PASUJĄCYCH PRODUKTÓW";
		data[1][0] = "^^^^";
		data[1][1] = "BRAK PASUJĄCYCH PRODUKTÓW";
		data[2][0] = "%%%%";
		data[2][1] = "BRAK PASUJĄCYCH PRODUKTÓW";
		return data;
	}
}
