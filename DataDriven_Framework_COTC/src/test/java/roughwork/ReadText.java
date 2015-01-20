package roughwork;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ReadText {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver =null;
		//System.setProperty("webdriver.chrome.driver", "C:\\Users\\Vidhya\\Softwares\\Selenium\\chromedriver_win32\\chromedriver.exe");
		//System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\chromedriver.exe");
		//driver = new ChromeDriver();
		driver=new FirefoxDriver();
		driver.get("https://www.campaignonthecloud.com/signin");
		
		driver.manage().window().maximize();
			
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.quit();
		
	/*	//driver.findElement(By.xpath("//*[@id='loginbtn']")).click();
		driver.findElement(By.id("loginbox_txtdomainname")).sendKeys("cotcqalive");
		driver.findElement(By.id("loginbox_txtemail")).sendKeys("pkavitha@i-waves.com");
		driver.findElement(By.id("loginbox_txtpassword")).sendKeys("kavi");
		driver.findElement(By.xpath("//*[@id='loginboxdetails']/div[4]/div/table/tbody/tr/td/table/tbody/tr/td[1]/table/tbody/tr[8]/td/table/tbody/tr/td[1]/span")).click();
		int size= driver.findElements(By.id("company_error_label")).size();
		System.out.println(size);
		if (size>0){
			System.out.println("Error message found");
			
		} else
		{
			System.out.println("No error");
		}
		*/
		
		
		/*driver.findElement(By.xpath("//*[@id='txtUsername']")).sendKeys("pkavitha@i-waves.com");
		driver.findElement(By.xpath("//*[@id='txtpasword']")).sendKeys("kavi");
		driver.findElement(By.xpath("//*[@id='lnkbtnlogin']")).click();
		
		driver.findElement(By.xpath("//*[@id='loginbtn']")).click();
		
		Thread.sleep(5000L);*/
	}

}
