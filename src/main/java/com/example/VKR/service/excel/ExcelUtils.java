package com.example.VKR.service.excel;

import com.example.VKR.service.excel.bean.ExcelBean;
import org.apache.poi.ss.usermodel.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    public static List<ExcelBean> excelToSoftIdList(InputStream inputStream){
        List<ExcelBean> list = new ArrayList<>();
        Workbook workbook = null;
        try {
            workbook = WorkbookFactory.create(inputStream);
            inputStream.close();
            Sheet sheet = workbook.getSheetAt(0);
            int rowLength = sheet.getLastRowNum();
            Row row = sheet.getRow(0);
            int colLength = row.getLastCellNum();
            Cell cell = row.getCell(0);

            for (int i =1; i <= rowLength; i++){
                ExcelBean bean = new ExcelBean();
                row = sheet.getRow(i);
                for (int j = 0; j <colLength; j++){
                    cell = row.getCell(j);
                    if(cell != null){
                        //cell.setCellType(Cell.CELL_TYPE_STRING);
                        String data = cell.getStringCellValue();
                        data = data.trim();
                        if(j == 1){
                            bean.setName(data);
                        }
                    }
                }
                list.add(bean);
            }
        }catch (Exception e){

        }
        return list;
    }
}
