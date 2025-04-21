package com.a3.dataManager;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

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

    @Test
    public void testData() throws IOException {
      getData("Guest_Profile", "FIRST_NAME", "LAST_NAME");
    }

    public static List<Map<String, String>> getData(String sheetName, String... columnHeaders) throws IOException {
        List<Map<String, String>> result = new ArrayList<>();
        String filePath = "C:\\Users\\Siva\\Desktop\\DATA.xlsx";

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) throw new RuntimeException("Sheet not found: " + sheetName);

            Row headerRow = sheet.getRow(0);
            if (headerRow == null) throw new RuntimeException("Header row not found!");

            // Map header names to column indices
            Map<String, Integer> headerIndexMap = new HashMap<>();
            for (Cell cell : headerRow) {
                headerIndexMap.put(cell.getStringCellValue().trim(), cell.getColumnIndex());
            }

            // Validate headers
            for (String header : columnHeaders) {
                if (!headerIndexMap.containsKey(header)) {
                    throw new RuntimeException("Column header not found: " + header);
                }
            }

            // Iterate data rows
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Map<String, String> rowData = new HashMap<>();
                for (String header : columnHeaders) {
                    int colIndex = headerIndexMap.get(header);
                    Cell cell = row.getCell(colIndex);

                    String[] actualData = getCellValue(cell).split(":");
                    String name = actualData[1].replace("\"", "");
                    rowData.put(header, name);
                }
                result.add(rowData);
            }
        }
        System.out.println(result);
        return result;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> DateUtil.isCellDateFormatted(cell)
                    ? cell.getDateCellValue().toString()
                    : String.valueOf(cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            case FORMULA -> cell.getCellFormula();
            default -> "";
        };
    }



}
