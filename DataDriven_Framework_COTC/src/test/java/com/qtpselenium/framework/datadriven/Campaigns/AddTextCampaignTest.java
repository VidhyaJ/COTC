package com.qtpselenium.framework.datadriven.Campaigns;

import java.util.Hashtable;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.qtpselenium.framework.datadriven.TestBase;
import com.qtpselenium.framework.datadriven.util.Constants;
import com.qtpselenium.framework.datadriven.util.TestDataProvider;

public class AddTextCampaignTest extends TestBase{
	
	@BeforeTest
	public void initLogs(){
	initLogs(this.getClass());
	}
	
	@Test(dataProviderClass=TestDataProvider.class,dataProvider="CampaignSuiteDataProvider")
	public void addTextcampaigntest(Hashtable<String,String> table){
	
		APPLICATION_LOG.debug("Executing Add Mobile Campaign Test");
		validateRunmodes("AddTextCampaignTest", Constants.CAMPAIGN_SUITE, table.get("Runmode"));
	    //doLogin(table.get(Constants.BROWSER_COL),table.get(Constants.USERNAME_COL),table.get(Constants.PASSWORD_COL));

}

}


