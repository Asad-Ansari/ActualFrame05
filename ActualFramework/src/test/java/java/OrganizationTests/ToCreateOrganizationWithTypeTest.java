package OrganizationTests;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import vtiger.GenricUtility.BaseClass;
import vtiger.GenricUtility.ExcelFileUtility;
import vtiger.GenricUtility.JavaUtility;
import vtiger_ObjectRepository.CreateOrganizationPage;
import vtiger_ObjectRepository.HomePage;
import vtiger_ObjectRepository.OrganizationInfoPage;
import vtiger_ObjectRepository.OrganizationPage;
@Listeners(vtiger.GenricUtility.ListernersImplemetation.class)
public class ToCreateOrganizationWithTypeTest extends BaseClass {

	@Test(groups="regression")
	public void toCreateOrganizationWithType() throws EncryptedDocumentException, IOException {
		HomePage hp = new HomePage(driver);
		hp.getOrganizationslink().click();
		OrganizationPage op = new OrganizationPage(driver);
		op.getOrgLink().click();
		// to read data from java utility
		
		ExcelFileUtility eutil = new ExcelFileUtility();
		JavaUtility jutil = new JavaUtility();
		String ORGNAME = eutil.toReadDataFromExcelFile("Organization", 1, 2)+jutil.toGetRandomNumber();
		System.out.println(ORGNAME);
		CreateOrganizationPage cop= new CreateOrganizationPage(driver);
		cop.getOrganizationnameTextfield().sendKeys(ORGNAME);
		
		WebElement indDropDown = cop.getIndustryDropDown();
		 Select IndustryDropDown= new Select(indDropDown);
		 IndustryDropDown.selectByVisibleText("Chemicals");
		 
		WebElement typeDropDown = cop.getTypeDropDown(); 
		 
		 Select selectDropDown= new Select(typeDropDown);
		 selectDropDown.selectByVisibleText("Customer");
		
		 cop.getSaveButton().click();
		 OrganizationInfoPage oip = new OrganizationInfoPage(driver);
		  String orgname = oip.getValidationLink().getText();
		  Assert.assertTrue(orgname.contains(ORGNAME));
}
}
