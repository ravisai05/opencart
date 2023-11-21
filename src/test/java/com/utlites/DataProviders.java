package com.utlites;

import java.io.IOException;

import org.testng.annotations.DataProvider;
public class DataProviders {

	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException {
		
		String path="src/test/resources/opentextLoginDetails.xlsx";
		ExcilUtlis ex=new ExcilUtlis(path);
		
		int rows1=ex.maxRows("Sheet1");
		int col=ex.maxColumns("sheet1");
		String data1[][]=new String[rows1][col];
		for(int i=1;i<=rows1;i++) {
			for (int j=0 ; j< col;j++) {
				System.out.println(ex.getDataOfCell("Sheet1", i, j));
				data1[i-1][j]=ex.getDataOfCell("Sheet1", i, j);
			}
		}
		
		return data1;
	}
	
	
}
