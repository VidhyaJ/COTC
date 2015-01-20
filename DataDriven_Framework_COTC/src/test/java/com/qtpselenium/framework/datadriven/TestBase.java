package com.qtpselenium.framework.datadriven;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.http.client.fluent.Request;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.SkipException;

import com.qtpselenium.framework.datadriven.util.Constants;
import com.qtpselenium.framework.datadriven.util.Utility;
import com.qtpselenium.framework.datadriven.util.Xls_Reader;


public class TestBase {
	public static Properties prop;
	public static Logger APPLICATION_LOG = Logger.getLogger("devpinoyLogger");
	//public static RemoteWebDriver driver=null;
	public static Set<?> winIds;
	public static Iterator<?> it;
	public static WebDriver driver=null;
	public static Hashtable<String,String> sessionData = new Hashtable<String,String>();
	
	public void initLogs(Class<?> class1){

		FileAppender appender = new FileAppender();
		// configure the appender here, with file location, etc
		appender.setFile(System.getProperty("user.dir")+"//target//Reports//"+CustomListener.resultFolderName+"//"+class1.getName()+".log");
		appender.setLayout(new PatternLayout("%d %-5p [%c{1}] %m%n"));
		appender.setAppend(false);
		appender.activateOptions();

		APPLICATION_LOG = Logger.getLogger(class1);
		APPLICATION_LOG.setLevel(Level.DEBUG);
		APPLICATION_LOG.addAppender(appender);
	}
	
	
	
