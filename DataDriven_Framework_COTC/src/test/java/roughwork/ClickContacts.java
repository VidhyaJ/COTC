package roughwork;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

public class ClickContacts {
	static WebDriver driver =null;

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
	/*	System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
		System.out.println("Dir is : " +System.getProperty("user.dir")+"\\chromedriver.exe");
		driver = new ChromeDriver();*/
		driver=new FirefoxDriver();
		driver.get("https://vidhyaautomate.campaignonthecloud.com");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		driver.findElement(By.xpath("//*[@id='txtUsername']")).sendKeys("vidhya@i-waves.com");
		driver.findElement(By.xpath("//*[@id='txtpasword']")).sendKeys("testing112");
		driver.findElement(By.xpath("//*[@id='lnkbtnlogin']")).click();
		Thread.sleep(5000);
		String element = driver.findElement(By.xpath("/html/body/form/div[3]/div[2]/div[2]/div/div/div/div/table/tbody/tr[2]/td[2]/div/div")).getText(); 
        System.out.println("Element is : " +element);
        String element1=driver.findElement(By.xpath("//*[@id='Loginpassworderr']")).getText();
        System.out.println("Element 1 is " +element1);
        driver.quit();
       /* driver = new FirefoxDriver();
        
        driver.get("http://vidhyaautomate.campaignonthecloud.com/Login");  //Give ur domain name
        driver.findElement(By.xpath(".//*[@id='txtUsername']")).sendKeys("vidhya@i-waves.com");
        driver.findElement(By.xpath(".//*[@id='txtpasword']")).sendKeys("kavi232");
        driver.findElement(By.xpath(".//*[@id='lnkbtnlogin']")).click();

        Thread.sleep(10000);
        //String element = driver.findElement(By.xpath("html/body/div[3]/div/div[2]/div/form/div[1]")).getText(); 
        String element = driver.findElement(By.xpath("/html/body/form/div[3]/div[2]/div[2]/div/div/div/div/table/tbody/tr[2]/td[2]/div/div")).getText(); 
        //String element2 = driver.findElement(By.xpath("/html/body/form/div[3]/div[2]/div[2]/div/div/div/div/table/tbody/tr[1]/td[2]/div/div")).getText();
                System.out.println(element);
        //System.out.println(element2);
        Assert.assertEquals("The password you entered\n     is incorrect", element);
       
		/*Thread.sleep(5000);
		
		driver.findElement(By.xpath("//*[@id='master_setting']/table/tbody/tr/td[5]/img")).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//*[@id='show_div_settings']/table/tbody/tr/td/table/tbody/tr[6]/td/a")).click();
		Thread.sleep(2000);
		System.out.println("Completed");*/
		driver.quit();
	}

}
