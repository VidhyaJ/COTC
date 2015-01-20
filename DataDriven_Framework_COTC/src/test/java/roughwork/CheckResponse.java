package roughwork;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class CheckResponse {
	WebDriver driver =null;
	
	@Test
	public void Login(){
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Vidhya\\workspace\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("http://www.campaignonthecloud.com");
		
		
		WebElement topBanner=driver.findElement(By.className("topmenu"));
		java.util.List<WebElement> allTopLinks = topBanner.findElements(By.tagName("a"));
		
		int z = allTopLinks.size();
		for (int j=0;j<z;j++){
			allTopLinks = driver.findElements(By.tagName("a"));
	 	  boolean flag = allTopLinks.get(j).isDisplayed();
			//System.out.println(allTopLinks.get(j).getText() + " is displayed in the Landing Page");
			
			if(flag==true){
				allTopLinks.get(j).click();
				//System.out.println(allTopLinks.get(j).getText());
			}
	}
driver.quit();
	}
}
		
		