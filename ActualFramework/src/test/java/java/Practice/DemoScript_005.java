package Practice;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DemoScript_005 {

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

		// Step 3:- Navigate to contacts link
		driver.findElement(By.linkText("Contacts")).click();
		// Step 4:- Click on Create contacts Look up image
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		// Step 5:- Create contacts with mandatory fields

		driver.findElement(By.name("lastname")).sendKeys("ansari");
		// Step 6:- Select the Organization from the organization look up image 
		
		String parentid = driver.getWindowHandle();
   driver.findElement(By.xpath("(// img[@src='themes/softed/images/select.gif'])[1]")).click();
   Set<String> childId = driver.getWindowHandles();
childId.remove(parentid);
   for(String id:childId) {
	driver.switchTo().window(id);
	break;
}
driver.findElement(By.linkText("Indian_It_Company10")).click();
driver.switchTo().window(parentid);

//Step 7:- Save And Verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String organization = driver.findElement(By.xpath("// span[@class='dvHeaderText']")).getText();
		if(organization.contains(organization)) {
			System.out.println(organization  +  "   Passed  ");
		}else {
			System.out.println(organization  +  "   Failed  ");
		}


		// Step 8:-Logout Applicatuion
		WebElement logout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));

		Actions action= new Actions(driver);
		action.moveToElement(logout).perform();
		driver.findElement(By.linkText("Sign Out")).click();

		// Step 9:-Close browser
		driver.quit();

	}

}
