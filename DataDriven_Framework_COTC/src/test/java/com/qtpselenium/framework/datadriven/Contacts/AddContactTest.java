package com.qtpselenium.framework.datadriven.Contacts;

import java.util.Hashtable;

import org.openqa.selenium.By;
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
	    //doLogin(table.get(Constants.BROWSER_COL),table.get(Constants.USERNAME_COL),table.get(Constants.PASSWORD_COL));
	    
		//WebDriverWait wait = new WebDriverWait(driver, 10);
        
        //WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("Xpath")));
	    //Assert.assertTrue(isElementPresent("ContactsTab_xpath"), "Element not found loginlink_xpath");
	    //click("ContactsTab_xpath");
		Thread.sleep(5000L);
	    //Assert.assertTrue(isElementPresent("CreateNewContact_xpath"), "Element not found loginlink_xpath");
	    click("CreateNewContact_xpath");
	    Thread.sleep(3000L);
	    
	    input("ContactName_xpath",table.get("contactname"));
	    input("ContactEmail_xpath",table.get("contactemail"));
	    input("ContactMobile_xpath",table.get("mobilenumber"));
	    input("Contact_Address",table.get("address"));
	    input("ContactCity_xpath",table.get("city"));
	    input("ContactZipcode_xapth",table.get("zipcode"));
	    input("ContactCompanyName_xpath",table.get("companyname"));
	    input("ContactBusiness_xpath",table.get("businessname"));
	    //input("ContactCountry_xpath",table.get("country"));
	    driver.findElement(By.xpath("//*[@id='cbo_contact_country']")).sendKeys(table.get("country"));
	    //input("ContactWebsite_xapth",table.get("website"));
	    driver.findElement(By.xpath("//*[@id='txt_contact_website']")).sendKeys(table.get("website"));
	    System.out.println("Groupname is  : " +sessionData.get("groupnamecreated"));
	    //input("Group_xpath",table.get("groupname"));
	    driver.findElement(By.xpath("//*[@id='drpdwn_addtogroup']")).sendKeys(sessionData.get("groupnamecreated"));
	    //input("Tag_xpath",table.get());
	    click("SaveContact_xpath");
	    Thread.sleep(5000L);
	    
	    

	    
	   
	    
	    /*
	     Thread.sleep(5000L);
		driver.findElement(By.xpath("//*[@id='txt_contact_name']")).sendKeys(contactname);
		driver.findElement(By.xpath("//*[@id='txt_contact_email']")).sendKeys(contactemail);
		driver.findElement(By.xpath("//*[@id='txt_mobile_number']")).sendKeys(mobilenumber);
		driver.findElement(By.xpath("//*[@id='txt_address']")).sendKeys(address);
		driver.findElement(By.xpath("//*[@id='txt_city']")).sendKeys(city);
		driver.findElement(By.xpath("//*[@id='txt_zipcode']")).sendKeys(zipcode);
		driver.findElement(By.xpath("//*[@id='txt_companyname']")).sendKeys(companyname);
		driver.findElement(By.xpath("//*[@id='txt_contact_business']")).sendKeys(businessname);
		driver.findElement(By.xpath("//*[@id='cbo_contact_country']")).sendKeys(country);
		driver.findElement(By.xpath("//*[@id='txt_contact_website']")).sendKeys(website);
		driver.findElement(By.xpath("//*[@id='drp_listfieldname1']")).sendKeys("cotc");
		driver.findElement(By.xpath("//*[@id='drpdwn_addtogroup']")).sendKeys(sessionData.get("groupnameintable"));
		driver.findElement(By.xpath("//*[@id='drpdwn_addgeneralTag']")).sendKeys("Interested");
		driver.findElement(By.xpath("//*[@id='create_newcontact_div']/div[4]/span[1]")).click();
		Thread.sleep(5000L);
	     */
}
}