package com.vantalii.enginee.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.vantalii.enginee.service.IExcelService;

@Service
public class ExcelService implements IExcelService {

    @Override
    public boolean importExcel(String fileName, boolean onlyFirstSheet) {
        Workbook workbook = getWorkbook(fileName);
        if (workbook == null) {
            return false;
        }
        try {
            int count = 1;
            if (!onlyFirstSheet) {
                count = workbook.getNumberOfSheets();
            }
            for (int i = 0; i < count; i++) {
                System.out.println("  ###################################" + "Sheet Number " + i + "  ###################################");
                Sheet datatypeSheet = workbook.getSheetAt(0);
                Iterator<Row> iterator = datatypeSheet.iterator();
                readColumnsAndRows(iterator);
            }
            workbook.close();
        } catch (Throwable t) {
            t.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean splitExcel(String fileName) {
        Workbook workbook = getWorkbook(fileName);
        if (workbook == null) {
            return false;
        }
        try {
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean splitExcelBySheetNum(String fileName) {
        Workbook workbook = getWorkbook(fileName);
        if (workbook == null) {
            return false;
        }
        try {
            int numOfSheets = workbook.getNumberOfSheets();
            if (numOfSheets > 1) {
                for(int i = 0 ;i<numOfSheets;i++){
                    Sheet inputSheet = workbook.getSheetAt(i);
                    String sheetName = inputSheet.getSheetName();
                    Workbook outputWB = new XSSFWorkbook();
                    Sheet outputSheet = outputWB.createSheet(sheetName);
                    Iterator<Row> iterator = inputSheet.iterator();
                    int rowCount = 0;
                    while (iterator.hasNext()) {
                        Row currentRow = iterator.next();
                        Iterator<Cell> cellIterator = currentRow.iterator();
                        Row outRow = outputSheet.createRow(rowCount);
                        int cellCount = 0;
                        while (cellIterator.hasNext()) {
                            Cell currentCell = cellIterator.next();
                            CellType cellType = currentCell.getCellTypeEnum();
                            Cell outCell = outRow.createCell(cellCount);
                            switch (cellType){
                                case STRING:
                                    outCell.setCellValue(currentCell.getStringCellValue());
                                    break;
                                case NUMERIC:
                                    outCell.setCellValue(currentCell.getNumericCellValue());
                                    break;
                                case BOOLEAN:
                                    outCell.setCellValue(currentCell.getBooleanCellValue());
                                    break;
                                case FORMULA:
                                    outCell.setCellValue(currentCell.getCellFormula());
                                    break;
                            }
                            cellCount++;
                        }
                        writeToWorkbook(outputWB,sheetName);
                        rowCount++;
                    }
                }
            }
            workbook.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void writeToWorkbook(Workbook wb,String sheetName){

        try{
            FileOutputStream out=new FileOutputStream(sheetName+".xlsx");
            wb.write(out);
            out.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    private Workbook getWorkbook(String fileName) {
        try {
            File importFile = new File(fileName);
            FileInputStream excelFile = new FileInputStream(importFile);
            //below code determines whether file is HSSFWorkbook or XSSFWorkbook automatically
            Workbook workbook = WorkbookFactory.create(excelFile);
            return workbook;
        } catch (Exception e) {
            return null;
        }
    }

    private void readColumnsAndRows(Iterator<Row> iterator) {
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            Iterator<Cell> cellIterator = currentRow.iterator();
            while (cellIterator.hasNext()) {
                Cell currentCell = cellIterator.next();
                //getCellTypeEnum shown as deprecated for version 3.15
                //getCellTypeEnum ill be renamed to getCellType starting from version 4.0
                if (currentCell.getCellTypeEnum() == CellType.STRING) {
                    System.out.print(currentCell.getStringCellValue() + "--");
                } else if (currentCell.getCellTypeEnum() == CellType.NUMERIC) {
                    System.out.print(currentCell.getNumericCellValue() + "--");
                }

            }
            System.out.println();
        }
    }

    


}
