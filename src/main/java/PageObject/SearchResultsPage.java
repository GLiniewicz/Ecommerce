package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Resources.Base;

public class SearchResultsPage {

	WebDriver driver;
	Base bs;

	public SearchResultsPage(WebDriver driver) {

		this.driver = driver;
	}

	private By searchResult = By.cssSelector("h1.heading");
	private By countOfItems = By.xpath("//div[@class='sidebar-plus-content']/aside/nav/ul/li[1]/label/span[2]");
	private By firstFavoriteIcon = By.xpath("//li[1]//article[1]//div[1]//a[1]//div[1]//button[1]");

	public WebElement searchResult() {
		bs = new Base();
		bs.waitForJStoLoad(driver);
		return driver.switchTo().activeElement().findElement(searchResult);
	}

	public WebElement countOfItems() {
		bs = new Base();
		bs.waitForJStoLoad(driver);
		return driver.switchTo().activeElement().findElement(countOfItems);
	}

	public WebElement firstFavoriteIcon() {
		bs = new Base();
		bs.waitForJStoLoad(driver);
		return driver.switchTo().activeElement().findElement(firstFavoriteIcon);
	}
}
