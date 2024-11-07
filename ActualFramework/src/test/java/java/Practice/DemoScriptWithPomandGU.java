package Practice;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import vtiger.GenricUtility.ExcelFileUtility;
import vtiger.GenricUtility.PropertyFileUtility;
import vtiger.GenricUtility.WebDriverUtility;
import vtiger_ObjectRepository.ContactInfoPage;
import vtiger_ObjectRepository.ContactsPage;
import vtiger_ObjectRepository.CreateContactPage;
import vtiger_ObjectRepository.HomePage;
import vtiger_ObjectRepository.LoginPage;

public class DemoScriptWithPomandGU {
	public static void main(String[] args) throws EncryptedDocumentException, IOException {

		ExcelFileUtility eutil = new ExcelFileUtility();
		PropertyFileUtility putil = new PropertyFileUtility();
		WebDriverUtility wutil = new WebDriverUtility();
		// To read from propety file
		String URL = putil.toReadDataFromPropertyFile("url");
		String BROWSER = putil.toReadDataFromPropertyFile("browser");
		String USERNAME = putil.toReadDataFromPropertyFile("username");
		String PASSWORD = putil.toReadDataFromPropertyFile("password");
		// READ data from excel file
		String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 1, 2);
		// SCRIPT
		WebDriver driver = null;
		if (BROWSER.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equals("firefox")) {
			driver = new FirefoxDriver();
		}
		wutil.ToMaximize(driver);
		wutil.waitforElements(driver);
		// step 2 login with valid credentials
		driver.get(URL);
		LoginPage lp = new LoginPage(driver);
		lp.getUsernametextfield().sendKeys(USERNAME);
		lp.getPasswordtextfield().sendKeys(PASSWORD);
		lp.getLoginbutton().click();
// step 3 click on contacts link
		HomePage hp = new HomePage(driver);
		hp.getContactsLink().click();
// step 4 click on create contact lookup image
		ContactsPage cp = new ContactsPage(driver);
		cp.getContactsLink().click();
//step 4 fill with mandorty field
		CreateContactPage ccp = new CreateContactPage(driver);
		ccp.getLastnameTextfield().sendKeys(LASTNAME);
		ccp.getSaveLink().click();
//step 5 verify
		ContactInfoPage cip = new ContactInfoPage(driver);
		String lastname = cip.getValidationlink().getText();
		if (lastname.contains(LASTNAME)) {
			System.out.println(lastname + "   passed   ");
		} else {
			System.out.println(lastname + "   failed  ");
		}
// logout
		wutil.toMouseHover(driver, hp.getLogoutlink());
		hp.getLogoutlink().click();
		driver.quit();
	}
}
