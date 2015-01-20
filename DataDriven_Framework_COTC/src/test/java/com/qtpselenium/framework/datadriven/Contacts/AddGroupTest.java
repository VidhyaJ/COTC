package com.qtpselenium.framework.datadriven.Contacts;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qtpselenium.framework.datadriven.TestBase;
//import com.qtpselenium.framework.datadriven.TestBase1;
import com.qtpselenium.framework.datadriven.util.Constants;
import com.qtpselenium.framework.datadriven.util.ErrorUtil;
import com.qtpselenium.framework.datadriven.util.TestDataProvider;

public class AddGroupTest extends TestBase{
	/*TO DO
     * 1.Remove the Thread.sleep and optimise the code to wait for the page load before the contacts can be clicked
     * 2.Verify Page Title
     * 3.Verify the confirmation message displayed after Group creation
     * 4.Create a duplicate group and check validation
     * 5.Add assertions to the Contacts tab availability and Create New Group image availability.
     * 6.Handle Exceptions
     */
	
	 /* COMPLETED
	  * LOGIN , CHOOSE CONTACTS TAB AND ADD TO GROUP IMAGE
	  * ADD A GROUP
	  */
	@BeforeTest
	public void initLogs(){
	initLogs(this.getClass());
	}
	
	@Test(dataProviderClass=TestDataProvider.class,dataProvider="ContactsSuiteDataProvider")
	public void addgrouptest(Hashtable<String,String> table) throws InterruptedException{
	
		 APPLICATION_LOG.debug("Executing AddGroup Test");
		 validateRunmodes("AddGroupTest", Constants.CONTACTS_SUITE, table.get("Runmode"));
	     //doLogin(table.get(Constants.BROWSER_COL),table.get(Constants.USERNAME_COL),table.get(Constants.PASSWORD_COL));
	   
		 Thread.sleep(20000L);
		 try{
		 Assert.assertTrue(isElementPresent("ContactsTab_xpath"), "Element not found Contacts Tab");
		 }catch(Throwable t){
			 ErrorUtil.addVerificationFailure(t);
		 }
		 click("ContactsTab_xpath");
		
	     Thread.sleep(3000L);
	     try{
	     Assert.assertTrue(isElementPresent("CreateNewGroup_xpath"), "Element not found CreateNewGroup icon"); 
	     }catch(Throwable t){
	    	 ErrorUtil.addVerificationFailure(t);
	     }
	     
	    
	     click("CreateNewGroup_xpath");
	     input("GroupName_xpath",table.get("groupname"));
	     sessionData.put("groupnamecreated", table.get("groupname"));
	     System.out.println("The group name created in Group is : " +sessionData.get("groupnamecreated"));
	     click("GroupSave_xpath");
	     
	     
	    
	
	     
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
				
	    }
	    
	}