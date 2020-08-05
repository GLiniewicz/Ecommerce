package PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	WebDriver driver;
	SearchResultsPage srp;

	public HomePage(WebDriver driver) {

		this.driver = driver;

	}

	private By readMagazineButton = By.xpath("//*[@title='Magazine - READ H&M MAGAZINE']");
	private By magazineHeader = By.cssSelector(".life-logotype-headline");
	private By koszykButton = By.cssSelector("[data-cart='/pl_pl/minicart/view']");
	private By searchBox = By.cssSelector("#main-search");
	private By footerLinks = By.cssSelector("nav.footer-category");
	private By favouritesTab = By.cssSelector("a.menu__favorites");
	private By wyprzedazButton = By.cssSelector("a[data-goto-text='Go to Wyprzedaż home']");
	private By lazienkaButton = By.linkText("Łazienka");

	public WebElement readMagazineButton() {
		return driver.findElement(readMagazineButton);
	}

	public WebElement magazineHeader() {
		return driver.findElement(magazineHeader);
	}

	public WebElement koszykButton() {
		return driver.findElement(koszykButton);
	}

	public WebElement searchBox() {
		return driver.findElement(searchBox);
	}

	public List<WebElement> footerLinks() {
		return driver.findElements(footerLinks);
	}

	public WebElement favouritesTab() {
		return driver.findElement(favouritesTab);
	}

	public WebElement wyprzedazButton() {
		return driver.findElement(wyprzedazButton);
	}

	public WebElement lazienkaButton() {
		return driver.switchTo().activeElement().findElement(lazienkaButton);
	}

}
