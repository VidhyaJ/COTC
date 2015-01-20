package com.qtpselenium.framework.datadriven.Login;

/*To Do
1.Check http response for each redirected page
2.Check response time for each page to load
3.Try to remove Thread.sleep
4.Check if link is displayed and then click
5.Redirection to Social sites
5.Redirection to Privacy and Terms and Conditions

Completed
1.Redirection of the topbanner and bottom links in the landing page
2.Logging of results in Application log file

Authors
1.Vidhya 2.Vignesh
Last Updated date : 16/10/2014*/


import java.io.IOException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qtpselenium.framework.datadriven.TestBase;
import com.qtpselenium.framework.datadriven.util.Constants;
import com.qtpselenium.framework.datadriven.util.TestDataProvider;

public class LandingPageTest extends TestBase{
	

	public static WebElement topBanner,socialfooter,policyfooter;
	public static int i;
	public static boolean flag;
	
		@BeforeTest
		public void initLogs(){
		initLogs(this.getClass());
		}
	
		@Test(dataProviderClass=TestDataProvider.class,dataProvider="LoginSuiteDataProvider")
		public void landingpageTest(Hashtable<String,String> table) throws InterruptedException, IOException{
		
			APPLICATION_LOG.debug("Executing LandingPage Test");
			validateRunmodes("LandingPageTest", Constants.LOGIN_SUITE, table.get("Runmode"));
			dodefaultLogin(table.get(Constants.BROWSER_COL),table.get(Constants.USERNAME_COL),table.get(Constants.PASSWORD_COL),table.get(Constants.DOMAINNAME_COL));
		   
			//Check for the page title - Digital Marketing Campaign | Online Marketing - Campaign On The Cloud
			Assert.assertTrue(verifyTitle("LandingPage"),"Titles do not match.Got title as -" +driver.getTitle());
			
			APPLICATION_LOG.debug(" Landing Page Regression Test Start Date/Time ");
			getcurrentdate();
			
//***************************************Test Case 1.Get all links of top banner and click**************************************/
			topBanner=driver.findElement(By.className("topmenu"));
			java.util.List<WebElement> allTopLinks = topBanner.findElements(By.tagName("a"));
			int z = allTopLinks.size();
			for (i=0;i<z;i++){
				allTopLinks = driver.findElements(By.tagName("a"));
				flag = allTopLinks.get(i).isDisplayed();
				APPLICATION_LOG.debug(allTopLinks.get(i).getText() + " is displayed in the Landing Page Top Banner");
				if(flag==true){
					allTopLinks.get(i).click();
					winIds= driver.getWindowHandles();
								
					if (!(winIds.size()>1))
					{
						getResponseTime(driver);
						checkResponse(driver.getCurrentUrl());
						getScreenshot(driver);
						System.out.println("-------------------------------------------------------------------------------------------");
					}
					else
					{
						closewindows(driver);
						System.out.println("-------------------------------------------------------------------------------------------");
					}
					
				} else{
					APPLICATION_LOG.debug(allTopLinks.get(i).getText() + " is not displayed in the Landing Page Top Banner");
				}
			}	
			    
			    driver.findElement(By.xpath("//*[@id='lnkbtnHomeClick']")).click();
			    Thread.sleep(3000);
			    
			    
//**********************************Test Case 2 : Get all links at bottom of the page and click -- Developed By Vignesh********************************/
			    WebElement footerBanner = driver.findElement(By.xpath("//div[@id='containerBottom']"));
				java.util.List<WebElement> allfooterLinks = footerBanner.findElements(By.tagName("label"));
				int x = allfooterLinks.size();
				APPLICATION_LOG.debug("Checking links at the bottom of the Landing Page");
								
				for (i=0;i<x;i++){
					footerBanner= driver.findElement(By.xpath("//div[@id='containerBottom']"));  
					   String linkName=footerBanner.findElements(By.tagName("label")).get(i).getText();
					   footerBanner.findElements(By.tagName("label")).get(i).click();
					   APPLICATION_LOG.debug(linkName+" is displayed and clicked in the Landing Page Footer section");
					    winIds= driver.getWindowHandles();
						Iterator it= winIds.iterator();
						if (!(winIds.size()>1))
							{
								getResponseTime(driver);
								checkResponse(driver.getCurrentUrl());
								getScreenshot(driver);
								System.out.println("-------------------------------------------------------------------------------------------");
								driver.navigate().back();
							}
							else
							{
								closewindows(driver);
								System.out.println("-------------------------------------------------------------------------------------------");
							}
					Thread.sleep(3000);
			
				}
	//*********************************************Test Case  3 : Check for Social Sites redirection********************************************/
	//*********************************************Developed by Arjun and modified by Vidhya****************************************************/
				
				  socialfooter=driver.findElement(By.xpath("//*[@id='footerMenu']/div[2]/div/table/tbody/tr/td[2]"));
		 		  List<WebElement> socialsites=socialfooter.findElements(By.tagName("a"));
		 		  System.out.println("Social sites size is : " +socialsites.size());
		 		  int socialsize=socialsites.size();
		 		  for(i=0;i<socialsize;i++)
		 	      {
		 			 socialsites=socialfooter.findElements(By.tagName("a"));
		 			 flag = socialsites.get(i).isDisplayed();
		 			if(flag==true){
						socialsites.get(i).click();
						winIds= driver.getWindowHandles();
										
						if (!(winIds.size()>1))
						{
							getResponseTime(driver);
							checkResponse(driver.getCurrentUrl());							
							getScreenshot(driver);
							System.out.println("-------------------------------------------------------------------------------------------");
						}
						else
						{
							System.out.println("Title is : "+driver.getTitle());
							closewindows(driver);
							System.out.println("-------------------------------------------------------------------------------------------");
						}
						
					}
		 	      } 			
		 			
//************************Test Case 4 : Verify the Copyright information displayed at the bottom of the home page*******************************/ 
//**************************Developed by Vignesh and modified by Vidhya*************************************************************************/
		 			 
		 		      driver.get("http://www.campaignsonthecloud.com");
		 		      String copyrighttext = driver.findElement (By.xpath("html/body/form/div[5]/div[2]/span[1]")).getText();
		 		      try{
			 			   Assert.assertEquals(copyrighttext,"Copyright © 2015 InfoWave Knowledgeware LLC. All rights reserved."); 
			 		  }catch(Throwable e){
			 			  System.out.println("Exception is displayed :"+e);
			 		   APPLICATION_LOG.debug(e+" Exception is displayed in the Home Page");
			 			 // e.printStackTrace(); 
			 		  }
			 		  System.out.println(copyrighttext);
			 		  APPLICATION_LOG.debug(copyrighttext+" is displayed in the Home Page");
   
//*************************************Test Case  5 : Check for Policy Footer links*****************************************************/
//*************************************Developed by Vignesh and modified by Vidhya******************************************************/
			 		  
					  policyfooter=driver.findElement(By.xpath("//*[@id='loginpagefooter']/div[2]/span[2]"));
					  List<WebElement> policyfooter_links= policyfooter.findElements(By.tagName("a"));
			 		  int policysize=policyfooter_links.size();
			 		  for(i=0;i<policysize;i++)
				 	      {
				 			policyfooter_links= policyfooter.findElements(By.tagName("a"));
			    			flag = policyfooter_links.get(i).isDisplayed();
			 	 			if(flag==true){
			 		 				policyfooter_links.get(i).click();
			 		 				APPLICATION_LOG.debug(policyfooter_links.get(i).getText()+" is displayed and clicked in the Landing Page Footer section");
			 						winIds= driver.getWindowHandles();
			 										
			 							if (!(winIds.size()>1))
			 							{
			 								getResponseTime(driver);
			 								checkResponse(driver.getCurrentUrl());							
			 								getScreenshot(driver);
			 								System.out.println("-------------------------------------------------------------------------------------------");
			 							}
			 							else
			 							{
			 								closewindows(driver);
			 								System.out.println("-------------------------------------------------------------------------------------------");
			 							}
			 							
			 							}
			 	   			 	      } 			
   APPLICATION_LOG.debug(" Landing Page Regression Test End Date/Time ");
   getcurrentdate();
								 					 		  
	driver.quit();
		}
}

