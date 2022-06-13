package com.example.VKR.service.excel.mapper;

import com.example.VKR.service.excel.bean.ExcelBean;

import java.util.List;

public interface SoftwareMapper {
    List<ExcelBean> getAllSoftware();
    int insertSoft(ExcelBean excelBean);
}
