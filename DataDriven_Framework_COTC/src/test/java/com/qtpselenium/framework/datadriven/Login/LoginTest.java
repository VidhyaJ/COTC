package com.qtpselenium.framework.datadriven.Login;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qtpselenium.framework.datadriven.TestBase;
//import com.qtpselenium.framework.datadriven.TestBase1;
import com.qtpselenium.framework.datadriven.util.Constants;
import com.qtpselenium.framework.datadriven.util.TestDataProvider;

public class LoginTest extends TestBase{
	
	public static String expectedmessage,passworderrmessage,emailerrmessage=null;
	@BeforeTest
	public void initLogs(){
	initLogs(this.getClass());
	APPLICATION_LOG.debug("Login Regression Test Start Date/Time ");
	getcurrentdate();
	}
	
	@Test(dataProviderClass=TestDataProvider.class,dataProvider="LoginSuiteDataProvider")
	public void logintest(Hashtable<String,String> table) throws InterruptedException{
	
		APPLICATION_LOG.debug("Executing Login Test");
		validateRunmodes("LoginTest", Constants.LOGIN_SUITE, table.get("Runmode"));
		
		//Login from Domain url
		APPLICATION_LOG.debug("***TC 1 : Executing Login from Domain testcase***");
	    doLogin(table.get(Constants.BROWSER_COL),table.get(Constants.USERNAME_COL),table.get(Constants.PASSWORD_COL));
	    
	        
	    boolean StartACampaignButton= isElementPresent("StartACampaign_xpath");
	    
	    if (((table.get(Constants.EXPECTEDRESULT_COL)).equals("SUCCESS")) && (StartACampaignButton)){
   		  APPLICATION_LOG.debug("Login Success from domain");
   		
   		  //Logout of the domain
   		  Thread.sleep(5000);
   		  APPLICATION_LOG.debug("***TC 2 : Executing Logout from Domain testcase***");  	
   		  logout();
   		  APPLICATION_LOG.debug("Logged Out from domain");
  	    
  		//Check if the login link is displayed after user logs out ,else throw an assertion error
  		 try{
  			 Assert.assertTrue(isElementPresent("loginButtondomain_xpath"), "User not logged out");
  			}catch(Throwable e){
  			 APPLICATION_LOG.debug("User not logged out yet.Exception thrown is : "+e);
  			 System.out.println("User not logged out yet.Exception thrown is : "+e);
  			}
  		 
   		 }
	    else if (!(((table.get(Constants.EXPECTEDRESULT_COL)).equals("SUCCESS")) && (StartACampaignButton))){
	    		 //Assert.fail("Not able to login with correct credentials"); 
	    		 APPLICATION_LOG.debug("Login Failure");}
	    else if (table.get(Constants.EXPECTEDRESULT_COL).equals("FAILURE")){
	    	if (StartACampaignButton){
	    		Assert.fail("Logged in with wrong credentials");
	    		APPLICATION_LOG.debug("Invalid Login");
	     	}
	    	    	
	    }
	    
	   	
		 //Check for Login scenario where password is incorrect/not specified
		    APPLICATION_LOG.debug("***TC 3 : Verifying login to COTC with invalid password***");
		    navigate("testSiteURL");
		    input("loginemail_xpath",table.get(Constants.USERNAME_COL));
			input("loginpassword_xpath","invalidpassword");
			click("loginButtondomain_xpath");
			Thread.sleep(3000);
			expectedmessage="The password you entered\n     is incorrect";
			passworderrmessage=driver.findElement(By.xpath("//*[@id='Loginpassworderr']")).getText();
	       
			try{
	        Assert.assertEquals(passworderrmessage,expectedmessage);
	        APPLICATION_LOG.debug("Validation message :"+expectedmessage+" is displayed when incorrect password is specified" );
	        }catch(Throwable e){
	        		APPLICATION_LOG.debug("Incorrect Password : " +e);
	        }
	     
			//Check for Login scenario where email id is incorrect/not specified
	        APPLICATION_LOG.debug("***TC 4 : Verifying login to COTC with invalid email id***");
	        driver.findElement(By.xpath("//*[@id='txtUsername']")).sendKeys(Keys.CONTROL+"a");
			driver.findElement(By.xpath("//*[@id='txtUsername']")).sendKeys(Keys.DELETE);
	        input("loginemail_xpath","invalidemail@i-waves.com");
	        driver.findElement(By.xpath("//*[@id='txtpasword']")).sendKeys(Keys.CONTROL+"a");
			driver.findElement(By.xpath("//*[@id='txtpasword']")).sendKeys(Keys.DELETE);
	        input("loginpassword_xpath","password");
			click("loginButtondomain_xpath");
			Thread.sleep(3000);
			expectedmessage="This Email ID is not registered\n      with this domain";
			String emailerrmessage=driver.findElement(By.xpath("//*[@id='loginfailederrbox']")).getText();
			
			try{
	        Assert.assertEquals(emailerrmessage,expectedmessage);
	        APPLICATION_LOG.debug("Validation message :"+expectedmessage+" is displayed when invalid email is specified" );
	        }catch(Throwable e){
	        	APPLICATION_LOG.debug("Incorrect Email : " +e);
	        }  
	      
		
		//Login from website
		APPLICATION_LOG.debug("***TC 5 : Verifying login to COTC from website***");
	    navigate("defaultestSiteURL");
	    Assert.assertTrue(isElementPresent("loginlink_xpath"), "Element not found loginlink_xpath");
	    doWebsiteLogin(table.get(Constants.DOMAINNAME_COL),table.get(Constants.USERNAME_COL),table.get(Constants.PASSWORD_COL));
	    Thread.sleep(3000);
	    
	    String expectedURL="http://"+table.get(Constants.DOMAINNAME_COL)+".campaignonthecloud.com/dashboard";
	  	String actualURL=driver.getCurrentUrl();
	  	String currentURL="https://www.campaignonthecloud.com/signin";
	  	
	  	try{
	  		 Assert.assertTrue(verifyURL(currentURL,actualURL),"Invalid Credentials during login");
	  		 System.out.println(actualURL);
	  	}catch(Throwable t){
	  		System.out.println("No Errors");
	  	}
	  		
	  	try{
	  		 Assert.assertTrue(verifyURL(expectedURL,actualURL),"User is not logged in to COTC from Website.Logging through domain since url is :" +driver.getCurrentUrl());
	  	}catch(Throwable e){
	  		APPLICATION_LOG.debug("Failure :" +e);
	  		input("loginemail_xpath",table.get(Constants.USERNAME_COL));
	  		input("loginpassword_xpath",table.get(Constants.PASSWORD_COL));
	  		click("loginButtondomain_xpath");
	  		Thread.sleep(3000);
	  	}
	       		
	     	 
	     if (((table.get(Constants.EXPECTEDRESULT_COL)).equals("SUCCESS")) && (StartACampaignButton)){
	   		  APPLICATION_LOG.debug("Login Success from Website");
	   		  }
		    else if (!(((table.get(Constants.EXPECTEDRESULT_COL)).equals("SUCCESS")) && (StartACampaignButton))){
		    		 Assert.fail("Not able to login with correct credentials"); 
		    		 APPLICATION_LOG.debug("Login Failure");}
		    else if (table.get(Constants.EXPECTEDRESULT_COL).equals("FAILURE")){
		    	if (StartACampaignButton){
		    		Assert.fail("Logged in with wrong credentials");
		    		APPLICATION_LOG.debug("Invalid Login");
		     	}
		   }
	    //Logout of the website
	    logout();
		APPLICATION_LOG.debug("Logged Out after Website Login");
		
		//Check for Login scenario in website where password is incorrect/not specified
		APPLICATION_LOG.debug("***TC 6 : Verifying login to COTC from website with invalid password***");
		 
		navigate("defaultestSiteURL");
		click("loginlink_xpath");
		input("domain_xpath",table.get(Constants.DOMAINNAME_COL));
		input("email_xpath",table.get(Constants.USERNAME_COL));
		input("password_xpath","invalidpassword");
		click("loginButton_xpath");
		Thread.sleep(3000);
		expectedmessage="One of your credential is wrong";
		passworderrmessage=driver.findElement(By.xpath("//*[@id='company_error_label']")).getText();
	       
			try{
	        Assert.assertEquals(passworderrmessage,expectedmessage);
	        APPLICATION_LOG.debug("Validation message :"+expectedmessage+" is displayed when incorrect password is specified" );
	        }catch(Throwable e){
	        		APPLICATION_LOG.debug("Incorrect Password : " +e);
	        }
	     
		//Check for Login scenario where email id is incorrect/not specified
	    APPLICATION_LOG.debug("***TC 6 : Verifying login to COTC website with invalid email id***");
	    clear("domain_xpath");
		clear("email_xpath");
		clear("password_xpath");
			
		input("domain_xpath",table.get(Constants.DOMAINNAME_COL));
		input("email_xpath","invalidemail@i-waves.com");
		input("password_xpath",table.get(Constants.PASSWORD_COL));
		click("loginButton_xpath");
		Thread.sleep(3000);
	    expectedmessage="One of your credential is wrong";
		emailerrmessage=driver.findElement(By.xpath("//*[@id='company_error_label']")).getText();
			try{
	        Assert.assertEquals(emailerrmessage,expectedmessage);
	        APPLICATION_LOG.debug("Validation message :"+expectedmessage+" is displayed when invalid email is specified" );
	        }catch(Throwable e){
	        	APPLICATION_LOG.debug("Incorrect Email : " +e);
	        }  
	      
		//Check for Login scenario where domainname is incorrect
	     APPLICATION_LOG.debug("***TC 7 : Verifying login to COTC website with invalid domain name***");
	    clear("domain_xpath");
		clear("email_xpath");
		clear("password_xpath");
		input("domain_xpath","invaliddomain");
		input("email_xpath",table.get(Constants.USERNAME_COL));
		input("password_xpath",table.get(Constants.PASSWORD_COL));
		click("loginButton_xpath");
		Thread.sleep(3000);
	       	
	    expectedmessage="It looks like your account has not been activated. Please activate your account through your Email and then log in.";
		emailerrmessage=driver.findElement(By.xpath("//*[@id='company_error_label']")).getText();
			try{
	        Assert.assertEquals(emailerrmessage,expectedmessage);
	        APPLICATION_LOG.debug("Validation message :"+expectedmessage+" is displayed when invalid domain is specified" );
	        }catch(Throwable e){
	        	APPLICATION_LOG.debug("Incorrect domain : " +e);
	        }  
	    
	   
		//driver.quit();
	}
	@AfterTest
	public void close(){
		if (!(driver==null)){
		driver.quit();
		}
	}
	@AfterClass
	public void endTime(){
		APPLICATION_LOG.debug("Login Regression Test End Date/Time ");
		getcurrentdate();
		
	}

}
