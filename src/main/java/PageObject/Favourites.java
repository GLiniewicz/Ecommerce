package PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Favourites {

	WebDriver driver;

	public Favourites(WebDriver driver) {

		this.driver = driver;

	}

	private By countOfItems = By.cssSelector("[class='favorite-items-quantity-number ng-binding']");

	public WebElement countOfItems() throws InterruptedException {
		Thread.sleep(1000);
		return driver.switchTo().activeElement().findElement(countOfItems);
	}
}
