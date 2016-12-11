package com.uscourts2.ujspacourts.testCases;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import junit.framework.Assert;

public class AllPages extends Keywords{

	
	
	public AllPages() throws IOException {
		super();
		// TODO Auto-generated constructor stub
	}

	@Test(priority=1)
	public void startTest() throws IOException
	{
		openBrowser();	
	}
	@Test(dataProvider="getData",priority=2)
	public void docketSheets(Hashtable<String,String> data) 
	{
		 
		test = extent.startTest("docket");
		if(Utility.isTestCaseRunnable("Tests","docket",xls))
		{		
			
		//	System.out.println(data.get("KeyWord"));
		//	System.out.println(data.get("Object"));
			test.log(LogStatus.INFO, "executing docket test");
		execute(data);
		Assert.assertEquals(dr.getTitle(), pr.getProperty("docket_title"));
		}
		
	}
	@Test(dataProvider="getData",priority=3,dependsOnMethods={"startTest","docketSheets"})
	public void Login(Hashtable<String,String> data) 
	{
		test = extent.startTest("Login");
		if(Utility.isTestCaseRunnable("Tests","Login",xls))
		{		
			test.log(LogStatus.INFO, "executing Login test");
			execute(data);
		}
		
		
	}
	
	
	@DataProvider
	public Object[][] getData(Method m)
	{
		System.out.println(m.getName());
		String testCase="";
		Object[][] data=null;
		if(m.getName().equals("docketSheets"))
			testCase="docket";
		else if(m.getName().equals("Login"))
			testCase="Login";
			
		data = Utility.getTestData("TestData",testCase,xls);
		return data;
	}
	
	
	
}
