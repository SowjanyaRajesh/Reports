package com.uscourts2.ujspacourts.testCases;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class TestFile {

	
	@Test
	public void test() throws ParseException
	{
		  String pattern = "MM_dd_yyyy";
		    SimpleDateFormat format = new SimpleDateFormat(pattern);
		     // formatting
		    System.out.println(format.format(new Date()));
		
	}
	

	
}
