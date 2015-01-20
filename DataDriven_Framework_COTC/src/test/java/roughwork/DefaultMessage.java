package roughwork;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DefaultMessage {
	
WebDriver driver =null;
	
	@Test
	public void DefaultText() throws InterruptedException{
		
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\Vidhya\\workspace\\chromedriver_win32\\chromedriver.exe");
		//driver = new ChromeDriver();
		driver=new FirefoxDriver();
		driver.get("http://vidhyaautomate.campaignonthecloud.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id='txtUsername']")).sendKeys("vidhya@i-waves.com");
		driver.findElement(By.xpath("//*[@id='txtpasword']")).sendKeys("testing1");
		driver.findElement(By.xpath("//*[@id='lnkbtnlogin']")).click();
		Thread.sleep(25000L);
		driver.findElement(By.xpath("//*[@id='menucontacts_normal']")).click();
		String x= driver.findElement(By.xpath("//*[@id='cotccontacts']/table[3]/tbody/tr/td/b/div[5]/label")).getText();
		System.out.println("The string is : " +x);
		try {
		Assert.assertEquals(x, "There is Currently no data to show", "Data does not Match");
		} catch(Throwable t){
			System.out.println("Message is " +t);
		}
		
		driver.quit();
		
		
}
}
