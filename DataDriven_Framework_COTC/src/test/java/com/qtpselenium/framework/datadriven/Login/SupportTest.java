package com.qtpselenium.framework.datadriven.Login;

import java.io.IOException;
import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qtpselenium.framework.datadriven.TestBase;
import com.qtpselenium.framework.datadriven.util.Constants;
import com.qtpselenium.framework.datadriven.util.ErrorUtil;
import com.qtpselenium.framework.datadriven.util.TestDataProvider;

public class SupportTest extends TestBase{
	public static String feedback_actualmsg,feedback_expectedmsg;
	@BeforeTest
	public void initLogs(){
		initLogs(this.getClass());
		APPLICATION_LOG.debug(" Landing Page ->Support Regression Test Start Date/Time ");
		getcurrentdate();
		
	}
	@Test(dataProviderClass=TestDataProvider.class,dataProvider="LoginSuiteDataProvider")
	public void supporttest(Hashtable<String,String> table) throws IOException, InterruptedException{
		
		validateRunmodes("SupportTest", Constants.LOGIN_SUITE, table.get("Runmode"));
		openBrowser(table.get(Constants.BROWSER_COL));
		navigate("defaultestSiteURL");	
		try{
		Assert.assertTrue(isElementPresent("Support_xpath"), "Element not found Support_xpath");
		} catch (Throwable t){
			ErrorUtil.addVerificationFailure(t);
		}
		
		
		click("Support_xpath");
		APPLICATION_LOG.debug("Support link clicked in the Home Page");
		
		input("Supportname_xpath",table.get("name"));
		input("Supportemail_xpath",table.get("email"));
		input("Supportcompany_xpath",table.get("company"));
		input("Supportsubject_xpath",table.get("subject"));
		driver.findElement(By.xpath("//*[@id='message']")).sendKeys(Keys.CONTROL+"a");
		driver.findElement(By.xpath("//*[@id='message']")).sendKeys(Keys.DELETE);
		input("Supportmessage_xpath",table.get("message"));
		Thread.sleep(2000);
		if (table.get("attachment").equals("Y")){
			
		//Upload button is present inside a frame.So switching to the frame in which it is present.
		driver.switchTo().frame(0);
		Thread.sleep(2000);
		click("SupportFileUpload_xpath");
		
		//Auto it code for selecting file in the upload popup
		/*
		$windowHandle = WinGetHandle("File Upload")
        WinActivate($windowHandle)

		ControlSetText("File Upload", "", "[CLASS:Edit; INSTANCE:1]", $CmdLine[1])
	    ControlClick("File Upload", "","[CLASS:Button; INSTANCE:1]")*/
	    
	     Process process = new ProcessBuilder(System.getProperty("user.dir")+"\\src\\test\\resources\\temp.exe",
	           System.getProperty("user.dir")+"\\src\\test\\resources\\Roughnote.txt", "Open").start();
	     Thread.sleep(3000);
		 driver.findElement(By.xpath("//*[@id='btnUpload']")).click();
		 driver.switchTo().defaultContent();
		 Thread.sleep(2000);
		 APPLICATION_LOG.debug("File Roughnote.txt uploaded in the Support section");
	 }
		 
		  
		    Actions act= new Actions(driver);
		 	WebElement check=driver.findElement(By.xpath("//*[@id='contactusContent']/div[1]/div/div[5]/label"));
			act.moveToElement(check).build().perform();
			act.click(check);
		    
		    driver.findElement(By.xpath("html/body/form/div[3]/div[2]/div/div/div[1]/div/div[3]/div[2]/div[1]/div/div[6]/span")).click();
		 	Thread.sleep(5000);
		 	//Assertion
			feedback_expectedmsg="Thank you very much for your feedback/query.";
			feedback_actualmsg=driver.findElement(By.xpath("//*[@id='thankyoupagecontactus']/div/div[1]/label")).getText();
			 try{
				 Assert.assertEquals(feedback_actualmsg,feedback_expectedmsg);
				 APPLICATION_LOG.debug("Confirmation message :"+feedback_actualmsg+" is displayed after feedback is posted" );
		         APPLICATION_LOG.debug("Feedback posted by  :"+table.get("email"));
		         APPLICATION_LOG.debug("Attachment  :"+table.get("attachment"));
		         }catch(Throwable e){
	             APPLICATION_LOG.debug("Exception in Support : " +e);
		         }
		  
		  //Using Javascript executor to click on the Submit button in the feedback page
		  //((JavascriptExecutor)driver).executeScript("contactusSubmit();");
		  
	}

	@AfterMethod
	public void close(){
		APPLICATION_LOG.debug(" Landing Page Support Regression Test End Date/Time ");
		getcurrentdate(); 
		quit();
	}

}


