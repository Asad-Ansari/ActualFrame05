package ContactTests;

import static org.testng.Assert.fail;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import vtiger.GenricUtility.BaseClass;
import vtiger.GenricUtility.ExcelFileUtility;
import vtiger_ObjectRepository.ContactInfoPage;
import vtiger_ObjectRepository.ContactsPage;
import vtiger_ObjectRepository.CreateContactPage;
import vtiger_ObjectRepository.HomePage;
@Listeners(vtiger.GenricUtility.ListernersImplemetation.class)
public class ToCreateContactTest extends BaseClass {
		@Test(groups = "smoke")
		
		public void toCreateContactTest_001() throws EncryptedDocumentException, IOException {
			HomePage hp = new HomePage(driver);
			hp.getContactsLink().click();
			ContactsPage cp = new ContactsPage(driver);
			cp.getContactsLink().click();
			// To read from excel
			ExcelFileUtility eutil = new ExcelFileUtility();
			String LASTNAME = eutil.toReadDataFromExcelFile("Contacts", 1, 2);
			CreateContactPage ccp = new CreateContactPage(driver);
			ccp.getLastnameTextfield().sendKeys(LASTNAME);
			ccp.getSaveLink().click();
			// Fail
			//Assert.fail();
			ContactInfoPage  cip = new ContactInfoPage(driver);
			String lastname = cip.getValidationlink().getText();
			
			//if( lastname.contains(LASTNAME)) {
				//System.out.println(lastname + ".......passed");
			//} else {
				//System.out.println(lastname +"......failed");
			//}
			Assert.assertTrue( lastname.contains(LASTNAME));

		}
	}
	

