package com.qtpselenium.framework.datadriven.Login;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qtpselenium.framework.datadriven.TestBase;
//import com.qtpselenium.framework.datadriven.TestBase1;
import com.qtpselenium.framework.datadriven.util.Constants;
import com.qtpselenium.framework.datadriven.util.TestDataProvider;

public class ForgotPasswordTest extends TestBase{
 public static String forgotpwd_expectedmsg;
 public static String forgotpwd_actualmsg;
	@BeforeTest
	public void initLogs(){
	initLogs(this.getClass());
	}
	
	@Test(dataProviderClass=TestDataProvider.class,dataProvider="LoginSuiteDataProvider")
	public void forgotpasswordtest(Hashtable<String,String> table) throws InterruptedException{
		    APPLICATION_LOG.debug(" Forgot Password Regression Test End Date/Time ");
		    getcurrentdate();
		   
		    validateRunmodes("ForgotPasswordTest", Constants.LOGIN_SUITE, table.get("Runmode"));
		    openBrowser(table.get(Constants.BROWSER_COL));
		    //Input values
		    driver.get(table.get(Constants.DOMAINNAME_COL));
			click("ForgotPassword_xpath");
			input("ForgotPasswordEmail_xpath",table.get(Constants.USERNAME_COL));
			click("ForgotPasswordSend_xpath");
			Thread.sleep(3000L);
			//Assertion
			forgotpwd_expectedmsg="Password has been sent to your mail!";
			forgotpwd_actualmsg=driver.findElement(By.xpath("//*[@id='passwordsentdiv']/div/div/div/div[2]/label")).getText();
			//System.out.println(forgotpwd_actualmsg);
			click("ForgotPasswordOk_xpath");
			 try{
				 Assert.assertEquals(forgotpwd_actualmsg,forgotpwd_expectedmsg);
				 APPLICATION_LOG.debug("Confirmation message :"+forgotpwd_expectedmsg +" is displayed in Forgot Password" );
		         APPLICATION_LOG.debug(" Password Requested for  :"+table.get(Constants.USERNAME_COL) +" Domain name :" +table.get(Constants.DOMAINNAME_COL));
		         }catch(Throwable e){
	             APPLICATION_LOG.debug("Exception in Forgot Password : " +e);
		         }
	}
 
	@AfterMethod
	public void close(){
		quit();
	}

}