	public static void init(){
		if(prop == null){
			String path=System.getProperty("user.dir")+"\\src\\test\\resources\\project.properties";			 prop = new Properties();
			try {
				FileInputStream fs = new FileInputStream(path);
				prop.load(fs);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void validateRunmodes(String testName,String suiteName,String dataRunmode){
		APPLICATION_LOG.debug("Validating runmode for : "+testName+" in suite "+ suiteName);
		System.out.println("Validating runmode for  : "+testName+" in suite  :"+ suiteName+ " and for datamode: " +dataRunmode);
		init();
		//suite runmode
		boolean suiteRunmode=Utility.isSuiteRunnable(suiteName, new Xls_Reader(prop.getProperty("xlsFileLocation")+"COTCSuite.xlsx"));
		System.out.println("SuiteRunMode : " +suiteRunmode);
		boolean testRunmode=Utility.isTestCaseRunnable(testName, new Xls_Reader(prop.getProperty("xlsFileLocation")+suiteName+".xlsx"));
		System.out.println("TestRunmode : " +testRunmode);
		boolean dataSetRunmode=false;
		if(dataRunmode.equalsIgnoreCase(Constants.RUNMODE_YES))
			dataSetRunmode=true;
		
		if(!(suiteRunmode && testRunmode && dataSetRunmode)){
			APPLICATION_LOG.debug("Skipping the test : "+testName+" inside the suite  : "+ suiteName);
			throw new SkipException("Skipping the test : "+testName+" inside the suite  : "+ suiteName);
		}
		
	}
	/****************Generic functions*********************/
	public void openBrowser(String browserType){
		
		//System.out.println("Inside Open Browser");
		
		if(browserType.equalsIgnoreCase("Mozilla"))
			driver= new FirefoxDriver();
		else if(browserType.equalsIgnoreCase("Chrome")){
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\chromedriver.exe");
			driver= new ChromeDriver();}
	 else if(browserType.equals("IE")){
				//System.setProperty("webdriver.ie.driver", prop.getProperty("chromedriverexe"))
		    System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"\\IEDriverServer.exe" );
		    driver= new InternetExplorerDriver();
				}
		
		
		/*try{
		DesiredCapabilities cap = new DesiredCapabilities();
		if(browserType.equals("Mozilla")){
			cap.setBrowserName("firefox");
		}else if(browserType.equals("Chrome")){
			cap.setBrowserName("chrome"); // iexplore
		}
		cap.setPlatform(Platform.ANY);
		try {
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),cap);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}catch(Exception e){
			Assert.fail("Not able to open browser - "+e.getMessage());
		}*/
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		
	}
	
	public void navigate(String URLKey){
		
		//System.out.println(prop.getProperty(URLKey));
		//driver.get("http://www/campaignonthecloud.com");
		driver.get(prop.getProperty(URLKey));
		
	}
	
	public void click(String identifier){
		try{
		if(identifier.endsWith("_xpath"))
			driver.findElement(By.xpath(prop.getProperty(identifier))).click();
		else if(identifier.endsWith("_id"))
			driver.findElement(By.id(prop.getProperty(identifier))).click();
		else if(identifier.endsWith("_name"))
			driver.findElement(By.name(prop.getProperty(identifier))).click();
		}catch(NoSuchElementException e){
			Assert.fail("Element not found - "+identifier);
		}

	}
	
	public void clear(String identifier){
		try{
		if(identifier.endsWith("_xpath"))
		{
		    driver.findElement(By.xpath(prop.getProperty(identifier))).sendKeys(Keys.CONTROL+"a");
			driver.findElement(By.xpath(prop.getProperty(identifier))).sendKeys(Keys.DELETE);
		}
		else if(identifier.endsWith("_id")){
			driver.findElement(By.id(prop.getProperty(identifier))).sendKeys(Keys.CONTROL+"a");
			driver.findElement(By.id(prop.getProperty(identifier))).sendKeys(Keys.DELETE);
		}
		else if(identifier.endsWith("_name")){
			driver.findElement(By.name(prop.getProperty(identifier))).sendKeys(Keys.CONTROL+"a");
		driver.findElement(By.name(prop.getProperty(identifier))).sendKeys(Keys.DELETE);
		}
		}catch(NoSuchElementException e){
			Assert.fail("Element not found - "+identifier);
		}

	}
	
	public void input(String identifier,String data){
		try{
		if(identifier.endsWith("_xpath"))
			driver.findElement(By.xpath(prop.getProperty(identifier))).sendKeys(data);
		else if(identifier.endsWith("_id"))
			driver.findElement(By.id(prop.getProperty(identifier))).sendKeys(data);
		else if(identifier.endsWith("_name"))
			driver.findElement(By.name(prop.getProperty(identifier))).sendKeys(data);
		}catch(NoSuchElementException e){
			Assert.fail("Element not found - "+identifier);
		}
	}
	
	public boolean verifyTitle(String expectedTitleKey){
		String expectedTitle=prop.getProperty(expectedTitleKey);
		String actualTitle=driver.getTitle();
		if(expectedTitle.equals(actualTitle))
			return true;
		else
			return false;
	}
	
	public boolean verifyURL(String expectedURL,String actualURL){
		//String expectedTitle=prop.getProperty(expectedTitleKey);
		//String actualURL=driver.getTitle();
		if(expectedURL.equalsIgnoreCase(actualURL))
			return true;
		else
			return false;
	}
	
	public boolean isElementPresent(String identifier){
		int size=0;
		if(identifier.endsWith("_xpath"))
			size = driver.findElements(By.xpath(prop.getProperty(identifier))).size();
		else if(identifier.endsWith("_id"))
			size = driver.findElements(By.id(prop.getProperty(identifier))).size();
		else if(identifier.endsWith("_name"))
			size = driver.findElements(By.name(prop.getProperty(identifier))).size();
		else // not in prop file
			size=driver.findElements(By.xpath(identifier)).size();
		
		if(size>0)
			return true;
		else
			return false;
	}
	
	public String getText(String identifier){
		String  text="";
		if(identifier.endsWith("_xpath"))
			text = driver.findElement(By.xpath(prop.getProperty(identifier))).getText();
		else if(identifier.endsWith("_id"))
			text = driver.findElement(By.id(prop.getProperty(identifier))).getText();
		else if(identifier.endsWith("_name"))
			text = driver.findElement(By.name(prop.getProperty(identifier))).getText();
		
		return text;
		
	}
	
	public static boolean checkResponse(String url){
		try {
			driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
         int resp_code= Request.Get(url).execute().returnResponse().getStatusLine()
                 .getStatusCode();
         System.out.println("Respose code for URL "+ url +" is -> "+ resp_code);
         APPLICATION_LOG.debug("Respose code for URL "+ url +" is -> "+ resp_code);
        if(resp_code == 200)
     	   return true;
        else
     	   return false;
     } catch (Exception e) {
    	 System.out.println("Exception is " +e);
    	   	   return false;
    	   	   }
     }
	
	public void quit(){
		if(driver!=null){
			driver.quit();
			driver=null;
		}
	}
	
	/*****************Application specific functions*******************/
	
	public void doLogin(String browser,String username,String password){
		openBrowser(browser);
		navigate("testSiteURL");
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		input("loginemail_xpath",username);
		input("loginpassword_xpath",password);
		click("loginButtondomain_xpath");
		
		//verifyTitle("LoginPage");
		//Assert.assertTrue(verifyTitle("LoginPage"),"Titles do not match.Got title as -" +driver.getTitle());
		
	
	}
	
	public void doWebsiteLogin(String domainname,String username,String password){
		
		click("loginlink_xpath");
		try {
			Thread.sleep(3000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		input("domain_xpath",domainname);
		input("email_xpath",username);
		input("password_xpath",password);
		click("loginButton_xpath");
	
	}
	
	public void dodefaultLogin(String browser,String username,String password,String domainname){
		openBrowser(browser);
		navigate("defaultestSiteURL");
	}
	

   public void logout() throws InterruptedException{
	   System.out.println("inside logout");
	    click("dropdown_xpath");
		Thread.sleep(3000);
		click("Logout_xpath");
		Thread.sleep(3000);
		System.out.println(driver.getCurrentUrl());
		Assert.assertTrue(isElementPresent("loginButtondomain_xpath"), "User not logged out");
   }
	
	
	//Get the page loading time for each link
	public static void getResponseTime(WebDriver driver){
		
		long start = System.currentTimeMillis();
        driver.getCurrentUrl();
        long finish = System.currentTimeMillis();
        long totalTime = finish - start;
        APPLICATION_LOG.debug("Total Time for the page : " +driver.getCurrentUrl() + " to load is( in milliseconds) :  " +totalTime+" Start : " +start+" Finish : "+finish);
        
       }

	//capture the screenshot of the tested pages.
	public static void getScreenshot(WebDriver driver) throws IOException{
		File srcFile =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		org.apache.commons.io.FileUtils.copyFile(srcFile,new File(System.getProperty("user.dir")+"//target//Reports//"+srcFile.getName()));
	}
    
	public static void closewindows(WebDriver driver) throws IOException{
		
		Iterator<?> it= winIds.iterator();
		System.out.println("Inside CloseWindows");
		String mainwindow=(String) it.next();
		
		while(it.hasNext()){
			String newwin=(String) it.next();
			
			driver.switchTo().window(newwin);
			//System.out.println(driver.getCurrentUrl());
			getResponseTime(driver);
			checkResponse(driver.getCurrentUrl());
			getScreenshot(driver);
			driver.close();
			driver.switchTo().window(mainwindow);
			//System.out.println("After switching back : " +driver.getCurrentUrl());
		}
			
	}
	
	public static void getcurrentdate()
	 {
		  int day, month, year;
	      int second, minute, hour;
	      GregorianCalendar date = new GregorianCalendar();
	      
	      day = date.get(Calendar.DAY_OF_MONTH);
	      month = date.get(Calendar.MONTH);
	      year = date.get(Calendar.YEAR);

	      second = date.get(Calendar.SECOND);
	      minute = date.get(Calendar.MINUTE);
	      hour = date.get(Calendar.HOUR);
	     
	      System.out.println("Current date is  "+day+"/"+(month+1)+"/"+year);
	      System.out.println("Current time is  "+hour+" : "+minute+" : "+second);
	      APPLICATION_LOG.debug("Current date is  "+day+"/"+(month+1)+"/"+year+" Current time is  "+hour+" : "+minute+" : "+second);
	   }
	
	// make zip of reports
		public static void zip(String filepath){
		 	try
		 	{
		 		File inFolder=new File(filepath);
		 		File outFolder=new File(filepath+"\\Results.zip");
		 		ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(outFolder)));
		 		BufferedInputStream in = null;
		 		byte[] data  = new byte[1000];
		 		String files[] = inFolder.list();
		 		for (int i=0; i<files.length-1; i++)
		 		{
		 			in = new BufferedInputStream(new FileInputStream
		 			(inFolder.getPath() + "/" + files[i]), 1000);  
		 			out.putNextEntry(new ZipEntry(files[i])); 
		 			int count;
		 			while((count = in.read(data,0,1000)) != -1)
		 			{
		 				out.write(data, 0, count);
		 			}
		 			out.closeEntry();
	  }
	  out.flush();
	  out.close();
		 	
	}
	  catch(Exception e)
	  {
		   System.out.println("inside exception");
		  e.printStackTrace();
	   }	
		}
	    
}
	
	
	
	
	

