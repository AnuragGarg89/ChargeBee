package org.chargebee.assignment.utility;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

public class ReadExcelFile {
    private XSSFWorkbook workbook;

    public ReadExcelFile() {
        File file = new File("DataFiles/InputData.xlsx");
        try {
            FileInputStream fis = new FileInputStream(file);
            workbook = new XSSFWorkbook(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getStringCellValue(String sheetName, int rowNum, int cellNum) {
        return workbook.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getStringCellValue();
    }

    public String getStringCellValue(int sheetIndex, int rowNum, int cellNum) {
        return workbook.getSheetAt(sheetIndex).getRow(rowNum).getCell(cellNum).getStringCellValue();
    }

    public double getNumericCellValue(String sheetName, int rowNum, int cellNum) {
        return workbook.getSheet(sheetName).getRow(rowNum).getCell(cellNum).getNumericCellValue();
    }
}
