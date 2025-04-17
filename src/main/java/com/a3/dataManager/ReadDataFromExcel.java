package com.a3.dataManager;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

public class ReadDataFromExcel {

    /**
     * Read data from excel
     * @return
     * @throws IOException
     */
    public static String readDataFromExcel(String fileName, String sheetName, String rowData, String columnData) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/"+fileName+".xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheet(sheetName);

        int rowNumber=0, columnNumber=0;
        for(Row row: sheet){
            System.out.println(row.getCell(0));
            Cell cell = row.getCell(0);
            if(cell.getStringCellValue().equals(rowData)){
                rowNumber = row.getRowNum();
                System.out.println(rowNumber);
                break;
            }
        }

        for(Cell cell: sheet.getRow(0)){
            if(cell.getStringCellValue().equals(columnData)){
                columnNumber = cell.getColumnIndex();
                System.out.println(columnNumber);
                break;
            }
        }
        String value = sheet.getRow(rowNumber).getCell(columnNumber).toString();
        return value;
    }


    @Test(dataProvider = "userCredentials")
    public void testData(String test1, String test2, String test3) throws IOException {
        readDataFromExcel();
        System.out.println(test1 + " :: " +  test2 + " :: " + test3);
    }


    @DataProvider(name="userCredentials")
    public Object[][] readDataFromExcel() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(System.getProperty("user.dir")+"/src/test/resources/userData.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet sheet = workbook.getSheet("users");

        int numberOfRows = sheet.getPhysicalNumberOfRows();
        System.out.println("numberOfRows :: " + numberOfRows);

        int numberOfColumns = sheet.getRow(0).getLastCellNum();
        System.out.println("numberOfColumns :: "+ numberOfColumns);

        String[][] userDataSet = new String[numberOfRows][numberOfColumns];
        DataFormatter dataFormatter = new DataFormatter();

        for (int i=1; i<numberOfRows; i++){
            for (int j=1; j<numberOfColumns; j++){
                userDataSet[i][j] = dataFormatter.formatCellValue(sheet.getRow(i).getCell(j));
                System.out.println(userDataSet[i][j]);
            }
        }

        for (String[] data:userDataSet){
            System.out.println(Arrays.toString(data));
        }

        return userDataSet;


    }



}
