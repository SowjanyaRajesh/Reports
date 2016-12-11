package com.uscourts2.ujspacourts.testCases;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
		 
		
		if(Utility.isTestCaseRunnable("Tests","docket",xls))
		{		
			
		//	System.out.println(data.get("KeyWord"));
		//	System.out.println(data.get("Object"));
		
		execute(data);
		}
		
	}
	@Test(dataProvider="getData",priority=3,dependsOnMethods={"startTest","docketSheets"})
	public void Login(Hashtable<String,String> data) 
	{
		if(Utility.isTestCaseRunnable("Tests","Login",xls))
		{		
			
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
