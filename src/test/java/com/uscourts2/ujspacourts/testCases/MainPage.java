package com.uscourts2.ujspacourts.testCases;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.uscourts2.ujspacourts.base.Xls_Reader;

public class MainPage {

	WebDriver dr;
	public Xls_Reader xls;
	Properties pr =new Properties();
	public MainPage() throws IOException
	{
		
		xls = new Xls_Reader(System.getProperty("user.dir")+"\\Data.xlsx");
		String path= System.getProperty("user.dir");
		FileInputStream file = new FileInputStream(path+"//src/test//resources//UAT.properties");
		
		pr.load(file);
		
	}
	
	
	
	
	
	
	
	
	
	
}
