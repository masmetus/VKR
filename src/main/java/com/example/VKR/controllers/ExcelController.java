package com.example.VKR.controllers;

import com.example.VKR.service.excel.bean.ExcelBean;
import com.example.VKR.service.excel.ExcelUtils;
import com.example.VKR.service.excel.SoftwareServiceImpl;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class ExcelController {

    @Autowired
    private SoftwareServiceImpl softwareService;

    @RequestMapping("/uploadExcel")
    @ResponseBody
    public String uploadExcel(@RequestParam("file") MultipartFile file) {
        String name = file.getOriginalFilename();
        if (name.length() < 6 || !name.substring(name.length() - 5).equals(".xlsx")) {
            return "File format Error";
        }
        List<ExcelBean> list = null;

        try {
            list = ExcelUtils.excelToSoftIdList(file.getInputStream());
            if (list == null || list.size() <= 0) {
                return "Файл пуст";
            }
            try {
                int result = 0;
                for (ExcelBean excelBean : list) {
                    System.out.println(excelBean.toString());
                    result = softwareService.insertSoft(excelBean);
                }
                if (result > 0) {
                    System.out.println("[ExcelController] данные Excel успешно сохранены");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return e.getMessage();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }

        return "Сохранено успешно";
    }

    @GetMapping("/getExcel")
    public void getExcel(HttpServletResponse response) throws IOException {
        String fileName = "Наименование о ПО.xls";
        response.setContentType("application / excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));

        List<ExcelBean> list = softwareService.getAllSoftware();

        HSSFWorkbook workbook = new HSSFWorkbook(); //Моя любимя книжка
        HSSFSheet sheet = workbook.createSheet(); //Мой любимый лист

        int rowNum = 0;

        String [] headers = {"Название"};

        HSSFRow row = sheet.createRow(rowNum);

        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
            sheet.setColumnWidth(i, 256 * 13);
        }

        for (ExcelBean software : list){
            rowNum++;
            HSSFRow row1 = sheet.createRow(rowNum);

            row1.createCell(0).setCellValue(software.getName());
        }

        workbook.write(response.getOutputStream());
        System.out.println("[Загрузка завершена]");
    }
}
