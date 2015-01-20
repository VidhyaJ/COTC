package roughwork;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.http.client.fluent.Request;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;


/*
 $windowHandle = WinGetHandle("File Upload")
        WinActivate($windowHandle)

		ControlSetText("File Upload", "", "[CLASS:Edit; INSTANCE:1]", $CmdLine[1])
	    ControlClick("File Upload", "","[CLASS:Button; INSTANCE:1]")
	
 */

public class LandingPagecheck {
	
static WebDriver driver =null;
WebElement topBanner;
	
	@Test
	public void DefaultText() throws InterruptedException, IOException{
		
		
		//System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
		//driver = new ChromeDriver();
		driver=new FirefoxDriver();
		driver.get("http://www.campaignonthecloud.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id='A2']")).click();
		
		driver.findElement(By.xpath("//*[@id='ct_txtname']")).sendKeys("Vidhya");
		driver.findElement(By.xpath("//*[@id='ct_txtemail']")).sendKeys("vidhya@i-waves.com");
		driver.findElement(By.xpath("//*[@id='ct_txtcompany']")).sendKeys("Infowave");
		driver.findElement(By.xpath("//*[@id='ct_txtsubject']")).sendKeys("AutomationFeedBack");
		driver.findElement(By.xpath("//*[@id='message']")).sendKeys(Keys.CONTROL+"a");
		driver.findElement(By.xpath("//*[@id='message']")).sendKeys(Keys.DELETE);
		driver.findElement(By.xpath("//*[@id='message']")).sendKeys("Positng this message using the tool and autoit");
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//*[@id='FileUpload1']")).click();
				
		 Process process = new ProcessBuilder(System.getProperty("user.dir")+"\\src\\test\\resources\\temp.exe",
	                "C:\\Users\\Vidhya\\Desktop\\Roughnote.txt", "Open").start();
		  Thread.sleep(4000);
		  System.out.println("Completed");
		  driver.switchTo().defaultContent();
		  int total=driver.findElements(By.xpath("//*[@id='idaddress']")).size();
			System.out.println("Total: " +total);
			System.out.println(driver.findElement(By.xpath("//*[@id='idaddress']")).isDisplayed());
		
		 // JavascriptExecutor executor = (JavascriptExecutor)driver;
		  ((JavascriptExecutor)driver).executeScript("contactusSubmit();");
		  System.out.println("Completed again");
		     driver.quit();
		/*List<WebElement> iframes=driver.findElements(By.tagName("iframe"));
		System.out.println(iframes.size());
		
		for (int i=0;i<iframes.size();i++)
		{
			driver.switchTo().frame(i);
			int total=driver.findElements(By.xpath("//*[@id='FileUpload1']")).size();
			System.out.println("Total: " +total+"i value is : " +i);
			//driver.findElement(By.xpath("//*[@id='FileUpload1']")).click();
			driver.switchTo().defaultContent();
		}*/
		//driver.findElement(By.xpath("//*[@id='FileUpload1']")).click();
		
		//driver.findElement(By.xpath("//*[@id='btnsubmit']")).click();
			
	}
	
}
		/*Get all links of top banner and click
		topBanner=driver.findElement(By.className("topmenu"));
		java.util.List<WebElement> allTopLinks = topBanner.findElements(By.tagName("a"));
		int z = allTopLinks.size();
		for (int j=0;j<z;j++){
			allTopLinks = driver.findElements(By.tagName("a"));
			boolean flag = allTopLinks.get(j).isDisplayed();
			String name=allTopLinks.get(j).getText();
			System.out.println(allTopLinks.get(j).getText() + " is displayed in the Landing Page");
			
			if(flag==true){
				allTopLinks.get(j).click();
				checkResponse(driver.getCurrentUrl());
				Set<String> winIds= driver.getWindowHandles();
				Iterator it= winIds.iterator();
				System.out.println(winIds.size());
				if (winIds.size()>1)
				{
					closewindows(driver);
				}
			} else{
			System.out.println(allTopLinks.get(j).getText() + " is not displayed in the Landing Page");
			
			}
		}	
		
		    //Thread.sleep(3000);
		    driver.findElement(By.xpath("//*[@id='lnkbtnHomeClick']")).click();
		    Thread.sleep(3000);
		    driver.quit();
			
		
	}

//true- present
	// false - not present
	public static boolean isElementPresent(String element_xpath){
		int count = driver.findElements(By.xpath(element_xpath)).size();
		
		if(count == 0)
			return false;
		else
			return true;
	}
	
	// Http resonp code - 200 OK
	public static boolean checkResponse(String url){
		try {
         int resp_code= Request.Get(url).execute().returnResponse().getStatusLine()
                 .getStatusCode();
         System.out.println("Respose code for URL "+ url +" is -> "+ resp_code);
        if(resp_code == 200)
     	   return true;
        else
     	   return false;
     } catch (Exception e) {
  	   return false;
     }
	
	}

	
public static void closewindows(WebDriver driver){
	
	System.out.println("Inside closewindows");
	Set<String> winIds= driver.getWindowHandles();
	Iterator it= winIds.iterator();
	System.out.println(winIds.size());
	String mainwindow=(String) it.next();
	
	while(it.hasNext()){
		String newwin=(String) it.next();
		driver.switchTo().window(newwin);
		driver.close();
		driver.switchTo().window(mainwindow);
	}
		
}*/

		



