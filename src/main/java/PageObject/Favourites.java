package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import Resources.Base;

public class Favourites {

	WebDriver driver;
	Base bs;

	public Favourites(WebDriver driver) {

		this.driver = driver;

	}

	private By countOfItems = By.cssSelector("[class='favorite-items-quantity-number ng-binding']");

	public WebElement countOfItems() {
		bs = new Base();
		bs.waitForJStoLoad(driver);
		return driver.switchTo().activeElement().findElement(countOfItems);
	}
}
