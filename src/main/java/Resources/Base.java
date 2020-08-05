package Resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Base {

	public WebDriver driver;
	public Properties prop;
	public Actions act;

	public WebDriver initializeDriver() throws IOException {
		prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\Resources\\data.properties");
		prop.load(fis);
		String browserName = System.getProperty("browser");

		if (browserName.contains("chrome")) {
			ChromeOptions co = new ChromeOptions();
			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\main\\java\\Resources\\chromedriver.exe");
			if (browserName.contains("headless"))
				co.addArguments("--headless");
			driver = new ChromeDriver(co);
		}
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		return driver;
	}

	public void loadHomePage() {
		driver.get(prop.getProperty("url"));
	}

	public String getScreenShotPath(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir") + "\\reports\\" + testCaseName + ".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;
	}

	public void clickOnLogo() throws InterruptedException {
		Thread.sleep(2000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.querySelector('a.menu__hm',':before').click();");
	}

	public void clickOnMagnifierIcon() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.querySelector('.menu__search_submit',':before').click();");
	}

	public String titleOfAChildPage() {
		Set<String> ids = driver.getWindowHandles();
		Iterator<String> it = ids.iterator();
		it.next();
		String childId = it.next();
		driver.switchTo().window(childId);
		String childWindowTitle = driver.getTitle();
		return childWindowTitle;
	}

	public void openWindowInNewTab(WebElement webElement) {
		act = new Actions(driver);
		act.moveToElement(webElement).keyDown(Keys.CONTROL).click().build().perform();
	}

	public void iterateAllTabsAndGrabTitle() {
		Set<String> abc = driver.getWindowHandles();
		Iterator<String> it = abc.iterator();
		String parentId = it.next();
		while (it.hasNext()) {
			driver.switchTo().window(it.next());
			driver.close();
		}
		driver.switchTo().window(parentId);
	}

	public int openAllLinksInSeparateTabs(WebElement j) {
		int i;
		for (i = 0; i < j.findElements(By.tagName("a")).size(); i++) {
			String clickonlinkTab = Keys.chord(Keys.CONTROL, Keys.ENTER);
			j.findElements(By.tagName("a")).get(i).sendKeys(clickonlinkTab);
		}
		return i;
	}

}
