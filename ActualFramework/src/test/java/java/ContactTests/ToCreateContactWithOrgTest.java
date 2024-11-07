package ContactTests;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import vtiger.GenricUtility.BaseClass;
import vtiger.GenricUtility.ExcelFileUtility;
import vtiger.GenricUtility.WebDriverUtility;
import vtiger_ObjectRepository.ContactInfoPage;
import vtiger_ObjectRepository.ContactsPage;
import vtiger_ObjectRepository.CreateContactPage;
import vtiger_ObjectRepository.HomePage;
@Listeners(vtiger.GenricUtility.ListernersImplemetation.class)
public class ToCreateContactWithOrgTest extends BaseClass {

	@Test(groups = "regression")
	public void toCreateContactTest_002() throws EncryptedDocumentException, IOException {

		HomePage hp = new HomePage(driver);
		hp.getContactsLink().click();
		ContactsPage cp = new ContactsPage(driver);
		cp.getContactsLink().click();

		ExcelFileUtility eutil = new ExcelFileUtility();
		String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 1, 2);
		CreateContactPage ccp = new CreateContactPage(driver);
		ccp.getLastnameTextfield().sendKeys(LASTNAME);
		ccp.getOrgIconButton().click();
		WebDriverUtility wutil= new WebDriverUtility();
		wutil.toSwitchWindow(driver, "Accounts");
		driver.findElement(By.xpath("//a[text()='vtiger']")).click();
		wutil.toSwitchWindow(driver, "Contacts");
		ccp.getSaveLink().click();
		ContactInfoPage cip = new ContactInfoPage(driver);
		String lastname = cip.getValidationlink().getText();
		Assert.assertTrue(lastname.contains(LASTNAME));
	}
}
