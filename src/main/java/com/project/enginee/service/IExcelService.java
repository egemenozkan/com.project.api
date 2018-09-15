package com.project.enginee.service;


public interface IExcelService {
    boolean importExcel(String fileName, boolean onlyFirstSheet);

    boolean splitExcel(String fileName);

    boolean splitExcelBySheetNum(String fileName);
}
