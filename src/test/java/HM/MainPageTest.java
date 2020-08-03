package HM;

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

	@Test
	public void TC1() throws IOException		
		{

		hp.readMagazineButton().click();
		Assert.assertTrue(hp.magazineHeader().getText().contains("MAGAZINE"));
		
		}

	
	 @Test()
	 public void TC2() throws IOException, InterruptedException 
	 	{
		 
		 loadHomePage(); 
		 String homePageTitle = driver.getTitle();
		 hp.koszykButton().click(); 
		 clickOnLogo();
		 Assert.assertTrue(homePageTitle.equals(driver.getTitle())); 
		 
		 }
	  
	  @DataProvider 
	  public Object[][] testData() 
	  	{ 
		  
		  Object[][] data = new Object[3][1]; 
		  data[0][0] = "buty"; 
		  data[1][0] = "top"; 
		  data[2][0] = "kapelusz";
	  
		  return data;
		  
	  	}
	  
	  
	  
	 @Test(dataProvider = "testData") 
	 public void TC3(String item)
	 	{
	  
		 hp.searchBox().clear();
		 hp.searchBox().sendKeys(item, Keys.ENTER); 
		 Assert.assertNotNull(srp.countOfItems().getText());
		 Assert.assertTrue(driver.getTitle().contains(item));
	  
	 	}
	  
	  @Test(dataProvider = "testData") 
	  public void TC4(String item) throws InterruptedException
	  	{
	  
		  hp.searchBox().clear(); 
		  hp.searchBox().sendKeys(item);
		  clickOnMagnifierIcon(); 
		  Assert.assertNotNull(srp.countOfItems().getText());
		  Assert.assertTrue(driver.getTitle().contains(item));
	  
	  	}
	  
	  @DataProvider 
	  public Object[][] testData1()
	  	{
		  
		  Object[][] data = new Object[3][2]; 
		  data[0][0] = "@@@@"; 
		  data[0][1] = "BRAK PASUJĄCYCH PRODUKTÓW";
		  data[1][0] = "^^^^"; 
		  data[1][1] = "BRAK PASUJĄCYCH PRODUKTÓW"; 
		  data[2][0] = "%%%%"; 
		  data[2][1] = "BRAK PASUJĄCYCH PRODUKTÓW";
	  
		  return data;
		  
		}
	  
	 @Test(dataProvider = "testData1")
	 public void TC5(String item, String mismatch) throws InterruptedException 
	 	{	
		 
		 hp.searchBox().clear(); 
		 hp.searchBox().sendKeys(item, Keys.ENTER); 
		 Assert.assertEquals(srp.searchResult().getText(), mismatch); 
		 
		 }
	 
	@Test()
	public void TC6()
		{
			loadHomePage();
		
			int childPageCount=0;
			for (int i = 0; i < hp.footerLinks().size(); i++)
				{			
				
				WebElement miniDriver = hp.footerLinks().get(i);
			//	int columnSize = miniDriver.findElements(By.tagName("a")).size();
				int counter = openAllLinksInSeparateTabs(miniDriver);
				childPageCount = childPageCount + counter;
				iterateAllTabsAndGrabTitle();	
			
				}
		
			Assert.assertEquals(childPageCount, 17);
		
		}
	
	 @Test() 
	 public void TC7() throws InterruptedException, IOException
	 	{
		 	data = edd.getData("General", "TC7");
		 
		 	String itemName = data.get(1);
		 	String countOfDesiredItems = data.get(2);
		 
		 	hp.searchBox().clear();
		 	hp.searchBox().sendKeys(itemName, Keys.ENTER);
		 	srp.firstFavoriteIcon().click();		 
		 	hp.favouritesTab().click();
		 	
		 	Assert.assertTrue(fs.countOfItems().getText().contains(countOfDesiredItems));
		
		 }
	 @Test() 
	 public void TC8() 
	 	{
		 	act = new Actions(driver); 		
		 	act.moveToElement(hp.wyprzedazButton()).build().perform();		
		 	act.moveToElement(hp.allSectionsInMainMenu()).click().build().perform();
		 	Assert.assertTrue(srp.searchResult().getText()
				.contains((hp.allSectionsInMainMenu().getText()).toUpperCase()));		
		 }

	
	@AfterTest
	public void teardown() 
		{
			driver.quit();
		}
}
