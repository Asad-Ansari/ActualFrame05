package Practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class DemoScript_004 {

	public static void main(String[] args) {

		// Step:- 1 Launch Browser

		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		// Step 2:-  Login to the Application with Valid credentials

		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("root");
		driver.findElement(By.id("submitButton")).click();

		// Step 3:- Navigate to organization link
		driver.findElement(By.linkText("Organizations")).click();

		// Step 4:- Click on Create organization Look up image
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		// Step 5:- Create organization with mandatory fields

		driver.findElement(By.name("accountname")).sendKeys("Indian_It_Company10");
		// Step 6:-Select Energy In the Industry downDown
		//identify Industrydropdown
		WebElement industryDropdown = driver.findElement(By.name("industry"));
		Select ref= new Select(industryDropdown);
		ref.selectByIndex(10);

		// Step 7:-Select Customer In the type downDown
		//identify typedropdown
		WebElement typeDropdown = driver.findElement(By.name("accounttype"));
		Select ref1= new Select(typeDropdown);
		ref1.selectByIndex(3);

		// Step 8:- Save And Verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String organizationName_Energy_Customer = driver.findElement(By.xpath("// span[@class='dvHeaderText']")).getText();
		if(organizationName_Energy_Customer.contains(organizationName_Energy_Customer)) {
			System.out.println(organizationName_Energy_Customer  +  "   Passed  ");
		}else {
			System.out.println(organizationName_Energy_Customer  +  "   Failed  ");
		}


		// Step 9:-Logout Applicatuion
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));

		Actions action= new Actions(driver);
		action.moveToElement(logout).perform();
		driver.findElement(By.linkText("Sign Out")).click();

		// Step 10:-Close browser
		driver.quit();

	}

}
