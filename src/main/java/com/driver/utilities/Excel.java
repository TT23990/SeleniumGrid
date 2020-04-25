package com.driver.utilities;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.testng.ITestContext;
import org.testng.ITestResult;

final public class Excel{
    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
    private static List<String> header;
    public static int RowNum=1;
    public static int LastRow;
    private static int FirstColNum;
    //This method is to set the File path and to open the Excel file
    //Pass Excel Path and SheetName as Arguments to this method
//    public static void setExcelFile(String Path,String SheetName) throws Exception {

//        FileInputStream ExcelFile = new FileInputStream(ReadConfig.getTestData("data-path"));
    public static void setWorkBook(Method tr){
        try {
            ExcelWBook = new XSSFWorkbook(new FileInputStream(Config.getTestData("data-path")));
            String test=tr.getName();
            ExcelWSheet = ExcelWBook.getSheet(tr.getName());//(Config.getTestData("data-sheet")); //
            LastRow=ExcelWSheet.getLastRowNum();
            header=new ArrayList<>();
            FirstColNum=ExcelWSheet.getRow(0).getFirstCellNum();
            for(int i=FirstColNum;i<ExcelWSheet.getRow(0).getLastCellNum();i++)
                header.add(ExcelWSheet.getRow(0).getCell(i).getStringCellValue());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getData(String colName) throws Exception {
        int ColNum=header.indexOf(colName)+FirstColNum;
        return (ColNum==-1)? colName + " not found in data file.": getCellData(ColNum);
    }

    //This method is to read the test data from the Excel cell
    //In this we are passing parameters/arguments as Row Num and Col Num
    private static String getCellData(int ColNum) throws Exception {

        Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
        String CellData = Cell.getStringCellValue();
        return CellData;
    }

//    private String ReadCell(int column, int row) {
//        return ExcelWSheet.getCellData(column, row).getContents();
//    }
//
//    private String ReadCellData(String column, int row) {
//        return ExcelWSheet.getCell(GetCell(column), row).getContents();
//    }
}
