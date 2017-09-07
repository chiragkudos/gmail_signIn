package com.Framework.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Map;
import java.util.Properties;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestData {

	public static String uploadFilePath(String filename) {
		String dataFilePath = "Data/" + filename + "";
		File datafile = new File(dataFilePath);
		String fullpath = datafile.getAbsolutePath();
		return fullpath;
	}

	public static XSSFSheet ExcelWSheet;
	public static XSSFWorkbook ExcelWBook;
	public static XSSFCell Cell;
	public static XSSFRow Row;
	// Data provider
	public static <UnicodeString> UnicodeString getCellData(int RowNum, int ColNum) throws Exception {
		try {
			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
			int dataType = Cell.getCellType();
			if (dataType == 3) {
				return (UnicodeString) "";
			} else {
				DataFormatter formatter = new DataFormatter();
				UnicodeString Data = (UnicodeString) formatter.formatCellValue(Cell);
				return Data;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw (e);
		}
	}
	public static Object[][] getDataForDataprovider(String FilePath, String SheetName,int startRow,int startCol)
			throws Exception {
		String[][] tabArray = null;
		try {
			FileInputStream ExcelFile = new FileInputStream(FilePath);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			int ci, cj;
			int totalRows = ExcelWSheet.getLastRowNum();
			Row = ExcelWSheet.getRow(2);
			int totalCols = Row.getPhysicalNumberOfCells();
			tabArray = new String[totalRows - 1][totalCols];
			ci = 0;
			for (int i = startRow; i <= totalRows; i++, ci++) {
				cj = 0;
				for (int j = startCol; j < totalCols; j++, cj++) {
					tabArray[ci][cj] = getCellData(i, j);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		return (tabArray);
	}

//  write data into excel file
	
	public static void writeExcel(String filename, String sheetname,  String cell[]) throws IOException {
		File datafile = new File(filename);
		String fullpath = datafile.getAbsolutePath();
		ExcelWBook = new XSSFWorkbook(fullpath);
		ExcelWSheet = ExcelWBook.getSheet(sheetname);
		int totalRows = ExcelWSheet.getLastRowNum();
		try {
			int rowno = totalRows + 1;
			FileInputStream inputStream = new FileInputStream(new File(fullpath));
			XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
			XSSFSheet firstSheet = workbook.getSheetAt(0);
			XSSFRow row = firstSheet.createRow(rowno);
			for(int i=0;i<cell.length;i++)
			{
				row.createCell(i).setCellValue(cell[i].toString());
			}
			inputStream.close();
			FileOutputStream fos = new FileOutputStream(new File(fullpath));
			workbook.write(fos);
			fos.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	// Create new excel file
	public static void createXLSFile(String filepath,String Sheetname, String cell[]) {
		try
		{
		File datafile = new File(filepath);
		String fullpath = datafile.getAbsolutePath();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(Sheetname);
		XSSFRow row = sheet.createRow(0);
		for(int i=0;i<cell.length;i++)
		{
			row.createCell(i).setCellValue(cell[i].toString());
		}
		FileOutputStream fileOut = new FileOutputStream(fullpath);
		workbook.write(fileOut);
		fileOut.close();
		}catch (Exception ex) {
			System.out.println(ex);
		}
	}
// Get total number of row from excel sheet
	public static int getTotalRow(String Datafile,String sheet) {
		int totalRows = 0;
		try {
			File datafile = new File(Datafile);
			String fullpath = datafile.getAbsolutePath();
			ExcelWBook = new XSSFWorkbook(fullpath);
			ExcelWSheet = ExcelWBook.getSheet(sheet);
			totalRows = ExcelWSheet.getLastRowNum();
		} catch (Exception e) {
		}
		return totalRows;
	}

	public static boolean verifyCellValue(String datafile,String sheet, String value,int i , int j)
	{
		boolean ServiceFlag = false;
		try {
			FileInputStream ExcelFile = new FileInputStream(datafile);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(sheet);
			String ser = getCellData(i, j);
			
					if (ser.equalsIgnoreCase(value.trim())||value.trim().contains(ser)||ser.contains(value.trim()))
						ServiceFlag = true;
				
		} catch (FileNotFoundException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ServiceFlag;
	}
	public static String getCellValue(int i , int j,String datafile,String sheet)
	{
		String ser=null;
		try {
			FileInputStream ExcelFile = new FileInputStream(datafile);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(sheet);
			ser = getCellData(i, j);
		} catch (FileNotFoundException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ser;
	}

	public static boolean verifyRowWiseColumnValue(String Datafile,String sheet,String value,int ColumnNumber) throws Exception {
		boolean ServiceFlag = false;
		String[][] tabArray = null;
		try {
			FileInputStream ExcelFile = new FileInputStream(Datafile);
			ExcelWBook = new XSSFWorkbook(ExcelFile);
			ExcelWSheet = ExcelWBook.getSheet(sheet);
			int startRow = 1;
			int startCol = 0;
			int ci, cj;
			int totalRows = ExcelWSheet.getLastRowNum();
			int totalCols = 5;
			tabArray = new String[totalRows][totalCols];
			ci = 0;
			for (int i = startRow; i <= totalRows; i++, ci++) {
				cj = ColumnNumber;
				int j = ColumnNumber;
				tabArray[ci][cj] = getCellData(i, j);

				if (((String) getCellData(i, j)).equalsIgnoreCase(value.trim())
						|| ((String) getCellData(i, j)).contains(value.trim())) {
					ServiceFlag = true;
					break;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Could not read the Excel sheet");
			e.printStackTrace();
		}
		return ServiceFlag;
	}
	public static void removeRowFromExcel(String Datafile,String sheetName, String value) throws IOException {
		try {
			Workbook wb = WorkbookFactory.create(new FileInputStream(Datafile));
			Sheet sheet = wb.getSheet(sheetName);
			Workbook wb2 = new HSSFWorkbook();
			wb2 = wb;
			Row row;
			row = sheet.getRow(0);
			if (row == null)
				row = sheet.getRow(1);
			int lastIndex = sheet.getLastRowNum();
			boolean flag = true;
			int rownum = 0;
			for (int n = 1; n <= sheet.getLastRowNum(); n++) {
				// sheet.getRow(0).getCell(0);
				row = sheet.getRow(n);
				for (int cn = 0; cn < row.getLastCellNum(); cn++) {
					Cell c = row.getCell(cn);
					String text = c.getStringCellValue();
					if (value.equals(text)) {
						flag = false;
						break;
					}
				}
				rownum = n;
				if (flag == false) {
					break;
				}
			}
			if (rownum != 0) {
				row = sheet.getRow(rownum);
				row.setZeroHeight(true);
				sheet.removeRow(row);
				if(rownum + 1<=lastIndex){
				sheet.shiftRows(rownum + 1, lastIndex, -1);
				}
				FileOutputStream fileOut = new FileOutputStream(Datafile);
				wb2.write(fileOut);
				fileOut.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String getValueFromConfig(String Datafile, String value) throws IOException {
		String result="";
		File file = new File(Datafile);
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();
		try {
				prop.load(fileInput);
				result = prop.getProperty(value);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
		}
		return result;
	}
}
