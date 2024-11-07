package Practice;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Demoscript {
	
	public static void main (String[]args) {
		// Step 1:-  Launch Browser
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		// Step 2:-  Login to the Application with Valid credentials
		driver.get("http://localhost:8888/");
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("root");
		driver.findElement(By.id("submitButton")).click();
		
		// Step 3:- Navigate to contacts link
		
		driver.findElement(By.linkText("Contacts")).click();
		
		// Step 4:- Click on Create contact Look up image
		driver.findElement(By.xpath("//img[@alt='Create Contact...']")).click();
	
		// Step 5:- Create contact with mandatory fields
		
		driver.findElement(By.name("lastname")).sendKeys("ansari");
		
		// Step 6:- Save and Verify
		
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		String lastname = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(lastname.contains("ansari")) {
			System.out.println(lastname  +  " passed");
		}else {
			System.out.println(lastname  +  " failed");
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
