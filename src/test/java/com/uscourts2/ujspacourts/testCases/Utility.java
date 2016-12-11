package com.uscourts2.ujspacourts.testCases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.uscourts2.ujspacourts.base.Xls_Reader;



public class Utility {
	
	public static Object[][]  getData(String sheet,String testCase,Xls_Reader xls){
		// find the rowNum for the test
				int testCaseRowNum=1;
				while(!xls.getCellData(sheet, 0, testCaseRowNum).trim().toLowerCase().equals(testCase.toLowerCase())){
					testCaseRowNum++;
				}
	//			System.out.println(testCaseRowNum);
			
				// row for Column and Data
				int colStartRowNum=testCaseRowNum+1;
				int dataStartRowNum=testCaseRowNum+2;
				int rows=0;
				// total rows of data in the test
				while(!xls.getCellData(sheet, 0, dataStartRowNum+rows).trim().equals("")){
					rows++;
				}
				System.out.println("Total rows "+ rows);

				//total cols
				int cols=0;
				while(!xls.getCellData(sheet, cols, colStartRowNum).trim().equals("")){
					cols++;
				}
				//System.out.println("Total cols "+ cols);
				// print the data
				Object[][] testData = new Object[rows][1];
				int i =0;
				for(int rNum=dataStartRowNum;rNum<dataStartRowNum+rows;rNum++){
					Hashtable<String,String> table = new Hashtable<String,String> ();
					
					for(int cNum=0;cNum<cols;cNum++){
					String data = xls.getCellData(sheet, cNum, rNum);
					String colName = xls.getCellData(sheet, cNum, colStartRowNum);
					
	//				System.out.println(colName+" --- "+data);
					table.put(colName, data);
					}
					//put the hashtable in object Array
					testData[i][0]=table;
					i++;
//					System.out.println("------------------------------------");
				}
				
				return testData;
			}
	
	public static boolean isTestCaseRunnable(String sheet,String testCaseName,Xls_Reader xls) {
		int rows = xls.getRowCount(sheet);
		
		for(int rNum=2;rNum<=rows;rNum++){
			String testName=xls.getCellData(sheet, "TestCase", rNum);
			if(testName.toLowerCase().equals(testCaseName.toLowerCase())){
				String runMode=xls.getCellData(sheet, "RunMode", rNum);
				if(runMode.equals("Y"))
					return true;
				else
					return false;
			}
			
		}
		return false;

	}
	public static Object[][] getTestData(String sheet,String testCase,Xls_Reader xls){
		// find the rowNum for the test
				int testCaseRowNum=1;
				int noRows=0;
				while(!xls.getCellData(sheet, 0, testCaseRowNum).trim().toLowerCase().equals(testCase.toLowerCase())){
					testCaseRowNum++;
					
				}
			
				for(int i=testCaseRowNum;i<=xls.getRowCount(sheet);i++)
				{
					if(xls.getCellData(sheet, 0, i).trim().toLowerCase().equals(testCase.toLowerCase()))
					noRows++;
				}
					
				
				System.out.println(testCaseRowNum);
				System.out.println(noRows);
			
				// row for Column and Data
				int colStartRowNum=testCaseRowNum;
				
								//total cols
				
				// print the data
				Object[][] testData = new Object[noRows][1];
				int i =0;
				for(int rNum=testCaseRowNum;rNum<testCaseRowNum+noRows;rNum++){
				
					int cols=0;
					while(!xls.getCellData(sheet, cols, rNum).trim().equals("")){
						cols++;
					}
			//		System.out.println("Total cols "+ cols);
					Hashtable<String,String> table = new Hashtable<String,String> ();
					
					for(int cNum=0;cNum<cols;cNum++){
					String data = xls.getCellData(sheet, cNum, rNum);
					String colName = xls.getCellData(sheet, cNum, 1);
					
//					System.out.println(colName+" --- "+data);
					table.put(colName, data);
					}
					//put the hashtable in object Array
					testData[i][0]=table;
					i++;
//					System.out.println("------------------------------------");
				}
				
				return testData;
			}
	
	public void test()
	{
		
		Xls_Reader xls = new Xls_Reader(System.getProperty("user.dir")+"\\Data.xlsx");
		//getTestData("TestData","docket",xls);
		//System.out.println(isTestCaseRunnable("Tests","docket",xls));
		getTestData("TestData","Login",xls);
	}

}
