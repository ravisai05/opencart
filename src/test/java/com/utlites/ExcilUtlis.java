package com.utlites;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Logger;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.testBase.BaseTestCase;

public class ExcilUtlis extends BaseTestCase{

	String path;
	public XSSFWorkbook workbook;
	public ExcilUtlis(String path) throws IOException {
		// TODO Auto-generated constructor stub
		this.path=path;
			File file=new File(path);
			FileInputStream fis=new FileInputStream(file);
			workbook=new XSSFWorkbook(fis);
		
	}
	

	public int maxRows(String sheetName) {
		return workbook.getSheet(sheetName).getLastRowNum();
	}
	public int maxColumns(String sheetName) {
		return workbook.getSheet(sheetName).getRow(0).getLastCellNum();
		}
	public String getDataOfCell(String sheetName,int rowFound,int columnFound) {
		
	String data="";
	switch(workbook.getSheet(sheetName).getRow(rowFound).getCell(columnFound).getCellType()){
	case STRING :
		data = workbook.getSheet(sheetName).getRow(rowFound).getCell(columnFound).getStringCellValue();
		break;
		
	case NUMERIC :
		data=String.valueOf((int)(workbook.getSheet(sheetName).getRow(rowFound).getCell(columnFound).getNumericCellValue()));
		break;
	
}
return data; 
		
	}
}
