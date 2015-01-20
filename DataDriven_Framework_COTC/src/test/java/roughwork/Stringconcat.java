package roughwork;

import java.util.Hashtable;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

import com.qtpselenium.framework.datadriven.TestBase;
import com.qtpselenium.framework.datadriven.util.Constants;
import com.qtpselenium.framework.datadriven.util.TestDataProvider;

public class Stringconcat extends TestBase{

	@Test(dataProviderClass=TestDataProvider.class,dataProvider="LoginSuiteDataProvider")
	public void logintest(Hashtable<String,String> table) throws InterruptedException{
		
		WebDriver driver = null; 
		       // System.out.println(driver.getCurrentUrl());
		        System.out.println(table.get(Constants.DOMAINNAME_COL));
		        String expectedURL="http://vidhyaautomate.campaignonthecloud.com/dashboard";
		        System.out.println("http://"+table.get(Constants.DOMAINNAME_COL)+".campaignonthecloud.com/dashboard");

	}

}
