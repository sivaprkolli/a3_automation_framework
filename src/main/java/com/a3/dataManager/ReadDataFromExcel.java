package com.a3.dataManager;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

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

}
