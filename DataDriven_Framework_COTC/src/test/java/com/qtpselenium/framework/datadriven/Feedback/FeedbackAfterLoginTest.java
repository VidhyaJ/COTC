package com.qtpselenium.framework.datadriven.Feedback;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qtpselenium.framework.datadriven.TestBase;
import com.qtpselenium.framework.datadriven.util.Constants;
import com.qtpselenium.framework.datadriven.util.ErrorUtil;
import com.qtpselenium.framework.datadriven.util.TestDataProvider;

/*TO DO
 * 1.Webdriver wait needs to be added only if Dashboard is present.
 * 2.Verify Page Title
 * 3.Add attachments to the Feedback
 */

 /* COMPLETED
  * POSTING FEEDBACK WITHOUT ATTACHMENT
  */


public class FeedbackAfterLoginTest extends TestBase {
	
	@BeforeTest
	public void initLogs(){
	initLogs(this.getClass());
	}
	
	@Test(dataProviderClass=TestDataProvider.class,dataProvider="FeedbackSuiteDataProvider")
	public void feedbackafterlogintest(Hashtable<String,String> table) throws InterruptedException{
	
		APPLICATION_LOG.debug("Executing Feedback After Login Test");
		validateRunmodes("FeedbackAfterLoginTest", Constants.FEEDBACK_SUITE, table.get("Runmode"));
	    //doLogin(table.get(Constants.BROWSER_COL),table.get(Constants.USERNAME_COL),table.get(Constants.PASSWORD_COL));
		
		//WebDriverWait wait = new WebDriverWait(driver,25);
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='Dashboard']")));

	    try{
	    Assert.assertTrue(isElementPresent("FeedbackButton_xpath"), "Element not found loginlink_xpath");
	    } catch(Throwable t){
			ErrorUtil.addVerificationFailure(t);
		}
	    click("FeedbackButton_xpath");
	    
		input("FeedbackEmail_xpath",table.get("email"));
		input("FeedbackScreenname_xpath",table.get("label"));
		input("FeedbackComments_xpath",table.get("comments"));
		click("FeedbackSend_xpath");
		click("FeedbackClose_xpath");

	
	//((JavascriptExecutor) driver).executeScript("document.getElementById('show_feedbackform()').click()");
	//driver.findElement(By.xpath("//*[@id='master_setting']/table/tbody/tr/td[2]/img")).click();

	
	//driver.findElement(By.xpath("//div[@id='load_editorfeedback_comments']/div/iframe")).sendKeys(comments);
    //driver.findElement(By.xpath("//*[@id='static_save_close_box']/table/tbody/tr/td[2]/span")).click();
			

}
}
