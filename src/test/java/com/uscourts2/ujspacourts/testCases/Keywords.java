package com.uscourts2.ujspacourts.testCases;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.jetty.util.log.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import com.uscourts2.ujspacourts.base.Xls_Reader;

import jxl.common.AssertionFailed;

public class Keywords{
	
	WebDriver dr;
	public Xls_Reader xls;
	Properties pr =new Properties();
	Logger log=null;
	String fileEnd=null;
	
	public Keywords() throws IOException {
		xls = new Xls_Reader(System.getProperty("user.dir")+"\\Data.xlsx");
		log = Logger.getLogger("devpinoyLogger");
		PropertyConfigurator.configure(System.getProperty("user.dir")+"\\log4j.properties");
		
		String pattern = "MM_dd_yyyy";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		fileEnd=format.format(new Date());
		String path= System.getProperty("user.dir");
		FileInputStream file = new FileInputStream(path+"//src/test//resources//UAT.properties");
		
		pr.load(file);
		
	}
	@Parameters(value={"browser"})
	@BeforeSuite
	public void setBrowser(String browser)
	{
		
		log.debug("finding driver");
		if(browser.equals("FireFox"))
			dr= new FirefoxDriver();
		else if(browser.equals("chrome"))
			dr = new ChromeDriver();
		else if (browser.equals("IE"))
			dr = new InternetExplorerDriver();
					
		dr.manage().window().maximize();
		
	}
	
	public void openBrowser() throws IOException
	{
		log.debug("opening browser");	
		dr.get(pr.getProperty("url"));
		dr.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				
	}
	@AfterSuite
	public void afterSuite()
	{
		System.out.println("closing browser");
//		dr.quit();
		
	}
	
	
	public WebElement find(String locator)
	{
		WebElement element=null;
		if(locator.contains("xpath"))
		{
			element=dr.findElement(By.xpath(pr.getProperty(locator)));
			
		}
		else if(locator.contains("id"))
		{
			element=dr.findElement(By.id(pr.getProperty(locator)));
		}
		else if(locator.contains("linkText"))
		{
			element=dr.findElement(By.linkText(pr.getProperty(locator)));
		}
		else if(locator.contains("plinkText"))
		{
			element=dr.findElement(By.partialLinkText(pr.getProperty(locator)));
		}
		else if(locator.contains("tagName"))
		{
			element=dr.findElement(By.tagName(pr.getProperty(locator)));
		}
		else
		{
			log.debug("locator not found");
			reportError("nolocator");
		}	
			
		
		return element;
	}
	
	
	public boolean checkLogin()
	{
		if(dr != null)
		{
			return true;
		}
		else
			return false;
		
	}
	public void execute(Hashtable<String,String> data)
	{
	//	System.out.println("current url is - "+dr.getCurrentUrl());
		switch(data.get("KeyWord"))
		{
		
		case "click": 
			 click(data.get("Object"));
		break;
		
		case "sendKey": 
			sendValue(data.get("Object"),data.get("Data"));
		break;
		
		default:
			System.out.println("keyword not matched-"+data.get("KeyWord"));
			break;
		
		}
	}
	public void click(String locator)
	{
		System.out.println("element clicking on is"+find(locator));
		WebElement element = find(locator);
		element.click();
		
		
	}
	public void sendValue(String locator,String value)
	{
		System.out.println(find(locator));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Entering value:"+value);
		WebElement element = find(locator);
		element.click();
		element.sendKeys(value);
		
	}
	
	public void reportError(String str) 
	{
		
	
		File file = ((TakesScreenshot)dr).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file,new File(System.getProperty("user.dir")+"\\screenshots\\"+str+fileEnd+".png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(System.getProperty("user.dir"+"\\screenshots\\"+str+fileEnd));
			e.printStackTrace();
		}
		Assert.fail("failed at"+str);
	}
	
	
}


