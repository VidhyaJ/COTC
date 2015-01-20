package roughwork;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.qtpselenium.framework.datadriven.TestBase;

public class ImportContact extends TestBase {

	@Test
	public void DefaultText() throws InterruptedException, IOException{
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Vidhya\\workspace\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		//driver=new FirefoxDriver();
		driver.get("http://vidhyaautomate.campaignonthecloud.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id='txtUsername']")).sendKeys("vidhya@i-waves.com");
		driver.findElement(By.xpath("//*[@id='txtpasword']")).sendKeys("testing1");
		driver.findElement(By.xpath("//*[@id='lnkbtnlogin']")).click();
		Thread.sleep(40000L);
		//waitForPageToLoad();
		//driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
		//
		////*[@id='menucontacts_normal']
		//WebDriverWait wait = new WebDriverWait(driver,100);
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='menucontacts_normal']")));
				
				//visibilityOfElementLocated(By.xpath("//*[@id='lnkbtnlogin']")));
		//FromSeconds(30.00)

		// wait.Until(driver => ((JavaScriptExecutor)driver).ExecuteScript("return document.readyState").Equals("complete"));
		driver.findElement(By.xpath("//*[@id='menucontacts_normal']")).click();
		//driver.findElement(By.xpath("//div[@id='master_mainmenus']/div[3]/span/img[2]")).click();
		Thread.sleep(10000L);
		//waitForPageToLoad();
		driver.findElement(By.xpath("//*[@id='img_import_icon']")).click();
		
					
		Select select = new Select(driver.findElement(By.xpath("//*[@id='drpdwnChooseContactFiletoUpload']")));
        select.selectByVisibleText("XLS");
            
        driver.findElement(By.xpath("//*[@id='Img3']")).click();
        
       
	     
        Process process = new ProcessBuilder(System.getProperty("user.dir")+"\\src\\test\\resources\\ChromeUpload.exe",
                "C:\\Users\\Vidhya\\Desktop\\Contacts.xls", "Open").start();

	     Thread.sleep(10000L); 
	     //Choose this for selecting cancel
	     //driver.findElement(By.xpath("//*[@id='fileupload_div']/span[3]")).click();
	     
	     //Choose this for selecing Upload
		 //driver.findElement(By.xpath("//*[@id='fileupload_div']/span[2]")).click();

	        String xpath_root="//*[@id='fileupload_div']/span[2]";
			Actions builder = new Actions(driver);  
			WebElement root = driver.findElement(By.xpath(xpath_root));
			builder.moveToElement(root).build().perform();
			root.click();
			
			Thread.sleep(3000L);
			
			//To Check close in the Import pop up
			//driver.findElement(By.xpath("//*[@id='Img_closeimport']")).click();
			//driver.findElement(By.xpath("//*[@id='popup_ok']")).click();
			
			
			driver.findElement(By.xpath("//*[@id='Img_addcontactstogrid']")).click();
			
			//List<WebElement> txt =driver.findElements(By.id("importform_error_msg"));
			//String x = driver.findElement(By.xpath("//div[id='import_header']/div")).getText();
			//String x=driver.findElement(By.className("display_error_style1")).getText();
					//System.out.println("Test is : " +x);
					//System.out.println("Test is : " +txt.get(0).getTagName());
					//System.out.println("Test is : " +txt.get(0).toString());

			//java.util.List<WebElement> allnames = driver.findElements(By.xpath("//div[starts-with(@id,'import')]"));
			java.util.List<WebElement> allnames = driver.findElements(By.xpath("//div[contains(@id,'import')]"));
				
			
			System.out.println("Size is : "+allnames.size());
					for (int j=0;j<allnames.size();j++){
						System.out.println("Text is :" +allnames.get(j).getText());
					}	
					
					driver.close();
					
	}
	
	public void waitForPageToLoad()
	  {
		System.out.println("Hi");
	(new WebDriverWait(driver, 100)).until(new ExpectedCondition<Boolean>() {
	      public Boolean apply(WebDriver d) {
	        return (((org.openqa.selenium.JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
	      }
	    });//Here DEFAULT_WAIT_TIME is a integer correspond to wait time in seconds
	
}
}
