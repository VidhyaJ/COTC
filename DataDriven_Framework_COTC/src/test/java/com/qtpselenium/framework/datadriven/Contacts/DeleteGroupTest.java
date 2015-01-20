package com.qtpselenium.framework.datadriven.Contacts;

import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qtpselenium.framework.datadriven.TestBase;
import com.qtpselenium.framework.datadriven.util.Constants;
import com.qtpselenium.framework.datadriven.util.ErrorUtil;
import com.qtpselenium.framework.datadriven.util.TestDataProvider;

public class DeleteGroupTest extends TestBase{
	
	@BeforeTest
	public void initLogs(){
	initLogs(this.getClass());
	}
	
	@Test(dataProviderClass=TestDataProvider.class,dataProvider="ContactsSuiteDataProvider")
	public void deletegrouptest(Hashtable<String,String> table) throws InterruptedException{
	
		 APPLICATION_LOG.debug("Executing Delete Group Test");
		 validateRunmodes("DeleteGroupTest", Constants.CONTACTS_SUITE, table.get("Runmode"));
	     //doLogin(table.get(Constants.BROWSER_COL),table.get(Constants.USERNAME_COL),table.get(Constants.PASSWORD_COL));
		 Thread.sleep(5000L);
	     try{
		 Assert.assertTrue(isElementPresent("ShowGroups_xpath"), "Element not found - Show Groups Tab");
	     }catch(Throwable t){
	    	 ErrorUtil.addVerificationFailure(t); 
	     }
	     
	      click("ShowGroups_xpath"); 
		 
		 input("SearchGroup_xpath",sessionData.get("groupnamecreated"));
		 System.out.println("The group name created is : "+sessionData.get("groupnamecreated"));
		 click("SearchGroupicon_xpath");
		 
		 
		 Thread.sleep(5000L);
			List<WebElement> rows = driver.findElements(By.xpath("//table[@id='tblgridview_load_groupcontacts']/tbody/tr"));
			
			for (int i=1;i<=rows.size();i++){
				
				String xpath1="//div[@id='load_groupcontacts_table']/table/tbody/tr[";
				String xpath2="]/td[2]/input";
				String xpathname1=xpath1+i+xpath2;
				String x = driver.findElement(By.xpath(xpathname1)).getAttribute("value");
				String y=sessionData.get("groupnamecreated");
				try{
				Assert.assertEquals(x, y);
				String xpath3="//div[@id='load_groupcontacts_table']/table/tbody/tr[";
				String xpath4="]/td[1]/label";
				String xpathname2=xpath3+i+xpath4;
				driver.findElement(By.xpath(xpathname2)).click();
				
				} catch (Throwable t){
					System.out.println("The actual is :" +x+ "The expected is :" +y);
				}
				
			}
			
			
			click("DeleteGroups");
			Thread.sleep(2000L);
			
			((JavascriptExecutor) driver).executeScript("contact_deletegroupandcontact();");
			Thread.sleep(5000L);
			driver.findElement(By.xpath("//*[@id='popup_ok']")).click();
			//click("DeleteOk");
	}
	   
}
