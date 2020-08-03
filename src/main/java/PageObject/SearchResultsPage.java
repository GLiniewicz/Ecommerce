package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SearchResultsPage {
	
	WebDriver driver;
	
	

   
    public SearchResultsPage(WebDriver driver) {

        this.driver=driver;
        }

	
   
    
    private By searchResult = By.cssSelector("h1.heading");
    private By countOfItems = By.xpath("//div[@class='sidebar-plus-content']/aside/nav/ul/li[1]/label/span[2]");
    private By firstFavoriteIcon = By.xpath("//li[1]//article[1]//div[1]//a[1]//div[1]//button[1]");
     
   public WebElement searchResult() {
	   
	   return driver.switchTo().activeElement().findElement(searchResult);
   }
 public WebElement countOfItems() {
	   
	   return driver.switchTo().activeElement().findElement(countOfItems);
   }
   public WebElement firstFavoriteIcon() throws InterruptedException {
	   Thread.sleep(1000);
	   return driver.switchTo().activeElement().findElement(firstFavoriteIcon);
  }

  
}
