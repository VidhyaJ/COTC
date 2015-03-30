package roughwork;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Hello {

		public static void main(String[] args) throws InterruptedException {
			// text , properties, xls

			//System.setProperty("webdriver.chrome.driver", "H:\\chromedriver.exe");
			//ChromeDriver driver = new ChromeDriver();
			//FirefoxDriver driver = new FirefoxDriver();
			
			// HW
			String browser = "Mozilla"; // properties
		WebDriver driver = null;
			
			if(browser.equals("Mozilla"))
				driver = new FirefoxDriver();
			else if(browser.equals("Chrome")){
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
			    driver = new ChromeDriver();
			}else if(browser.equals("IE")){
			// exe path
			}
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		    driver.manage().window().maximize();
 		
 		  driver.get("http://www.campaignsonthecloud.com");    
 		 /* String copyrighttext1 = driver.findElement(By.xpath("/html/body/form/div[4]/div[2]/span[1]")).getText();
 		  String copyrighttext2 = driver.findElement(By.xpath("/html/body/form/div[4]/div[2]/span[2]")).getText();
 		  String copyrighttext3 = driver.findElement(By.xpath("/html/body/form/div[4]/div[2]/span[3]")).getText();
 		  try{
 			   Assert.assertEquals(copyrighttext1+copyrighttext2+copyrighttext3," Copyright © 2014 InfoWave Knowledgeware LLC. All rights reserved."); 
 		  }catch(Throwable e){
 			  System.out.println("Exception is displayed :"+e);
 			 // e.printStackTrace(); 
 		  }
 		   //System.out.println(copyrighttext1+copyrighttext2+copyrighttext3);*/
 		  
 		//WebElement catBox = driver.findElement(By.xpath("html/body/form/div[5]/div[1]/div/div[2]/div/table/tbody/tr/td[2]"));
 		 // WebElement socialfooter=driver.findElement(By.xpath("//*[@id='footerMenu']/div[2]/div/table/tbody/tr/td[2]/table/tbody/tr[1]/td/table/tbody/tr/td"));
 		/* WebElement socialfooter=driver.findElement(By.xpath("//*[@id='footerMenu']/div[2]/div/table/tbody/tr/td[2]"));
 		  List<WebElement> socialsites=socialfooter.findElements(By.tagName("a"));
 		  System.out.println("Social sites size is : " +socialsites.size());
 		 for(int i=0;i<socialsites.size();i++)
 	     { 
 	     socialsites.get(i).click();
 	     
 	     System.out.println(driver.getCurrentUrl());
 	     driver.navigate().back();
 	     System.out.println("Facebook, twitter, Linkedin, Mixpanel Links opened");
 	     }*/
 		 
 		 
 		  
 		 //String linkname=driver.findElement(By.xpath("//*[@id='footerMenu']/div[2]/div/table/tbody/tr/td[2]/table/tbody/tr[1]/td/table/tbody/tr/td[1]/a")).getText();
 		 //System.out.println("Linkname : " +linkname);
 		  //for// ( int i=1;i<4;i++){
 			 //driver.findElement(By.xpath("//*[@id='footerMenu']/div[2]/div/table/tbody/tr/td[2]/table/tbody/tr[1]/td/table/tbody/tr/td[i]\a"));
 		 // }
 		  
 	    /*List<WebElement> catValues = catBox.findElements(By.tagName("a"));
 	    for(int i=0;i<catValues.size();i++)
 	    { 
 	    catValues.get(i).click();
 	     
 	    driver.navigate().back();
 	    System.out.println("Facebook, twitter, Linkedin, Mixpanel Links opened");
 	    }*/
 		   //driver.quit();
 		}
 			  
}


