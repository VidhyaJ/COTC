package com.qtpselenium.framework.datadriven.Contacts;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qtpselenium.framework.datadriven.TestBase;
import com.qtpselenium.framework.datadriven.util.Constants;
import com.qtpselenium.framework.datadriven.util.TestDataProvider;

public class AddContactTest extends TestBase{
	
	@BeforeTest
	public void initLogs(){
	initLogs(this.getClass());
	}
	
	@Test(dataProviderClass=TestDataProvider.class,dataProvider="ContactsSuiteDataProvider")
	public void addcontacttest(Hashtable<String,String> table) throws InterruptedException{
	
		APPLICATION_LOG.debug("Executing AddContact Test");
		validateRunmodes("AddContactTest", Constants.CONTACTS_SUITE, table.get("Runmode"));
	    doLogin(table.get(Constants.BROWSER_COL),table.get(Constants.USERNAME_COL),table.get(Constants.PASSWORD_COL));
	    		
		//Thread.sleep(5000);
	    WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='menucontacts_normal']")));
        Assert.assertTrue(isElementPresent("ContactsTab_xpath"), "Element not found loginlink_xpath");
	    click("ContactsTab_xpath");
	    element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='img_create_contact']")));
		//Thread.sleep(5000L);
	    Assert.assertTrue(isElementPresent("CreateNewContact_xpath"), "Element not found loginlink_xpath");
	    click("CreateNewContact_xpath");
	  
	       
	       
	    input("ContactDetails_xpath",table.get("contactemail1"));
	    driver.findElement(By.xpath(prop.getProperty("ContactDetails_xpath"))).sendKeys(Keys.ENTER);
	    input("ContactDetails_xpath",table.get("contactemail1"));
	    click("AddMultipleContactNext_xpath");
	    driver.quit();
	    
	}
}