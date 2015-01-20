package com.qtpselenium.framework.datadriven.Login;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qtpselenium.framework.datadriven.TestBase;
import com.qtpselenium.framework.datadriven.util.Constants;
import com.qtpselenium.framework.datadriven.util.ErrorUtil;
import com.qtpselenium.framework.datadriven.util.TestDataProvider;

public class SignUpTest extends TestBase{
	
	public static String SignUpConfirm_expectedmsg,SignUpConfirm_actualmsg,duplicatedomain_actualmsg,duplicatedomain_expectedmsg,invalidemail_expectedmsg;
    
	@BeforeTest
	public void initLogs(){
	initLogs(this.getClass());
	APPLICATION_LOG.debug("Sign Up Regression Test Start Date/Time ");
	getcurrentdate();			
	}
	
	@Test(dataProviderClass=TestDataProvider.class,dataProvider="LoginSuiteDataProvider")
	public void signuptest(Hashtable<String,String> table) throws InterruptedException{
				
		validateRunmodes("SignUpTest", Constants.LOGIN_SUITE, table.get("Runmode"));
		openBrowser(table.get(Constants.BROWSER_COL));
		navigate("defaultestSiteURL");	
		try{
		  Assert.assertTrue(isElementPresent("SignUp_xpath"), "Element not found loginlink_xpath");
		} catch (Throwable t){
			ErrorUtil.addVerificationFailure(t);
		}
		click("SignUplink_xpath");
		Thread.sleep(2000);
		
		input("name_xpath",table.get(Constants.NAME_COL));
		input("signupemail_xpath",table.get(Constants.USERNAME_COL));
		input("domainname_xpath",table.get(Constants.DOMAINNAME_COL));
		click("terms_xpath");
		Thread.sleep(2000);
		click("signUpButton_xpath");
		Thread.sleep(5000);
			
		 duplicatedomain_expectedmsg="Domain Name already exist. Please try with different name";
		 invalidemail_expectedmsg="Please check the email format";
		 duplicatedomain_actualmsg=driver.findElement(By.xpath("//*[@id='company_error_label']")).getText();
		 if (((((duplicatedomain_actualmsg.equalsIgnoreCase(duplicatedomain_expectedmsg) || duplicatedomain_actualmsg.equalsIgnoreCase(invalidemail_expectedmsg)))) && (table.get(Constants.EXPECTEDRESULT_COL)).equals("FAILURE"))){
			 //System.out.println("Validation message :"+duplicatedomain_actualmsg+" is displayed during Sign up" );
				APPLICATION_LOG.debug("Validation message :"+duplicatedomain_actualmsg+" is displayed during Sign up" );
				APPLICATION_LOG.debug("Duplicate/Invalid Credentials! UserName :"+table.get(Constants.USERNAME_COL) +" Domain name :" +table.get(Constants.DOMAINNAME_COL) );
				duplicatedomain_actualmsg=null;
		 }
			
		else{
			 Thread.sleep(3000);
	         SignUpConfirm_expectedmsg="Thank you for signing up with Campaign On The Cloud!";
	         SignUpConfirm_actualmsg=driver.findElement(By.xpath("//*[@id='divfreeplanresults']/div[1]/table/tbody/tr[1]/td/label")).getText();
	         System.out.println("Actual Message : "+SignUpConfirm_actualmsg);
				
		  try{
			  /*System.out.println("Inside try");
			  System.out.println("Actual : " +SignUpConfirm_actualmsg);
			  System.out.println("Expected : " +SignUpConfirm_expectedmsg);*/
			  Assert.assertEquals(SignUpConfirm_actualmsg,SignUpConfirm_expectedmsg);
			  APPLICATION_LOG.debug("Confirmation message :"+SignUpConfirm_actualmsg +" is displayed after Sign up" );
	          APPLICATION_LOG.debug("Signed up User name :"+table.get(Constants.USERNAME_COL) +" Domain name :" +table.get(Constants.DOMAINNAME_COL) );
	         } catch(Throwable e){
	         //System.out.println("Inside exception catch");
             APPLICATION_LOG.debug("SignUp failed .Exception in sign up : " +e);
	         }
		 	   
		}
	}
	@AfterMethod
	public void close(){
		if (!(driver==null)){
		driver.quit();
		}
	}
	@AfterClass
	public void endTime(){
		APPLICATION_LOG.debug(" Sign Up Regression Test End Date/Time ");
		getcurrentdate();
		
	}

}
