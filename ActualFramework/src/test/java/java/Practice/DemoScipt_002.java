package Practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DemoScipt_002 {

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

		driver.findElement(By.name("accountname")).sendKeys("Indian_It_Company52");

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
