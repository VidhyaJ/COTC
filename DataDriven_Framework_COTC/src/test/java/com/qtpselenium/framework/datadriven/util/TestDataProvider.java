package com.qtpselenium.framework.datadriven.util;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import com.qtpselenium.framework.datadriven.TestBase;
//import com.qtpselenium.framework.datadriven.TestBase1;

public class TestDataProvider {
	
	@DataProvider(name="LoginSuiteDataProvider")
	public static Object[][] getDataSuiteA(Method m){
		TestBase.init();
		System.out.println(TestBase.prop.getProperty("xlsFileLocation"));
		Xls_Reader xls1 = new Xls_Reader(TestBase.prop.getProperty("xlsFileLocation")+Constants.LOGIN_SUITE+".xlsx");

		return Utility.getData(m.getName(), xls1);
	}
	
	@DataProvider(name="ContactsSuiteDataProvider")
	public static Object[][] getDataSuiteB(Method m){
		TestBase.init();
		Xls_Reader xls1 = new Xls_Reader(TestBase.prop.getProperty("xlsFileLocation")+Constants.CONTACTS_SUITE+".xlsx");

		return Utility.getData(m.getName(), xls1);
	}
	
	@DataProvider(name="FeedbackSuiteDataProvider")
	public static Object[][] getDataSuiteC(Method m){
		TestBase.init();
		System.out.println(TestBase.prop.getProperty("xlsFileLocation"));
		Xls_Reader xls1 = new Xls_Reader(TestBase.prop.getProperty("xlsFileLocation")+Constants.FEEDBACK_SUITE+".xlsx");

		return Utility.getData(m.getName(), xls1);
	}
	
	@DataProvider(name="CampaignSuiteDataProvider")
	public static Object[][] getDataSuiteD(Method m){
		TestBase.init();
		System.out.println(TestBase.prop.getProperty("xlsFileLocation"));
		Xls_Reader xls1 = new Xls_Reader(TestBase.prop.getProperty("xlsFileLocation")+Constants.CAMPAIGN_SUITE+".xlsx");

		return Utility.getData(m.getName(), xls1);
	}


}
