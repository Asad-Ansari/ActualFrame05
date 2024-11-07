package Practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class DemoScript_003 {

	public static void main(String[] args) throws InterruptedException {
		// Step:- 1 Launch Browser

		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		// Step 2:-  Login to the Application with Valid credentials

		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("root");
		Thread.sleep(10);
		driver.findElement(By.id("submitButton")).click();

		// Step 3:- Navigate to organization link
		driver.findElement(By.linkText("Organizations")).click();

		// Step 4:- Click on Create organization Look up image
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		// Step 5:- Create organization with mandatory fields

		driver.findElement(By.name("accountname")).sendKeys("Indian_It_Company51");
		// Step 6:-Select Chemical In the Industry downDown

		//identify dropdown
		WebElement listbox = driver.findElement(By.name("industry"));
		Select ref= new Select(listbox);
		ref.selectByIndex(4);
		// Step7:- Save And Verify
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String organizationName1 = driver.findElement(By.xpath("// span[@class='dvHeaderText']")).getText();
		if(organizationName1.contains(organizationName1)) {
			System.out.println(organizationName1  +  "   Passed  ");
		}else {
			System.out.println(organizationName1  +  "   Failed  ");
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
