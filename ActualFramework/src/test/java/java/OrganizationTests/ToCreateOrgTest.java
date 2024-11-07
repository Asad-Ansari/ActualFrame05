package OrganizationTests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import vtiger.GenricUtility.BaseClass;
import vtiger.GenricUtility.ExcelFileUtility;
import vtiger.GenricUtility.JavaUtility;
import vtiger_ObjectRepository.ContactInfoPage;
import vtiger_ObjectRepository.ContactsPage;
import vtiger_ObjectRepository.CreateContactPage;
import vtiger_ObjectRepository.CreateOrganizationPage;
import vtiger_ObjectRepository.HomePage;
import vtiger_ObjectRepository.OrganizationInfoPage;
import vtiger_ObjectRepository.OrganizationPage;
@Listeners(vtiger.GenricUtility.ListernersImplemetation.class)
public class ToCreateOrgTest extends BaseClass {

	@Test(groups="smoke")
	public void toCreateOrgTest__001() throws EncryptedDocumentException, IOException {
		HomePage hp = new HomePage(driver);
		hp.getOrganizationslink().click();
		OrganizationPage op= new OrganizationPage(driver);
		op.getOrgLink().click();
		//To Read data from Excel
		ExcelFileUtility eutil = new ExcelFileUtility();
		String ORGANITIONNAME = eutil.toReadDataFromExcelFile("Organization", 1, 2)+new JavaUtility().toGetRandomNumber();
		
		CreateOrganizationPage cop= new CreateOrganizationPage(driver);
		cop.getOrganizationnameTextfield().sendKeys(ORGANITIONNAME);
		cop.getSaveButton().click();
		ContactInfoPage cip = new ContactInfoPage(driver);
		OrganizationInfoPage oip= new OrganizationInfoPage(driver);
		String orgname = oip.getValidationLink().getText();
		Assert.assertTrue(orgname.contains(ORGANITIONNAME));
	}
}
