package com.qtpselenium.framework.datadriven.Contacts;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qtpselenium.framework.datadriven.TestBase;
//import com.qtpselenium.framework.datadriven.TestBase1;
import com.qtpselenium.framework.datadriven.util.Constants;
import com.qtpselenium.framework.datadriven.util.ErrorUtil;
import com.qtpselenium.framework.datadriven.util.TestDataProvider;

public class AddGroupTest extends TestBase{
	
	public static String expectedgroupmessage;
	public static String actualgroupmessage;
	public static boolean isloggedin=false;
	
 @BeforeTest
	 public void initLogs(){
	 initLogs(this.getClass());
	 APPLICATION_LOG.debug("Executing AddGroup Test");
 	}
	

	@Test(priority=1,dataProviderClass=TestDataProvider.class,dataProvider="ContactsSuiteDataProvider")
	public void addgrouptest(Hashtable<String,String> table) throws InterruptedException{

		  validateRunmodes("AddGroupTest", Constants.CONTACTS_SUITE, table.get("Runmode"));
		  if (!isloggedin){
		    doLogin(table.get(Constants.BROWSER_COL),table.get(Constants.USERNAME_COL),table.get(Constants.PASSWORD_COL));
	        Thread.sleep(20000L);
	        
		  try{
			 Assert.assertTrue(isElementPresent("ContactsTab_xpath"), "Element not found Contacts Tab");
			 }catch(Throwable t){
				 ErrorUtil.addVerificationFailure(t);
				 APPLICATION_LOG.debug("Contacts Tab not found." );
				 
			 }
		    
			 click("ContactsTab_xpath");
			 Thread.sleep(3000L);
			 isloggedin=true;
			 try{
		     Assert.assertTrue(isElementPresent("CreateNewGroup_xpath"), "Element not found CreateNewGroup icon"); 
		     }catch(Throwable t){
		    	 ErrorUtil.addVerificationFailure(t);
		    	 APPLICATION_LOG.debug("Create New Group icon is not found." );
		     }
		  }
		 		  
		 click("CreateNewGroup_xpath");
	     input("GroupName_xpath",table.get("groupname"));
	     sessionData.put("groupnamecreated", table.get("groupname"));
	     APPLICATION_LOG.debug("The group name entered is  : " +sessionData.get("groupnamecreated"));
	     click("GroupSave_xpath");
	     Thread.sleep(3000);
	     try{
	     expectedgroupmessage=table.get("message");
		 actualgroupmessage=driver.findElement(By.xpath("//*[@id='display_error_msg']")).getText();
		 
		 if ((table.get(Constants.EXPECTEDRESULT_COL).equals("FAILURE")) && (expectedgroupmessage.equalsIgnoreCase(actualgroupmessage)))
			   APPLICATION_LOG.debug("Group Name already exists.Message displayed is :"+actualgroupmessage );
		 else if ((table.get(Constants.EXPECTEDRESULT_COL).equals("SUCCESS")) && (expectedgroupmessage.equalsIgnoreCase(actualgroupmessage)))
			  APPLICATION_LOG.debug("Group Created.Message displayed is  :  " +actualgroupmessage);
		 else if ((table.get(Constants.EXPECTEDRESULT_COL).equals("FAILURE")) && (expectedgroupmessage.equalsIgnoreCase(actualgroupmessage)))
			 APPLICATION_LOG.debug("Duplicate group name created: " +actualgroupmessage);
		 else if ((table.get(Constants.EXPECTEDRESULT_COL).equals("FAILURE")) && !(expectedgroupmessage.equalsIgnoreCase(actualgroupmessage)))
			 APPLICATION_LOG.debug("Duplicate group names not accepted.");
      }catch(Throwable e){
	         //System.out.println("Inside exception catch");
             APPLICATION_LOG.debug("Group Creation failed .Exception in Groups: " +e);
	         }
		clear("GroupName_xpath");	
    }
	 @Test(priority=2)
     public void addgroupCancelTest() throws InterruptedException{
		 
		 input("GroupName_xpath","CheckCancelGroup");
	     click("GroupCancel_xpath");
	     Thread.sleep(3000);
		 
	 }
	     
	  	/*sessionData.put("groupnameintable", groupname);
		  //Thread.sleep(20000L);
	    
	   // WebDriverWait wait = new WebDriverWait(driver,40);
	   // wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='menucontacts_normal']")));
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='menucontacts_normal']")));
		 * //driver.findElement(By.xpath("//*[@id='menucontacts_normal']")).click();
		 * //System.out.println("Clicked Contacts");
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='addnewgroup_div']/table/tbody/tr/td/img]")));
	    //driver.findElement(By.xpath("//body")).sendKeys(Keys.F5);
	    //driver.findElement(By.xpath("//*[@id='menucontacts_normal']")).click();
		 */
				
	    

@AfterClass
public void close(){
	if (!(driver==null)){
		driver.quit();
		}
	APPLICATION_LOG.debug(" Add Group Test End Date/Time ");
	getcurrentdate();
	
}


	    
	}