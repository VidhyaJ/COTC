package roughwork;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class UploadFilesUsingFF {
	

	 WebDriver driver = null;
	
	@Test
	public void uploadfiles() throws IOException, InterruptedException{
	
		driver = new FirefoxDriver();
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\Vidhya\\workspace\\chromedriver_win32\\chromedriver.exe");
		//driver = new ChromeDriver();
		driver.get("http://qtpselenium.com/test/uploadform/contactform.php");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(15,TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//*[@id='photo']")).click();
		
		  Process process = new ProcessBuilder(System.getProperty("user.dir")+"\\src\\test\\resources\\temp.exe",
	                "C:\\Users\\Vidhya\\Desktop\\Roughnote.txt", "Open").start();
		  Thread.sleep(4000);
		  System.out.println("Completed");
		  
	}
}


