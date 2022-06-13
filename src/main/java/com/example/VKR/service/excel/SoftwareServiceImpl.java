package com.example.VKR.service.excel;


import com.example.VKR.service.excel.bean.ExcelBean;
import com.example.VKR.service.excel.mapper.SoftwareMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SoftwareServiceImpl implements SoftwareMapper {

    @Autowired
    private SoftwareMapper softwareMapper;

    @Override
    public List<ExcelBean> getAllSoftware(){
        return softwareMapper.getAllSoftware();
    }

    @Override
    public int insertSoft(ExcelBean excelBean) {
        return softwareMapper.insertSoft(excelBean);
    }

}
