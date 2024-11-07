package Practice;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DemoScriptDDT_002 {

	public static void main(String[] args) throws IOException {
		// To read data from property file

				FileInputStream pfis = new FileInputStream(".\\src\\test\\resources\\CommonData.properties");
				Properties prop= new Properties();
				prop.load(pfis);
				String URL = prop.getProperty("url");
				String USERNAME = prop.getProperty("username");
				String PASSWORD = prop.getProperty("password");
				String BROWSER = prop.getProperty("browser");
				// To read data from EXCEL file
				FileInputStream efis = new FileInputStream(".\\src\\test\\resources\\TestDataM6.xlsx");
				Workbook wb = WorkbookFactory.create(efis);
				String ORGANIZATIONNAME = wb.getSheet("Organization").getRow(1).getCell(2).toString();

				// Step:- 1 Launch Browser

				WebDriver driver= new ChromeDriver();
				driver.manage().window().maximize();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
				// Step 2:-  Login to the Application with Valid credentials

				driver.get(URL);
				driver.findElement(By.name("user_name")).sendKeys(USERNAME);
				driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
				driver.findElement(By.id("submitButton")).click();

				// Step 3:- Navigate to organization link
				driver.findElement(By.linkText("Organizations")).click();

				// Step 4:- Click on Create organization Look up image
				driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

				// Step 5:- Create organization with mandatory fields

				driver.findElement(By.name("accountname")).sendKeys(ORGANIZATIONNAME);

				// Step 6:- Save and Verify
				driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
				String organizationName = driver.findElement(By.xpath("// span[@class='dvHeaderText']")).getText();
				if(organizationName.contains(organizationName)) {
					System.out.println(organizationName  +  "   Passed  ");
				}else {
					System.out.println(organizationName  +  "   Failed  ");
				}
				
				
				// Step 7:-Logout Applicatuion
				WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
				
				Actions action= new Actions(driver);
				action.moveToElement(logout).perform();
				driver.findElement(By.linkText("Sign Out")).click();
				
				// Step 8:-Close browser
						driver.quit();
				
				
				
				
	}

}
