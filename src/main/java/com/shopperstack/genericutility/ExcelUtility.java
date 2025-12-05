package com.shopperstack.genericutility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtility {
	public String getDataFromExcelFile(String sheet, int row, int cell) throws EncryptedDocumentException, IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/TestData/Org.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		String data = wb.getSheet(sheet).getRow(row).getCell(cell).getStringCellValue();
		wb.close();
		return data;
	}

	public int getLastRowNum(String sheet) throws Throwable {
		FileInputStream fis = new FileInputStream("./src/test/resources/TestData/Org.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheet);
		int lastRowCount = sh.getLastRowNum();
		return lastRowCount;
	}

	public int getLastCellNum(String sheet, int row, int cell) throws Throwable {
		FileInputStream fis = new FileInputStream("./src/test/resources/TestData/Org.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheet);
		Row r = sh.getRow(row);
		int lastCellCount = r.getLastCellNum();
		return lastCellCount;
	}

	public Object[][] readMultipleDataFromExcel(String sheet) throws Throwable{

		FileInputStream fis = new FileInputStream("./src/test/resources/TestData/Org.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet(sheet);
		int lastrow = sh.getLastRowNum();
		int lastcell = sh.getRow(lastrow).getLastCellNum();
		Object[][] obj = new Object[lastrow][lastcell];
		//i is row index,j is cell index
		int i = 1, j = 0;
		for (; i <= lastrow; i++) {
			Row row = sh.getRow(i);

			for (; j <= lastcell; j++) {

				obj[i][j] = row.getCell(j).getStringCellValue();
			}
		}
		return obj;
	}
	
	public void setDataInToExcelSheet(String sheet, int row, int cell, String cellvalue)
			throws EncryptedDocumentException, IOException {
		String filePath = "C:\\Users\\Admin\\Desktop\\Java\\UnifiedFrameWork\\Data\\NinzaHrm.xlsx";
		try (FileInputStream fis = new FileInputStream(filePath);
				Workbook wb = WorkbookFactory.create(fis);
				FileOutputStream fos = new FileOutputStream(filePath)) {
			Sheet sheet1 = wb.getSheet(sheet);
			if (sheet1 == null) {
				sheet1 = wb.createSheet(sheet);
			}
			Row row1 = sheet1.getRow(row);
			if (row1 == null) {
				row1 = sheet1.createRow(row);
			}
			Cell cell1 = row1.createCell(cell);
			cell1.setCellValue(cellvalue);
			wb.write(fos);
		} catch (IOException e) {

			e.printStackTrace();
		}

	}
}
