package Practice;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import vtiger.GenricUtility.ExcelFileUtility;
import vtiger.GenricUtility.PropertyFileUtility;
import vtiger.GenricUtility.WebDriverUtility;

public class DemoScriptWithGU {
	public static void main (String[]args) throws IOException {


		ExcelFileUtility eutil = new ExcelFileUtility();
		PropertyFileUtility putil = new PropertyFileUtility();
		WebDriverUtility wutil = new WebDriverUtility();

		//Read data from property file
		String URL = putil.toReadDataFromPropertyFile("url");
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");

		//Read data from excel file 
		String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 1, 2);


		//Script
		//step 1:-Launch Browser
		WebDriver driver = null;
		if(BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		}else if(BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		}else if(BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		wutil.ToMaximize(driver);
		wutil.waitforElements(driver);


		//step 2 :-Login to application with valid credentials
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		//step 3 :- navigate to contacts link
		driver.findElement(By.linkText("Contacts")).click();
		//step 4:- click on create contact look up image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		//step 5:-create contact with mandatory fields
		driver.findElement(By.name("lastname")).sendKeys(LASTNAME);
		//step 6:-save and verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String lastname = driver.findElement(By.xpath("//span[@class='dvHeaderText']" )).getText();
		if(lastname.contains(LASTNAME)) {
			System.out.println(lastname+"-------passed");
		}else {
			System.out.println(lastname+"--------failed");
		}
		//step 7:- Logout of application
		WebElement logoutEle = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wutil.toMouseHover(driver, logoutEle);
		driver.findElement(By.linkText("Sign Out")).click();
		//step 8:-close the browser
		driver.quit();
	}
}
